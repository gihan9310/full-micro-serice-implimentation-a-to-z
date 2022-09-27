package com.gihanz.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gihanz.dtos.ContactInfoDTO;
import lombok.*;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
public class ContactInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mobile;
    private String email;
    private String address;
    @OneToOne()
    @JoinColumn(referencedColumnName = "id",name = "customerId",unique = true)
    private Customer customer;

    @JsonIgnore()
    public ContactInfoDTO getDto(){
        ContactInfoDTO info = new ContactInfoDTO();
        BeanUtils.copyProperties(this,info);
        return info;
    }
}
