package com.quafresh.web.aquafreshweb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LoginRequestDTO {
    private Integer id;
    private String username;
    private String email;
    private String phone;
    private String address;
}
