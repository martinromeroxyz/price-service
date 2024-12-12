package com.inditex.price.modules.domain.usecase;

import com.inditex.price.model.PriceDTO;
import com.inditex.price.modules.infrastructure.web.filter.PriceFilter;

public interface GetPriceUseCase {

    PriceDTO getPrice(PriceFilter filter);
}
