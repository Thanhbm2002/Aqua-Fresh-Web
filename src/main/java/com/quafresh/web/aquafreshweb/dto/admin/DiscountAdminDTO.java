package com.quafresh.web.aquafreshweb.dto.admin;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Builder
@Data
public class DiscountAdminDTO {
    private Integer id;
    private String name;
    private BigDecimal discountValue;
    private Date startDate;
    private Date endDate;
    private String description;
    private Boolean isActive;
}
