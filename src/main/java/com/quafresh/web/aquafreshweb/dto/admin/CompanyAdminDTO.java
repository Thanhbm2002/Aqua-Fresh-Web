package com.quafresh.web.aquafreshweb.dto.admin;

import lombok.Builder;

@Builder
public class CompanyAdminDTO {
    private Integer id;
    private String name;
    private Boolean status;
}
