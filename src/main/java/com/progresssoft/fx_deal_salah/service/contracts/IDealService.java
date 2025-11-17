package com.progresssoft.fx_deal_salah.service.contracts;

import com.progresssoft.fx_deal_salah.dto.DealRequestDTO;
import com.progresssoft.fx_deal_salah.dto.DealResponseDTO;

public interface IDealService {
    DealResponseDTO createDeal(DealRequestDTO dealRequest);
}