package com.gihanz.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gihanz.dtos.OrderDto;
import lombok.*;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity( name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String invoiceNumber;
    private LocalDate orderDate;
    @Column(nullable = false)
    private Long customerId;
    @Column(nullable = false)
    private String customerNic;

    @JsonIgnore()
    public OrderDto getDto(){
        OrderDto order = new OrderDto();
        BeanUtils.copyProperties(this,order);
        return order;
    }

}
