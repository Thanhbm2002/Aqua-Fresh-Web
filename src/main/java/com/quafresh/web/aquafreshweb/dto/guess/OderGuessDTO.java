package com.quafresh.web.aquafreshweb.dto.guess;

import com.quafresh.web.aquafreshweb.entity.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OderGuessDTO {
    private BigDecimal price;
    private Integer quantity;
    private Integer idProductDetail;
    private Integer idUser;
    private BigDecimal shippingPrice;
    private BigDecimal total;
    private String status;
    private Integer idOrder;
    private Instant orderDate;

    public OderGuessDTO(OrderDetail orderDetail) {
        this.price = orderDetail.getPrice();
        this.quantity = orderDetail.getQuantity();
        this.idProductDetail = orderDetail.getIdProductDetail().getId();
        this.idUser = orderDetail.getIdOrder().getIdUser().getId();
        this.shippingPrice = orderDetail.getIdOrder().getShippingPrice();
        this.total = orderDetail.getIdOrder().getTotal();
        this.status = orderDetail.getIdOrder().getStatus();
        this.idOrder = orderDetail.getIdOrder().getId();
        this.orderDate = orderDetail.getIdOrder().getOrderDate();
    }
}

