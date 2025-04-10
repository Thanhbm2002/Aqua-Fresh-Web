package com.quafresh.web.aquafreshweb.service.admin;

import com.quafresh.web.aquafreshweb.dto.admin.ProductDetailAdminDTO;

import java.util.List;

public interface ProductDetailAdminService {
    ProductDetailAdminDTO getByID(Integer id);

    List<ProductDetailAdminDTO> getAll();

    List<ProductDetailAdminDTO> search(String keyword);

    ProductDetailAdminDTO create(ProductDetailAdminDTO productAdminDTO);

    ProductDetailAdminDTO update(Integer id, ProductDetailAdminDTO productAdminDTO);

    String delete(Integer id);
}
