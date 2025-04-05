package com.quafresh.web.aquafreshweb.service.impladmin;

import com.quafresh.web.aquafreshweb.dto.admin.CategoryAdminDTO;
import com.quafresh.web.aquafreshweb.entity.Category;
import com.quafresh.web.aquafreshweb.repositories.CatogoryRepository;
import com.quafresh.web.aquafreshweb.service.admin.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryImpl implements CategoryService {

    private final CatogoryRepository catogoryRepository;

    @Override
    public CategoryAdminDTO getCategory(Integer id) {
        Category category = catogoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category not found"));
        return CategoryAdminDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .status(category.getStatus())
                .build();
    }

    @Override
    public List<CategoryAdminDTO> getCategoryList() {
        List<Category> categories = catogoryRepository.findAll();
        return categories.stream()
                .map(category -> CategoryAdminDTO.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .status(category.getStatus())
                        .build()) // Trả về đối tượng DTO
                .collect(Collectors.toList());  // Dùng collect để thu thập kết quả vào list
    }

    @Override
    public List<CategoryAdminDTO> searchCategory(String keyword) {
        List<Category> categories = catogoryRepository.findByName(keyword);
        if(categories.isEmpty()){
            throw new IllegalArgumentException("Category not found");
        }
        return categories.stream().map(category -> CategoryAdminDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .build())
                .collect(Collectors.toList());
    }

    @Override
    public CategoryAdminDTO addCategory(CategoryAdminDTO category) {
        Category categoryAdd = new Category();
        categoryAdd.setName(category.getName());
        categoryAdd.setStatus(category.isStatus());
        categoryAdd=catogoryRepository.save(categoryAdd);
        return CategoryAdminDTO.builder()
                .id(categoryAdd.getId())
                .name(categoryAdd.getName())
                .status(categoryAdd.getStatus())
                .build();
    }

    @Override
    public CategoryAdminDTO updateCategory(Integer id, CategoryAdminDTO category) {
        Category categoryUpdate = catogoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category not found"));
        categoryUpdate.setName(category.getName());
        categoryUpdate.setStatus(category.isStatus());
        categoryUpdate=catogoryRepository.save(categoryUpdate);
        return CategoryAdminDTO.builder()
                .id(categoryUpdate.getId())
                .name(categoryUpdate.getName())
                .status(categoryUpdate.getStatus())
                .build();
    }

    @Override
    public String deleteCategory(Integer id) {
        Category category = catogoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with ID: " + id));
        catogoryRepository.delete(category);
        return "Category with ID " + id + " has been deleted successfully.";
    }

}
