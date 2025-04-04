package com.quafresh.web.aquafreshweb.dto.admin;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ColorAdminDtO {
    private Integer id;
    private String name;
    private boolean status;
}
