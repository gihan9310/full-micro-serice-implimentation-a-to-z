package com.gihanz.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerDTO {

    private Long id;
    private String customerName;
    private String nic;
    private ContactInfoDTO contactInfoDTO;
}
