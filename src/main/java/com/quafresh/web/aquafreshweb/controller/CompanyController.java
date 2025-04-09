package com.quafresh.web.aquafreshweb.controller;

import com.quafresh.web.aquafreshweb.dto.admin.ColorAdminDtO;
import com.quafresh.web.aquafreshweb.dto.admin.CompanyAdminDTO;
import com.quafresh.web.aquafreshweb.service.impladmin.ColorImpl;
import com.quafresh.web.aquafreshweb.service.impladmin.CompanyImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin/companies")
@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
@AllArgsConstructor
public class CompanyController {
    private final CompanyImpl companyImpl;

    @GetMapping
    public ResponseEntity<List<CompanyAdminDTO>> getAll(){
        return ResponseEntity.ok(companyImpl.getAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<CompanyAdminDTO>> search(@RequestParam String name){
        return ResponseEntity.ok(companyImpl.search(name));
    }
    @GetMapping("detail/{id}")
    public ResponseEntity<CompanyAdminDTO> getById(@PathVariable Integer id){
        return ResponseEntity.ok(companyImpl.getByID(id));
    }

    @PostMapping("/crate")
    public ResponseEntity<CompanyAdminDTO> create(@RequestBody CompanyAdminDTO companyAdminDTO){
        return ResponseEntity.ok(companyImpl.create(companyAdminDTO));
    }
    @PutMapping("update/{id}")
    public ResponseEntity<CompanyAdminDTO> update(@PathVariable Integer id, @RequestBody CompanyAdminDTO companyAdminDTO){
        return ResponseEntity.ok(companyImpl.update(id, companyAdminDTO));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<CompanyAdminDTO> delete(@PathVariable Integer id){
        companyImpl.delete(id);
        return ResponseEntity.ok().build();
    }
}
