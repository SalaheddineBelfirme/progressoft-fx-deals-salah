package com.progresssoft.fx_deal_salah.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class DealRequestDTO {
    
    @NotBlank(message = "Deal ID is required")
    @Size(min = 3, max = 50, message = "Deal ID must be between 3 and 50 characters")
    private String dealUniqueId;
    
    @NotBlank(message = "From currency is required")
    @Pattern(regexp = "^[A-Z]{3}$", message = "From currency must be 3 uppercase letters (e.g., USD, EUR)")
    private String fromCurrency;
    
    @NotBlank(message = "To currency is required")
    @Pattern(regexp = "^[A-Z]{3}$", message = "To currency must be 3 uppercase letters (e.g., USD, EUR)")
    private String toCurrency;
    
    @NotNull(message = "Timestamp is required")
    @PastOrPresent(message = "Timestamp cannot be in the future")
    private LocalDateTime dealTimestamp;
    
    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    @DecimalMin(value = "0.01", message = "Amount must be at least 0.01")
    private Double dealAmount;
}