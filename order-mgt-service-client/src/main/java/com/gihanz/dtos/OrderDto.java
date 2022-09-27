package com.gihanz.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gihanz.entities.Order;
import lombok.*;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class OrderDto {

    private Long id;
    private String invoiceNumber;
    private LocalDate orderDate;
    private Long customerId;
    private String customerNic;

    private List<OrderDetailDto> orderDetailDtoList;

    @JsonIgnore()
    public Order getEntity(){
        Order order = new Order();
        BeanUtils.copyProperties(this,order);
        return order;
    }
}
