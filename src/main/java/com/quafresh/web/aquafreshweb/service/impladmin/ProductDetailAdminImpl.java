package com.quafresh.web.aquafreshweb.service.impladmin;

import com.quafresh.web.aquafreshweb.dto.admin.PictureAdimDTO;
import com.quafresh.web.aquafreshweb.dto.admin.ProductDetailAdminDTO;
import com.quafresh.web.aquafreshweb.entity.*;
import com.quafresh.web.aquafreshweb.repositories.*;
import com.quafresh.web.aquafreshweb.service.admin.ProductDetailAdminService;
import com.quafresh.web.aquafreshweb.util.AdminMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductDetailAdminImpl implements ProductDetailAdminService {

    private final AdminMapper adminMapper;
    private final ProductDetailRepository productDetailRepository;
    private final PictureRepository pictureRepository;
    private final ProductRepository productRepository;
    private final TechnologyRepository technologyRepository;
    private final ColorRepository colorRepository;
    private final DiscountRepository discountRepository;

    @Override
    public ProductDetailAdminDTO getByID(Integer id) {
        ProductDetail productDetail = productDetailRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Product detail not found"));
        return adminMapper.toProductDetailAdminDTO(productDetail);
    }

    @Override
    public List<ProductDetailAdminDTO> getAll() {
        List<ProductDetail> listProductDetail = productDetailRepository.findAll();
        return listProductDetail.stream().map(adminMapper::toProductDetailAdminDTO).collect(Collectors.toList());
    }

    @Override
    public List<ProductDetailAdminDTO> search(String keyword) {

        List<ProductDetail> listProductDetail = productDetailRepository.findByProductName(keyword);
        return listProductDetail.stream().map(adminMapper::toProductDetailAdminDTO).collect(Collectors.toList());
    }

    @Override
    public ProductDetailAdminDTO create(ProductDetailAdminDTO dto) {
        ProductDetail productDetail = new ProductDetail();

        productDetail.setPrice(dto.getPrice());
        productDetail.setQuantity(dto.getQuantity());
        productDetail.setDescription(dto.getDescription());
        productDetail.setStatus(dto.getStatus());

        // Lấy và gán Entity liên quan từ DB (hoặc throw nếu không tìm thấy)
        Product product = productRepository.findById(dto.getIdProduct().getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productDetail.setIdProduct(product);

        Color color = colorRepository.findById(dto.getIdColor().getId())
                .orElseThrow(() -> new RuntimeException("Color not found"));
        productDetail.setIdColor(color);

        Technology tech = technologyRepository.findById(dto.getIdTechnology().getId())
                .orElseThrow(() -> new RuntimeException("Technology not found"));
        productDetail.setIdTechnology(tech);

        Discount discount = null;
        if (dto.getIdDiscount() != null) {
            discount = discountRepository.findById(dto.getIdDiscount().getId())
                    .orElseThrow(() -> new RuntimeException("Discount not found"));
        }
        productDetail.setIdDiscount(discount);

        // Lưu ProductDetail trước để có ID (nếu cần dùng cho ảnh)
        productDetailRepository.save(productDetail);

        // Nếu có ảnh thì lưu ảnh gắn với productDetail
        if (dto.getListUrl() != null && !dto.getListUrl().isEmpty()) {
            List<Picture> pictures = dto.getListUrl().stream().map(pictureDto -> {
                Picture pic = new Picture();
                pic.setUrlImage(pictureDto.getUrlImage());
                pic.setIdProductDetail(productDetail); // gắn quan hệ
                return pic;
            }).collect(Collectors.toList());

            // Lưu danh sách ảnh (nếu có PictureRepository)
            pictureRepository.saveAll(pictures);

            // gắn lại vào productDetail để đồng bộ
            productDetail.setListUrl(pictures);
        }

        // Trả về DTO
        return adminMapper.toProductDetailAdminDTO(productDetail);
    }

    @Override
    @Transactional
    public ProductDetailAdminDTO update(Integer id, ProductDetailAdminDTO productAdminDTO) {
       ProductDetail productDetail = productDetailRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        productDetail.setPrice(productAdminDTO.getPrice());
        productDetail.setQuantity(productAdminDTO.getQuantity());
        productDetail.setDescription(productAdminDTO.getDescription());
        productDetail.setStatus(productAdminDTO.getStatus());

        // Lấy và gán Entity liên quan từ DB (hoặc throw nếu không tìm thấy)
        Product product = productRepository.findById(productAdminDTO.getIdProduct().getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productDetail.setIdProduct(product);

        Color color = colorRepository.findById(productAdminDTO.getIdColor().getId())
                .orElseThrow(() -> new RuntimeException("Color not found"));
        productDetail.setIdColor(color);

        Technology tech = technologyRepository.findById(productAdminDTO.getIdTechnology().getId())
                .orElseThrow(() -> new RuntimeException("Technology not found"));
        productDetail.setIdTechnology(tech);

        productDetailRepository.save(productDetail);
        // Xử lý ảnh
        List<Picture> oldPictures = pictureRepository.findByIdProductDetail(productDetail);
        List<String> newUrls = productAdminDTO.getListUrl().stream()
                .map(PictureAdimDTO::getUrlImage).toList();

        // 1. Xoá ảnh cũ không còn trong newUrls
        List<Picture> picturesToDelete = oldPictures.stream()
                .filter(pic -> !newUrls.contains(pic.getUrlImage()))
                .collect(Collectors.toList());
        pictureRepository.deleteAll(picturesToDelete);

        // 2. Thêm ảnh mới (chưa có trong oldPictures)
        List<String> oldUrls = oldPictures.stream()
                .map(Picture::getUrlImage).toList();

        List<Picture> picturesToAdd = newUrls.stream()
                .filter(url -> !oldUrls.contains(url)) // chỉ thêm nếu chưa có
                .map(url -> {
                    Picture pic = new Picture();
                    pic.setUrlImage(url);
                    pic.setIdProductDetail(productDetail);
                    return pic;
                }).collect(Collectors.toList());

        pictureRepository.saveAll(picturesToAdd);

        return adminMapper.toProductDetailAdminDTO(productDetail);
    }

    @Override
    public String delete(Integer id) {
        ProductDetail productDetail = productDetailRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        productDetailRepository.delete(productDetail);
        return "Product deleted";
    }
}
