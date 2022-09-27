package com.gihanz.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gihanz.dtos.CustomerDTO;
import lombok.*;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName;
    @Column(unique = true)
    private String nic;

    @JsonIgnore()
    public CustomerDTO getDto(){
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(this,customerDTO);
        return customerDTO;
    }
}
