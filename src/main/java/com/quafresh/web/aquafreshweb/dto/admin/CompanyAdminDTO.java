package com.quafresh.web.aquafreshweb.dto.admin;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CompanyAdminDTO {
    private Integer id;
    private String name;
    private Boolean status;
}
