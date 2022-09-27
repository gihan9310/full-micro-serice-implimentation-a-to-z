package com.gihanz.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gihanz.entities.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerDTO {

    private Long id;
    @NotBlank(message = "Product code is required.")
    @Size(min = 1 ,max = 30,message = "Product code characters length must be match( min 1 max 30)")
    private String customerName;
    @NotBlank(message = "Nic number can not be empty")
    @Size(min = 10,max = 12,message = "Invalid nic number.")
    private String nic;
    @Valid
    private ContactInfoDTO contactInfoDTO;

    @JsonIgnore()
    public Customer getEntity(){
        Customer customer = new Customer();
        BeanUtils.copyProperties(this,customer);
        return customer;
    }
}
