package com.quafresh.web.aquafreshweb.service.Impl;

import com.quafresh.web.aquafreshweb.dto.guess.OrderDetailClientDTO;
import com.quafresh.web.aquafreshweb.entity.OrderDetail;
import com.quafresh.web.aquafreshweb.entity.OrderTable;
import com.quafresh.web.aquafreshweb.entity.ProductDetail;
import com.quafresh.web.aquafreshweb.entity.User;
import com.quafresh.web.aquafreshweb.repositories.OrderDetailRepository;
import com.quafresh.web.aquafreshweb.repositories.OrderTableRepository;
import com.quafresh.web.aquafreshweb.repositories.ProductDetailRepository;
import com.quafresh.web.aquafreshweb.repositories.UserRepository;
import com.quafresh.web.aquafreshweb.service.guess.OderGuessService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OderGuessImpl implements OderGuessService {

    private final OrderTableRepository orderTableRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ProductDetailRepository productDetailRepository;
    private final UserRepository userRepository;


    @Transactional
    @Override
    public ResponseEntity<String> addOrder(OrderDetailClientDTO orderDetailClientDTO) {
        try {
            // B1: Lấy thông tin user
            Optional<User> user = userRepository.findById(orderDetailClientDTO.getIdUser());
            if (user.isEmpty()) {
                return ResponseEntity.badRequest().body("User not found");
            }

            // B2: Tạo và lưu OrderTable (chưa có total)
            OrderTable orderTable = new OrderTable();
            orderTable.setOrderDate(new Date().toInstant());
            orderTable.setIdUser(user.get());
            orderTable.setStatus(orderDetailClientDTO.getStatus());
            orderTable.setShippingPrice(BigDecimal.valueOf(123123)); // hoặc tính sau
            orderTable = orderTableRepository.save(orderTable); // cần lưu để có ID

            // B3: Tạo và lưu OrderDetail
            ProductDetail productDetail = productDetailRepository.findById(orderDetailClientDTO.getIdProductDetail())
                    .orElseThrow(() -> new RuntimeException("ProductDetail not found"));
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setIdOrder(orderTable);
            orderDetail.setIdProductDetail(productDetail);
            orderDetail.setPrice(orderDetailClientDTO.getPrice());
            orderDetail.setQuantity(orderDetailClientDTO.getQuantity());
            orderDetailRepository.save(orderDetail);

            // B4: Cập nhật tồn kho (trừ số lượng)
            int newQuantity = productDetail.getQuantity() - orderDetailClientDTO.getQuantity();
            productDetail.setQuantity(newQuantity);
            productDetailRepository.save(productDetail);

            // B5: Cập nhật total (sau khi orderDetail đã được lưu)
            BigDecimal total = updateTotalQuantity(orderTable.getId());
            orderTable.setTotal(total);
            orderTableRepository.save(orderTable);

            return ResponseEntity.ok("Order created successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to create order");
        }}

    @Override
    public BigDecimal updateTotalQuantity(Integer orderId) {
        List<OrderDetail> details = orderDetailRepository.findByIdOrder_Id(orderId);

        return details.stream()
                .map(detail -> detail.getPrice().multiply(BigDecimal.valueOf(detail.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public int getTotalQuantityByProductDetailId(Integer productDetailId) {
        List<OrderDetail> details = orderDetailRepository.findByIdProductDetail_Id(productDetailId);
        return details.stream().mapToInt(OrderDetail::getQuantity).sum();
    }
}
