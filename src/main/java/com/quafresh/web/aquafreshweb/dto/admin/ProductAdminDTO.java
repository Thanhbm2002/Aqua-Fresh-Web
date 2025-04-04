package com.quafresh.web.aquafreshweb.dto.admin;

import lombok.Builder;

@Builder
public class ProductAdminDTO {
    private Integer id;
    private String name;
    private CategoryAdminDTO idCategory;
    private CompanyAdminDTO idCompany;
    private Boolean status;
}
