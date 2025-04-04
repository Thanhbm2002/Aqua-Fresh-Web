package com.quafresh.web.aquafreshweb.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class CustomerDTO {
    private int id;
    private String username;
    private String fullname;
    private String phone;
    private String email;
    private String address;
}
