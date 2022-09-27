package com.gihanz.exceptions;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class OrderExceptionResponse {
    private String customerNameError;
}
