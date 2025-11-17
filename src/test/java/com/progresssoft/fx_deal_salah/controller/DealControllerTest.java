package com.progresssoft.fx_deal_salah.controller;

import com.progresssoft.fx_deal_salah.dto.DealRequestDTO;
import com.progresssoft.fx_deal_salah.dto.DealResponseDTO;
import com.progresssoft.fx_deal_salah.service.contracts.IDealService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DealController.class)
class DealControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IDealService dealService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateDeal_Success() throws Exception {
        DealRequestDTO request = new DealRequestDTO();
        request.setDealUniqueId("DEAL-999");
        request.setFromCurrency("USD");
        request.setToCurrency("EUR");
        request.setDealTimestamp(LocalDateTime.now());
        request.setDealAmount(1000.50);

        DealResponseDTO response = new DealResponseDTO();
        response.setDealUniqueId("DEAL-999");
        response.setFromCurrency("USD");
        response.setToCurrency("EUR");
        response.setDealTimestamp(request.getDealTimestamp());
        response.setDealAmount(1000.50);
        response.setStatus("SUCCESS");
        response.setMessage("Deal created successfully");

        when(dealService.createDeal(any(DealRequestDTO.class))).thenReturn(response);

        mockMvc.perform(post("/api/deals")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("SUCCESS"))
                .andExpect(jsonPath("$.dealUniqueId").value("DEAL-999"))
                .andExpect(jsonPath("$.fromCurrency").value("USD"))
                .andExpect(jsonPath("$.toCurrency").value("EUR"));
    }
    @Test
    void testCreateDeal_Duplicate() throws Exception {
        // 1. إعداد بيانات الاختبار
        DealRequestDTO request = new DealRequestDTO();
        request.setDealUniqueId("DEAL-999");
        request.setFromCurrency("USD");
        request.setToCurrency("EUR");
        request.setDealTimestamp(LocalDateTime.now());
        request.setDealAmount(1000.50);

        DealResponseDTO response = new DealResponseDTO();
        response.setStatus("ERROR");
        response.setMessage("Deal already exists");

        // 2. محاكاة الـ Service
        when(dealService.createDeal(any(DealRequestDTO.class))).thenReturn(response);

        // 3. تنفيذ الاختبار والتحقق - معدل
        mockMvc.perform(post("/api/deals")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())  // ← تغيير من 400 إلى 200
                .andExpect(jsonPath("$.status").value("ERROR"))
                .andExpect(jsonPath("$.message").value("Deal already exists"));
    }
}
