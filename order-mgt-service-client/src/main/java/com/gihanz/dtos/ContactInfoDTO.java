package com.gihanz.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ContactInfoDTO {

    private Long id;
    private String mobile;
    private String email;
    private String address;
}
