package com.gihanz.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gihanz.dtos.ItemDTO;
import lombok.*;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity( name = "ITEMS")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String itemCode;
    private String itemName;
    private Double itemQty;
    private Double itemPrice;

    @JsonIgnore()
    public ItemDTO getDto(){
        ItemDTO dto = new ItemDTO();
        BeanUtils.copyProperties(this,dto);
        return dto;
    }
}
