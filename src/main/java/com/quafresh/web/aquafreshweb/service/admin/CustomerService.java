package com.quafresh.web.aquafreshweb.service.admin;

import com.quafresh.web.aquafreshweb.dto.admin.CustomerDTO;

import java.util.List;

public interface CustomerService {
    CustomerDTO create(CustomerDTO customerDTO);
    CustomerDTO update(Integer id,CustomerDTO customerDTO);
    String delete(Integer id);
    List<CustomerDTO> search(String keyword);
    List<CustomerDTO> getAll();

}
