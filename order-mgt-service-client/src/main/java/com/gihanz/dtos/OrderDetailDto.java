package com.gihanz.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gihanz.entities.Order;
import com.gihanz.entities.OrderDetail;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class OrderDetailDto {
    private Long id;
    private String itemCode;
    private String itemName;
    private Double qty;
    private Double price;
//    private OrderDto dto;

    @JsonIgnore()
    public OrderDetail getEntity(){
        OrderDetail orderDetail = new OrderDetail();
        BeanUtils.copyProperties(this,orderDetail);
        return orderDetail;
    }
    @JsonIgnore()
    public OrderDetail getEntity(Order order){
        OrderDetail orderDetail = new OrderDetail();
        BeanUtils.copyProperties(this,orderDetail);
        orderDetail.setOrder(order);
        return orderDetail;
    }
}
