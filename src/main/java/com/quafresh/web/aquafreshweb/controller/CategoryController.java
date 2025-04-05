package com.quafresh.web.aquafreshweb.controller;

import com.quafresh.web.aquafreshweb.dto.admin.CategoryAdminDTO;
import com.quafresh.web.aquafreshweb.service.admin.CategoryService;
import com.quafresh.web.aquafreshweb.service.impladmin.CategoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin/category")
@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
@AllArgsConstructor
public class CategoryController {

    private final CategoryImpl categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryAdminDTO>> getAllCategory(){
        return ResponseEntity.ok(categoryService.getCategoryList());
    }

    @GetMapping("/search")
    public ResponseEntity<List<CategoryAdminDTO>> searchCategory(@RequestParam String name){
        return ResponseEntity.ok(categoryService.searchCategory(name));
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryAdminDTO> getCategory(@PathVariable Integer id){
        return ResponseEntity.ok(categoryService.getCategory(id));
    }

    @PostMapping
    public ResponseEntity<CategoryAdminDTO> createCategory(@RequestBody CategoryAdminDTO categoryDTO){
        return ResponseEntity.ok(categoryService.addCategory(categoryDTO));
    }
    @PutMapping("/{id}")
    public ResponseEntity<CategoryAdminDTO> updateCategory(@PathVariable Integer id, @RequestBody CategoryAdminDTO categoryDTO){
        return ResponseEntity.ok(categoryService.updateCategory(id, categoryDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryAdminDTO> deleteCategory(@PathVariable Integer id){
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }
}
