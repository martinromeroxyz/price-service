package com.inditex.price.modules.infrastructure.persistence.repository;

import com.inditex.price.modules.domain.model.Brand;
import com.inditex.price.modules.domain.model.Price;
import com.inditex.price.modules.domain.repository.PriceRepository;
import com.inditex.price.modules.infrastructure.persistence.entity.PriceJpaEntity;
import com.inditex.price.modules.infrastructure.persistence.specification.PriceSpecification;
import com.inditex.price.modules.infrastructure.web.filter.PriceFilter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class PriceRepositoryImpl implements PriceRepository {

    private final PriceJpaRepository priceJpaRepository;

    @Override
    public List<Price> findPriceByProductIdAndBrandIdAndDate(PriceFilter priceFilter) {
        List<PriceJpaEntity> priceJpaEntity = priceJpaRepository.findAll(PriceSpecification.builder().priceFilter(priceFilter).build());
        return priceJpaEntity.stream().map(this::mapToDomain).toList();
    }

    private Price mapToDomain(PriceJpaEntity entity) {
        Brand brand = Brand.builder()
                .brandId(entity.getBrand().getId())
                .brandName(entity.getBrand().getBrand())
                .build();

        return Price.builder()
                .id(entity.getId())
                .brand(brand)
                .productId(entity.getProductId())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .price(entity.getPrice())
                .currency(entity.getCurrency())
                .priceList(entity.getPriceList())
                .priority(entity.getPriority())
                .build();
    }
}
