package com.gihanz.exceptions;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ItemExceptionResponse {
    private String customerNameError;
}
