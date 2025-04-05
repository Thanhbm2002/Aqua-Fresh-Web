package com.quafresh.web.aquafreshweb.service.impladmin;

import com.quafresh.web.aquafreshweb.dto.admin.TechnologyAdminDTO;
import com.quafresh.web.aquafreshweb.entity.Technology;
import com.quafresh.web.aquafreshweb.repositories.TechnologyRepository;
import com.quafresh.web.aquafreshweb.service.admin.TechnologyService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TechnologyImpl implements TechnologyService {

    private final TechnologyRepository technologyRepository; // Repository cho Technology

    @Override
    public TechnologyAdminDTO getByID(Integer id) {
        // Lấy Technology theo ID, nếu không tìm thấy thì ném ngoại lệ
        Technology technology = technologyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Technology not found"));

        // Trả về DTO cho Technology
        return TechnologyAdminDTO.builder()
                .id(technology.getId())
                .name(technology.getName())
                .status(technology.getStatus())
                .build();
    }

    @Override
    public List<TechnologyAdminDTO> getAll() {
        // Lấy tất cả các Technology từ database
        List<Technology> technologyList = technologyRepository.findAll();

        // Chuyển đổi danh sách Technology thành danh sách DTO
        return technologyList.stream().map(technology -> TechnologyAdminDTO.builder()
                        .id(technology.getId())
                        .name(technology.getName())
                        .status(technology.getStatus())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<TechnologyAdminDTO> search(String keyword) {
        // Tìm kiếm Technology theo tên, có thể tùy chỉnh để phù hợp hơn
        List<Technology> technologyList = technologyRepository.findByName(keyword);

        // Nếu không tìm thấy, ném ngoại lệ
        if (technologyList.isEmpty()) {
            throw new IllegalArgumentException("Technology not found");
        }

        // Trả về danh sách các Technology dưới dạng DTO
        return technologyList.stream().map(technology -> TechnologyAdminDTO.builder()
                        .id(technology.getId())
                        .name(technology.getName())
                        .status(technology.getStatus())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public TechnologyAdminDTO create(TechnologyAdminDTO technologyAdminDTO) {
        // Tạo đối tượng Technology từ DTO
        Technology technology = new Technology();
        technology.setName(technologyAdminDTO.getName());
        technology.setStatus(technologyAdminDTO.getStatus());

        // Lưu vào cơ sở dữ liệu
        technology = technologyRepository.save(technology);

        // Trả về DTO của Technology vừa tạo
        return TechnologyAdminDTO.builder()
                .id(technology.getId())
                .name(technology.getName())
                .status(technology.getStatus())
                .build();
    }

    @Override
    public TechnologyAdminDTO update(Integer id, TechnologyAdminDTO technologyAdminDTO) {
        // Lấy Technology theo ID, nếu không tìm thấy thì ném ngoại lệ
        Technology technology = technologyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Technology not found"));

        // Cập nhật các trường của Technology
        technology.setName(technologyAdminDTO.getName());
        technology.setStatus(technologyAdminDTO.getStatus());

        // Lưu thay đổi vào cơ sở dữ liệu
        technology = technologyRepository.save(technology);

        // Trả về DTO sau khi cập nhật
        return TechnologyAdminDTO.builder()
                .id(technology.getId())
                .name(technology.getName())
                .status(technology.getStatus())
                .build();
    }

    @Override
    public String delete(Integer id) {
        // Lấy Technology theo ID, nếu không tìm thấy thì ném ngoại lệ
        Technology technology = technologyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Technology not found"));

        // Xóa Technology khỏi cơ sở dữ liệu
        technologyRepository.delete(technology);

        // Trả về thông báo xác nhận
        return "Technology deleted successfully";
    }
}
