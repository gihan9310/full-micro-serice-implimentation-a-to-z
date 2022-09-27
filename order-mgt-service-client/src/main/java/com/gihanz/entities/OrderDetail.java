package com.gihanz.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gihanz.dtos.OrderDetailDto;
import com.gihanz.dtos.OrderDto;
import lombok.*;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity( name = "ORDER_DETAILS")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemCode;
    private String itemName;
    private Double qty;
    private Double price;
    @ManyToOne()
    @JoinColumn(referencedColumnName = "id",name = "orderId")
    private Order order;

    @JsonIgnore()
    public OrderDetailDto getDto(){
        OrderDetailDto orderDetailDto = new OrderDetailDto();
        BeanUtils.copyProperties(this,orderDetailDto);
        return orderDetailDto;
    }
}
