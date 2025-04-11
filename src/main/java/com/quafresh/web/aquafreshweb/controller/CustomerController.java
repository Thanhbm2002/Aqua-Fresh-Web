package com.quafresh.web.aquafreshweb.controller;

import com.quafresh.web.aquafreshweb.dto.admin.CustomerDTO;
import com.quafresh.web.aquafreshweb.service.Impl.CustomerImpl;
import com.quafresh.web.aquafreshweb.service.admin.CustomerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("admin/customers")
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
public class CustomerController {
    private final CustomerImpl customerImpl;

    @GetMapping
   public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return ResponseEntity.ok(customerImpl.getAll());
    }
    @GetMapping("/search")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers(@RequestParam String name) {
        return ResponseEntity.ok(customerImpl.search(name));
    }

    @PostMapping("/create")
    public ResponseEntity<CustomerDTO> crateCustomer(@RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.ok(customerImpl.create(customerDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Integer id, @RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.ok(customerImpl.update(id, customerDTO));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Integer id) {
        String response =customerImpl.delete(id);
        return ResponseEntity.ok(response);
    }
}
