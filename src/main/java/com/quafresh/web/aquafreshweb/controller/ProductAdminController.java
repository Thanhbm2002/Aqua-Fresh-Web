package com.quafresh.web.aquafreshweb.controller;

import com.quafresh.web.aquafreshweb.dto.admin.ProductAdminDTO;
import com.quafresh.web.aquafreshweb.service.admin.ProductAdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/products")
@PreAuthorize("hasRole('ROLE_ADMIN')")
@AllArgsConstructor
public class ProductAdminController {

    private final ProductAdminService productAdminService;

    // Lấy tất cả sản phẩm
    @GetMapping
    public ResponseEntity<List<ProductAdminDTO>> getAllProducts() {
        List<ProductAdminDTO> products = productAdminService.getAll();
        return ResponseEntity.ok(products);
    }

    // Tìm kiếm sản phẩm theo tên
    @GetMapping("/search")
    public ResponseEntity<List<ProductAdminDTO>> searchProducts(@RequestParam String keyword) {
        List<ProductAdminDTO> products = productAdminService.search(keyword);
        return ResponseEntity.ok(products);
    }

    // Lấy thông tin chi tiết sản phẩm theo ID
    @GetMapping("detail/{id}")
    public ResponseEntity<ProductAdminDTO> getProductById(@PathVariable Integer id) {
        ProductAdminDTO product = productAdminService.getByID(id);
        return ResponseEntity.ok(product);
    }

    // Tạo mới sản phẩm
    @PostMapping("/crate")
    public ResponseEntity<ProductAdminDTO> createProduct(@RequestBody ProductAdminDTO productAdminDTO) {
        ProductAdminDTO product = productAdminService.create(productAdminDTO);
        return ResponseEntity.ok(product);
    }

    // Cập nhật thông tin sản phẩm
    @PutMapping("update/{id}")
    public ResponseEntity<ProductAdminDTO> updateProduct(@PathVariable Integer id, @RequestBody ProductAdminDTO productAdminDTO) {
        ProductAdminDTO product = productAdminService.update(id, productAdminDTO);
        return ResponseEntity.ok(product);
    }

    // Xóa sản phẩm
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id) {
        String response = productAdminService.delete(id);
        return ResponseEntity.ok(response);
    }
}
