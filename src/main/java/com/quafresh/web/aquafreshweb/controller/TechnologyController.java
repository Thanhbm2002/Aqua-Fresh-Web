package com.quafresh.web.aquafreshweb.controller;

import com.quafresh.web.aquafreshweb.dto.admin.TechnologyAdminDTO;
import com.quafresh.web.aquafreshweb.service.impladmin.TechnologyImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin/technologies")
@RestController
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
@AllArgsConstructor
public class TechnologyController {

    private final TechnologyImpl technologyService;

    @GetMapping
    public ResponseEntity<List<TechnologyAdminDTO>> getAllTechnologies() {
        return ResponseEntity.ok(technologyService.getAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<TechnologyAdminDTO>> searchTechnology(@RequestParam String name) {
        return ResponseEntity.ok(technologyService.search(name));
    }

    @GetMapping("detail/{id}")
    public ResponseEntity<TechnologyAdminDTO> getTechnology(@PathVariable Integer id) {
        return ResponseEntity.ok(technologyService.getByID(id));
    }

    @PostMapping("/create")
    public ResponseEntity<TechnologyAdminDTO> createTechnology(@RequestBody TechnologyAdminDTO technologyDTO) {
        return ResponseEntity.ok(technologyService.create(technologyDTO));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<TechnologyAdminDTO> updateTechnology(@PathVariable Integer id, @RequestBody TechnologyAdminDTO technologyDTO) {
        return ResponseEntity.ok(technologyService.update(id, technologyDTO));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteTechnology(@PathVariable Integer id) {
        technologyService.delete(id);
        return ResponseEntity.ok().build();
    }
}
