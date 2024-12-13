package com.inditex.price.modules.infrastructure.persistence.converter;

import com.inditex.price.model.PriceFilterDTO;
import com.inditex.price.modules.domain.filter.PriceFilter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PriceFilterDTOToPriceFilterConverter implements Converter<PriceFilterDTO, PriceFilter> {

    @Override
    public PriceFilter convert(PriceFilterDTO priceFilterDTO) {
        return PriceFilter.builder()
                .productId(priceFilterDTO.getProductId())
                .brandId(priceFilterDTO.getBrandId())
                .date(priceFilterDTO.getDate())
                .build();
    }
}
