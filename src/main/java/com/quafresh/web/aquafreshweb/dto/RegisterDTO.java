package com.quafresh.web.aquafreshweb.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterDTO {
    private String username;
    private String password;
    private String fullname;
    private String email;
    private String phone;
}
