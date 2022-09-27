package com.gihanz.exceptions;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CustomExceptionResponse {
    private String customerNameError;
}
