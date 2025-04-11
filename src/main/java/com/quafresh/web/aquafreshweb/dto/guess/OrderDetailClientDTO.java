package com.quafresh.web.aquafreshweb.dto.guess;

import com.quafresh.web.aquafreshweb.entity.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailClientDTO {
    private Integer id;
    private BigDecimal price;
    private Integer quantity;
    private Integer idProductDetail;
    private Integer idUser;
    private String status;
    public OrderDetailClientDTO(OrderDetail orderDetail) {
        this.id = orderDetail.getId();
        this.price = orderDetail.getPrice();
        this.quantity = orderDetail.getQuantity();
        this.idProductDetail = orderDetail.getIdProductDetail().getId();
        this.idUser = orderDetail.getIdOrder().getIdUser().getId();
        this.status = orderDetail.getIdOrder().getStatus();
    }
}
