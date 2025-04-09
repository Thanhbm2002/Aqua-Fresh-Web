package com.quafresh.web.aquafreshweb.controller;

import com.quafresh.web.aquafreshweb.dto.admin.ColorAdminDtO;
import com.quafresh.web.aquafreshweb.service.impladmin.ColorImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin/color")
@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
@AllArgsConstructor
public class ColorController {
    private final ColorImpl colorService;

    @GetMapping
    public ResponseEntity<List<ColorAdminDtO>> getAll(){
        return ResponseEntity.ok(colorService.getColorList());
    }

    @GetMapping("/search")
    public ResponseEntity<List<ColorAdminDtO>> search(@RequestParam String name){
        return ResponseEntity.ok(colorService.searchColor(name));
    }
    @GetMapping("detail/{id}")
    public ResponseEntity<ColorAdminDtO> getById(@PathVariable Integer id){
        return ResponseEntity.ok(colorService.getColor(id));
    }

    @PostMapping
    public ResponseEntity<ColorAdminDtO> create(@RequestBody ColorAdminDtO colorAdminDtO){
        return ResponseEntity.ok(colorService.addColor(colorAdminDtO));
    }
    @PutMapping("update/{id}")
    public ResponseEntity<ColorAdminDtO> update(@PathVariable Integer id, @RequestBody ColorAdminDtO colorAdminDtO){
        return ResponseEntity.ok(colorService.updateColor(id, colorAdminDtO));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ColorAdminDtO> delete(@PathVariable Integer id){
        colorService.deleteColor(id);
        return ResponseEntity.ok().build();
    }
}
