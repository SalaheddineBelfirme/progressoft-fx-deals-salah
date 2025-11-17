package com.progresssoft.fx_deal_salah.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class DealResponseDTO {
    private String dealUniqueId;
    private String fromCurrency;
    private String toCurrency;
    private LocalDateTime dealTimestamp;
    private Double dealAmount;
    private String status;
    private String message;
}