package com.inditex.price.modules.application;

import com.inditex.price.model.PriceDTO;
import com.inditex.price.modules.domain.model.Price;
import com.inditex.price.modules.domain.repository.PriceRepository;
import com.inditex.price.modules.domain.usecase.GetPriceUseCase;
import com.inditex.price.modules.infrastructure.error.PriceException;
import com.inditex.price.modules.infrastructure.persistence.converter.PriceToPriceDTOConverter;
import com.inditex.price.modules.domain.filter.PriceFilter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class GetPriceUseCaseImpl implements GetPriceUseCase {

        private final PriceRepository priceRepository;
        private final PriceToPriceDTOConverter priceToPriceDTOConverter;

        @Override
        public PriceDTO getPrice(PriceFilter filter) {
            List<Price> prices = priceRepository.findPriceByProductIdAndBrandIdAndDate(filter);

            return prices.stream()
                    .max(Comparator.comparing(Price::getPriority))
                    .map(priceToPriceDTOConverter::convert)
                    .orElseThrow(() -> new PriceException("Product does not exist or there is not a price for this date"));
        }
}
