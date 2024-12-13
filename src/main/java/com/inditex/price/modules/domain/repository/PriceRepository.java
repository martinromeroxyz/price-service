package com.inditex.price.modules.domain.repository;

import com.inditex.price.modules.domain.model.Price;
import com.inditex.price.modules.domain.filter.PriceFilter;

import java.util.List;

public interface PriceRepository {

    List<Price> findPriceByProductIdAndBrandIdAndDate(PriceFilter priceFilter);
}
