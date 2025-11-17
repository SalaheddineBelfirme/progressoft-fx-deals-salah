package com.progresssoft.fx_deal_salah.controller;

import com.progresssoft.fx_deal_salah.dto.DealRequestDTO;
import com.progresssoft.fx_deal_salah.dto.DealResponseDTO;
import com.progresssoft.fx_deal_salah.service.contracts.IDealService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/deals")
public class DealController {

    @Autowired
    private IDealService dealService;

    @PostMapping
    public ResponseEntity<DealResponseDTO> createDeal(@Valid @RequestBody DealRequestDTO dealRequest) {
        DealResponseDTO response = dealService.createDeal(dealRequest);
        return ResponseEntity.ok(response);
    }


    
}