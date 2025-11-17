package com.progresssoft.fx_deal_salah.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "deals")
@Data
public class Deal {
    
    @Id
    @Column(name = "deal_unique_id", length = 50)
    private String dealUniqueId;
    
    @NotNull
    @Column(name = "from_currency", length = 3)  // تأكد أن الطول 3
    @Size(min = 3, max = 3, message = "From currency must be exactly 3 characters")
    private String fromCurrency;
    
    @NotNull
    @Column(name = "to_currency", length = 3)    // تأكد أن الطول 3
    @Size(min = 3, max = 3, message = "To currency must be exactly 3 characters")
    private String toCurrency;
    
    @NotNull
    @Column(name = "deal_timestamp")
    private LocalDateTime dealTimestamp;
    
    @NotNull
    @Column(name = "deal_amount")
    private Double dealAmount;
}