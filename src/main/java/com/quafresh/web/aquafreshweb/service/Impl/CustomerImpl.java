package com.quafresh.web.aquafreshweb.service.Impl;

import com.quafresh.web.aquafreshweb.dto.admin.CustomerDTO;
import com.quafresh.web.aquafreshweb.entity.User;
import com.quafresh.web.aquafreshweb.repositories.UserRepository;
import com.quafresh.web.aquafreshweb.service.admin.CustomerService;
import com.quafresh.web.aquafreshweb.util.AdminMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerImpl implements CustomerService {
    private final UserRepository userRepository;
    private final AdminMapper adminMapper;
    @Override
    public List<CustomerDTO> getAll() {
        List<User> listCustomer = userRepository.findAllUsersByRole(false);
        return listCustomer.stream()
                .map(adminMapper::toCustomerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO create(CustomerDTO customerDTO) {
        User customer = new User();
        customer.setUsername(customerDTO.getUsername());
        customer.setPassword(customerDTO.getPassword());
        customer.setFullname(customerDTO.getFullname());
        customer.setPhone(customerDTO.getPhone());
        customer.setEmail(customerDTO.getEmail());
        customer.setAddress(customerDTO.getAddress());
        userRepository.save(customer);
        return adminMapper.toCustomerDTO(customer);
    }

    @Override
    public CustomerDTO update(Integer id, CustomerDTO customerDTO) {
        User customer = userRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Customer not found"));
        customer.setUsername(customerDTO.getUsername());
        customer.setPassword(customerDTO.getPassword());
        customer.setFullname(customerDTO.getFullname());
        customer.setPhone(customerDTO.getPhone());
        customer.setEmail(customerDTO.getEmail());
        customer.setAddress(customerDTO.getAddress());
        userRepository.save(customer);
        return adminMapper.toCustomerDTO(customer);
    }

    @Override
    public String delete(Integer id) {
        User customer = userRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Customer not found"));
        userRepository.delete(customer);
        return "Delete cussedly";
    }

    @Override
    public List<CustomerDTO> search(String keyword) {
      List<User> listCustomer = userRepository.searchUserByFullnameContainingIgnoreCase(false, keyword);
      return listCustomer.stream().map(adminMapper::toCustomerDTO).collect(Collectors.toList());
    }
}
