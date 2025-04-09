package com.quafresh.web.aquafreshweb.controller;

import com.quafresh.web.aquafreshweb.dto.admin.ProductDetailAdminDTO;
import com.quafresh.web.aquafreshweb.service.impladmin.ProductDetailAdminImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/products-detail")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class ProductDetailAdminController {
    private final ProductDetailAdminImpl productDetailAdmin;

    @GetMapping
    public ResponseEntity<List<ProductDetailAdminDTO>> getAllProductDetails() {
        return ResponseEntity.ok(productDetailAdmin.getAll());
    }
    @GetMapping("search")
    public ResponseEntity<List<ProductDetailAdminDTO>> getProductDetailsByName(@RequestParam String name) {
        return ResponseEntity.ok(productDetailAdmin.search(name));
    }
    @PostMapping("/crate")
    public ResponseEntity<ProductDetailAdminDTO> createProductDetail(@RequestBody ProductDetailAdminDTO productDetailAdminDTO) {
        return ResponseEntity.ok(productDetailAdmin.create(productDetailAdminDTO));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ProductDetailAdminDTO> updateProductDetail(@PathVariable Integer id,@RequestBody ProductDetailAdminDTO productDetailAdminDTO) {
        return ResponseEntity.ok(productDetailAdmin.update(id,productDetailAdminDTO));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteProductDetail(@PathVariable Integer id) {
        productDetailAdmin.delete(id);
        return ResponseEntity.ok("Product deleted successfully");
    }
}
