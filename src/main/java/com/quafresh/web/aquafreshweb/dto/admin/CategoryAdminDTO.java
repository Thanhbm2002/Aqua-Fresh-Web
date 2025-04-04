package com.quafresh.web.aquafreshweb.dto.admin;

import lombok.Builder;
import lombok.Getter;



@Getter
@Builder
public class CategoryAdminDTO {
    private Integer id;
    private String name;
    private Boolean status;
}
