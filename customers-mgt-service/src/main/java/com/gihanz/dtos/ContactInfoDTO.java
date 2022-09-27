package com.gihanz.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gihanz.entities.ContactInfo;
import lombok.*;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ContactInfoDTO {

    private Long id;
    @Size(message = "Invalid phone number.",min = 10,max = 10)
    private String mobile;
    @Email(message = "invalid email address")
    private String email;
    @NotBlank(message = "Address can not be null.")
    @Size(message = "Invalid address characters length.",max = 100)
    private String address;

    @JsonIgnore()
    public ContactInfo getEntity(){
        ContactInfo info = new ContactInfo();
        BeanUtils.copyProperties(this,info);
        return info;
    }
}
