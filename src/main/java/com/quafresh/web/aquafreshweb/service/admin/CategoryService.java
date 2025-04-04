package com.quafresh.web.aquafreshweb.service.admin;

import com.quafresh.web.aquafreshweb.dto.admin.CategoryAdminDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {
    CategoryAdminDTO getCategory(Integer id);
     List<CategoryAdminDTO> getCategoryList();
     List<CategoryAdminDTO> searchCategory(String keyword);
     CategoryAdminDTO addCategory(CategoryAdminDTO category);
     CategoryAdminDTO updateCategory(Integer id,CategoryAdminDTO category);
     String deleteCategory(Integer id);
}
