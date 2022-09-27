package com.gihanz.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ItemDTO {

    private Long id;
    private String itemCode;
    private String itemName;
    private Double itemQty;
    private Double itemPrice;

}
