package com.inditex.price.modules.infrastructure.persistence.converter;

import com.inditex.price.modules.domain.model.Brand;
import com.inditex.price.modules.domain.model.Price;
import com.inditex.price.modules.infrastructure.persistence.entity.PriceJpaEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PriceJpaEntityToPriceConverter implements Converter<PriceJpaEntity, Price> {

    @Override
    public Price convert(PriceJpaEntity source) {
        Brand brand = Brand.builder()
                .brandId(source.getBrand().getId())
                .brandName(source.getBrand().getBrand())
                .build();

        return Price.builder()
                .id(source.getId())
                .brand(brand)
                .productId(source.getProductId())
                .startDate(source.getStartDate())
                .endDate(source.getEndDate())
                .price(source.getPrice())
                .currency(source.getCurrency())
                .priceList(source.getPriceList())
                .priority(source.getPriority())
                .build();
    }
}