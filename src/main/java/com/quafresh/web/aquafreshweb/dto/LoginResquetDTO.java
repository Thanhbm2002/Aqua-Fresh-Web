package com.quafresh.web.aquafreshweb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LoginResquetDTO {
    private String username;
    private String password;
    private String email;
    private String phone;
    private Boolean role;
    private String token;
}
