package com.quafresh.web.aquafreshweb.service.impladmin;

import com.quafresh.web.aquafreshweb.dto.admin.CompanyAdminDTO;
import com.quafresh.web.aquafreshweb.entity.Company;
import com.quafresh.web.aquafreshweb.repositories.CompanyRepository;
import com.quafresh.web.aquafreshweb.service.admin.CompanyService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CompanyImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    @Override
    public CompanyAdminDTO getByID(Integer id) {
        Company company = companyRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Company not found"));
        return CompanyAdminDTO.builder()
                .id(company.getId())
                .name(company.getName())
                .status(company.getStatus())
                .build();
    }

    @Override
    public List<CompanyAdminDTO> getAll() {
        List<Company> companyList = companyRepository.findAll();
        return companyList.stream().map(company -> CompanyAdminDTO.builder()
                .id(company.getId())
                .name(company.getName())
                .status(company.getStatus())
                .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<CompanyAdminDTO> search(String keyword) {
        List<Company> companyList = companyRepository.findByName(keyword);
        if (companyList.isEmpty()){
            throw new IllegalArgumentException("Company not found");
        }
        return companyList.stream().map(company -> CompanyAdminDTO.builder()
                        .id(company.getId())
                        .name(company.getName())
                        .status(company.getStatus())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public CompanyAdminDTO create(CompanyAdminDTO companyAdminDTO) {
        Company company = new Company();
        company.setName(companyAdminDTO.getName());
        company.setStatus(companyAdminDTO.getStatus());
        company = companyRepository.save(company);
        return CompanyAdminDTO.builder()
                .id(company.getId())
                .name(company.getName())
                .status(company.getStatus())
                .build();
    }

    @Override
    public CompanyAdminDTO update(Integer id, CompanyAdminDTO companyAdminDTO) {
        Company company = companyRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Company not found"));
        company.setName(companyAdminDTO.getName());
        company.setStatus(companyAdminDTO.getStatus());
        return CompanyAdminDTO.builder()
                .id(company.getId())
                .name(company.getName())
                .status(company.getStatus())
                .build();
    }

    @Override
    public String delete(Integer id) {
        Company company = companyRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Company not found"));
        companyRepository.delete(company);
        return "Company deleted successfully";
    }
}
