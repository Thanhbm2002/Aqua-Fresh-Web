package com.quafresh.web.aquafreshweb.service.admin;

import com.quafresh.web.aquafreshweb.dto.admin.ProductAdminDTO;
import com.quafresh.web.aquafreshweb.entity.Product;

import java.util.List;

public interface ProductAdminService {
    ProductAdminDTO getByID(Integer id);

    List<ProductAdminDTO> getAll();

    List<ProductAdminDTO> search(String keyword);

    ProductAdminDTO create(ProductAdminDTO productAdminDTO);

    ProductAdminDTO update(Integer id, ProductAdminDTO productAdminDTO);

    String delete(Integer id);
}
