package com.progresssoft.fx_deal_salah.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private int status;
    private LocalDateTime timestamp;
    private String message;
    private String path;
    private Object details;
}