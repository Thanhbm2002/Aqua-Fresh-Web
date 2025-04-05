package com.quafresh.web.aquafreshweb.service.impladmin;

import com.quafresh.web.aquafreshweb.dto.admin.CategoryAdminDTO;
import com.quafresh.web.aquafreshweb.dto.admin.CompanyAdminDTO;
import com.quafresh.web.aquafreshweb.dto.admin.ProductAdminDTO;
import com.quafresh.web.aquafreshweb.entity.Category;
import com.quafresh.web.aquafreshweb.entity.Company;
import com.quafresh.web.aquafreshweb.entity.Product;
import com.quafresh.web.aquafreshweb.repositories.CatogoryRepository;
import com.quafresh.web.aquafreshweb.repositories.CompanyRepository;
import com.quafresh.web.aquafreshweb.repositories.ProductRepository;
import com.quafresh.web.aquafreshweb.service.admin.ProductAdminService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductAdminImpl implements ProductAdminService {
    private ProductRepository productRepository;
    private final CatogoryRepository catogoryRepository;
    private final CompanyRepository companyRepository;

    private ProductAdminDTO convertToDTO(Product product) {
        return ProductAdminDTO.builder()
                .id(product.getId())
                .name(product.getProductName())
                .idCategory(CategoryAdminDTO.builder()
                        .id(product.getIdCategory().getId())
                        .name(product.getIdCategory().getName())
                        .status(product.getIdCategory().getStatus())
                        .build())
                .idCompany(CompanyAdminDTO.builder()
                        .id(product.getIdCompany().getId())
                        .name(product.getIdCompany().getName())
                        .status(product.getIdCompany().getStatus())
                        .build())
                .status(product.getStatus())
                .build();
    }

    private List<ProductAdminDTO> convertToDTO(List<Product> products) {
        return products.stream().map(product -> ProductAdminDTO.builder()
                .id(product.getId())
                .name(product.getProductName())
                .idCategory(product.getIdCategory() != null ?
                        CategoryAdminDTO.builder().id(product.getIdCategory().getId()).name(product.getIdCategory().getName()).status(product.getIdCategory().getStatus()).build() : null)
                .idCompany(product.getIdCompany() != null ?
                        CompanyAdminDTO.builder().id(product.getIdCompany().getId()).name(product.getIdCompany().getName()).status(product.getIdCompany().getStatus()).build() : null)
                .status(product.getStatus())
                .build()
        ).collect(Collectors.toList());
    }
    @Override
    public ProductAdminDTO getByID(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        return convertToDTO(product);
    }

    @Override
    public List<ProductAdminDTO> getAll() {
        List<Product> productList = productRepository.findAll();
        return convertToDTO(productList);
    }


    @Override
    public List<ProductAdminDTO> search(String keyword) {
        List<Product> productList = productRepository.findByName(keyword);
        if (productList.isEmpty()) {
            throw new EntityNotFoundException("Product not found");
        }
        return convertToDTO(productList);
    }

    @Override
    public ProductAdminDTO create(ProductAdminDTO productAdminDTO) {
        // Tạo mới sản phẩm
        Product product = new Product();
        product.setProductName(productAdminDTO.getName());
        product.setStatus(productAdminDTO.getStatus());

        // Lấy Category từ cơ sở dữ liệu
        Category category = catogoryRepository.findById(productAdminDTO.getIdCategory().getId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        product.setIdCategory(category);

        // Lấy Company từ cơ sở dữ liệu
        Company company = companyRepository.findById(productAdminDTO.getIdCompany().getId())
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));
        product.setIdCompany(company);

        // Lưu sản phẩm vào cơ sở dữ liệu
        product = productRepository.save(product);

        // Trả về DTO của sản phẩm vừa tạo
        return convertToDTO(product);
    }

    @Override
    public ProductAdminDTO update(Integer id, ProductAdminDTO productAdminDTO) {
        // Lấy sản phẩm từ cơ sở dữ liệu
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        // Cập nhật thông tin sản phẩm
        product.setProductName(productAdminDTO.getName());
        product.setStatus(productAdminDTO.getStatus());

        // Lấy Category từ cơ sở dữ liệu
        Category category = catogoryRepository.findById(productAdminDTO.getIdCategory().getId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        product.setIdCategory(category);

        // Lấy Company từ cơ sở dữ liệu
        Company company = companyRepository.findById(productAdminDTO.getIdCompany().getId())
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));
        product.setIdCompany(company);

        // Lưu sản phẩm đã cập nhật vào cơ sở dữ liệu
        product = productRepository.save(product);

        // Trả về DTO của sản phẩm đã cập nhật
        return convertToDTO(product);
    }

    @Override
    public String delete(Integer id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found"));

        productRepository.delete(product);

        return "Product deleted successfully";
    }

}
