package com.quafresh.web.aquafreshweb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class RegisterRequestDTO {
    private String message;
    private String username;
    private String email;
}
