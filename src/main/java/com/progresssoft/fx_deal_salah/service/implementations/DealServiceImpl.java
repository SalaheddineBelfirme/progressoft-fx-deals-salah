package com.progresssoft.fx_deal_salah.service.implementations;

import com.progresssoft.fx_deal_salah.dto.DealRequestDTO;
import com.progresssoft.fx_deal_salah.dto.DealResponseDTO;
import com.progresssoft.fx_deal_salah.entity.Deal;
import com.progresssoft.fx_deal_salah.exceptions.DealAlreadyExistsException;
import com.progresssoft.fx_deal_salah.exceptions.InvalidDealDataException;
import com.progresssoft.fx_deal_salah.repository.DealRepository;
import com.progresssoft.fx_deal_salah.service.contracts.IDealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class DealServiceImpl implements IDealService {

    private static final Logger logger = LoggerFactory.getLogger(DealServiceImpl.class);

    @Autowired
    private DealRepository dealRepository;

    @Override
    public DealResponseDTO createDeal(DealRequestDTO dealRequest) {
        try {
            logger.info("Attempting to create deal with ID: {}", dealRequest.getDealUniqueId());
            
            // 1. تحقق من التكرار
            if (dealRepository.existsById(dealRequest.getDealUniqueId())) {
                logger.warn("Deal already exists with ID: {}", dealRequest.getDealUniqueId());
                throw new DealAlreadyExistsException("Deal with ID " + dealRequest.getDealUniqueId() + " already exists");
            }

            // 2. تحقق إضافي من العملات
            validateCurrencies(dealRequest.getFromCurrency(), dealRequest.getToCurrency());

            // 3. أنشئ الـ Entity واحفظه
            Deal deal = new Deal();
            deal.setDealUniqueId(dealRequest.getDealUniqueId());
            deal.setFromCurrency(dealRequest.getFromCurrency().toUpperCase());
            deal.setToCurrency(dealRequest.getToCurrency().toUpperCase());
            deal.setDealTimestamp(dealRequest.getDealTimestamp());
            deal.setDealAmount(dealRequest.getDealAmount());

            Deal savedDeal = dealRepository.save(deal);
            logger.info("Deal created successfully with ID: {}", savedDeal.getDealUniqueId());

            // 4. أنشئ الـ Response
            return createSuccessResponse(savedDeal, "Deal created successfully");

        } catch (DealAlreadyExistsException e) {
            logger.error("Deal creation failed - duplicate: {}", e.getMessage());
            throw e; // نعيد الـ Exception لنعالجها في الـ Controller
        } catch (Exception e) {
            logger.error("Unexpected error creating deal: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to create deal: " + e.getMessage());
        }
    }

    private void validateCurrencies(String fromCurrency, String toCurrency) {
        if (fromCurrency.equalsIgnoreCase(toCurrency)) {
            throw new InvalidDealDataException("From currency and To currency cannot be the same");
        }

    }

    private DealResponseDTO createSuccessResponse(Deal deal, String message) {
        DealResponseDTO response = new DealResponseDTO();
        response.setDealUniqueId(deal.getDealUniqueId());
        response.setFromCurrency(deal.getFromCurrency());
        response.setToCurrency(deal.getToCurrency());
        response.setDealTimestamp(deal.getDealTimestamp());
        response.setDealAmount(deal.getDealAmount());
        response.setStatus("SUCCESS");
        response.setMessage(message);
        return response;
    }
}