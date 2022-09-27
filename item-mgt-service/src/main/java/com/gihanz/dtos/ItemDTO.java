package com.gihanz.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gihanz.entities.Item;
import lombok.*;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ItemDTO {

    private Long id;
    @NotBlank(message = "Item code can not blank.")
    @Size(max = 15,min = 4 ,message = "Your item code length must be between 4 to 15 characters.")
    private String itemCode;
    @NotBlank(message = "Item code can not blank.")
    @Size(max = 60 ,message = "Your item code max length 60 characters.")
    private String itemName;
    @Min(value = 0,message = "Item qty con not lese than 0.")
    private Double itemQty;
    @Min(value = 0,message = "Item price con not lese than 0.")
    private Double itemPrice;

    @JsonIgnore()
    public Item getEntity(){
        Item item = new Item();
        BeanUtils.copyProperties(this,item);
        return item;
    }
}
