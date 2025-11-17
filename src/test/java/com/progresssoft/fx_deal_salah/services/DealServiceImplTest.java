package com.progresssoft.fx_deal_salah.services;

import com.progresssoft.fx_deal_salah.dto.DealRequestDTO;
import com.progresssoft.fx_deal_salah.dto.DealResponseDTO;
import com.progresssoft.fx_deal_salah.entity.Deal;
import com.progresssoft.fx_deal_salah.exceptions.DealAlreadyExistsException;
import com.progresssoft.fx_deal_salah.repository.DealRepository;
import com.progresssoft.fx_deal_salah.service.implementations.DealServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DealServiceImplTest {

    @Mock
    private DealRepository dealRepository;

    @InjectMocks
    private DealServiceImpl dealService;

    @Test
    void testCreateDeal_Success() {
    
        DealRequestDTO request = new DealRequestDTO();
        request.setDealUniqueId("DEAL-123");
        request.setFromCurrency("USD");
        request.setToCurrency("EUR");
        request.setDealTimestamp(LocalDateTime.now());
        request.setDealAmount(1000.50);

        Deal savedDeal = new Deal();
        savedDeal.setDealUniqueId("DEAL-123");
        savedDeal.setFromCurrency("USD");
        savedDeal.setToCurrency("EUR");
        savedDeal.setDealTimestamp(request.getDealTimestamp());
        savedDeal.setDealAmount(1000.50);

        when(dealRepository.existsById("DEAL-123")).thenReturn(false);
        when(dealRepository.save(any(Deal.class))).thenReturn(savedDeal);

        DealResponseDTO response = dealService.createDeal(request);

        assertNotNull(response);
        assertEquals("SUCCESS", response.getStatus());
        assertEquals("DEAL-123", response.getDealUniqueId());
        assertEquals("USD", response.getFromCurrency());
        assertEquals("EUR", response.getToCurrency());
        
        verify(dealRepository, times(1)).existsById("DEAL-123");
        verify(dealRepository, times(1)).save(any(Deal.class));
    }

    @Test
    void testCreateDeal_Duplicate() {
        DealRequestDTO request = new DealRequestDTO();
        request.setDealUniqueId("DEAL-123");
        request.setFromCurrency("USD");
        request.setToCurrency("EUR");
        request.setDealTimestamp(LocalDateTime.now());
        request.setDealAmount(1000.50);

        when(dealRepository.existsById("DEAL-123")).thenReturn(true);

        DealAlreadyExistsException exception = assertThrows(
            DealAlreadyExistsException.class,
            () -> dealService.createDeal(request)
        );

        assertTrue(exception.getMessage().contains("already exists"));
        
        verify(dealRepository, never()).save(any(Deal.class));
    }
}