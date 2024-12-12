package com.inditex.price.modules.infrastructure.web.converter;

import com.inditex.price.model.PriceDTO;
import com.inditex.price.modules.domain.model.Price;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PriceToPriceDTOConverter implements Converter<Price, PriceDTO> {

    @Override
    public PriceDTO convert(Price source) {
        return PriceDTO.builder()
                .productId(source.getProductId())
                .brandId(source.getBrand().getBrandId().toString())
                .priceList(source.getPriceList())
                .startDate(source.getStartDate())
                .endDate(source.getEndDate())
                .price(source.getPrice())
                .currency(source.getCurrency().getCurrencyCode())
                .build();
    }
}
