package com.inditex.price.modules.infrastructure.persistence.repository;

import com.inditex.price.modules.domain.model.Price;
import com.inditex.price.modules.domain.repository.PriceRepository;
import com.inditex.price.modules.infrastructure.persistence.converter.PriceJpaEntityToPriceConverter;
import com.inditex.price.modules.infrastructure.persistence.entity.PriceJpaEntity;
import com.inditex.price.modules.infrastructure.persistence.specification.PriceSpecification;
import com.inditex.price.modules.domain.filter.PriceFilter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class PriceRepositoryImpl implements PriceRepository {

    private final PriceJpaRepository priceJpaRepository;
    private final PriceJpaEntityToPriceConverter priceJpaEntityToPriceConverter;

    @Override
    public List<Price> findPriceByProductIdAndBrandIdAndDate(PriceFilter priceFilter) {
        List<PriceJpaEntity> priceJpaEntity = priceJpaRepository.findAll(PriceSpecification.builder().priceFilter(priceFilter).build());
        return priceJpaEntity.stream().map(priceJpaEntityToPriceConverter::convert).toList();
    }
}
