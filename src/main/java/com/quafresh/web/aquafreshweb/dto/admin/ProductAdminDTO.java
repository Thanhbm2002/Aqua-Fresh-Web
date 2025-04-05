package com.quafresh.web.aquafreshweb.dto.admin;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Data
public class ProductAdminDTO {
    private Integer id;
    private String name;
    private CategoryAdminDTO idCategory;
    private CompanyAdminDTO idCompany;
    private Boolean status;
}
