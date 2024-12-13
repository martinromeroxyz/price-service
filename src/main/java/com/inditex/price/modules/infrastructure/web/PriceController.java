package com.inditex.price.modules.infrastructure.web;

import com.inditex.price.api.PriceApi;
import com.inditex.price.model.PriceDTO;
import com.inditex.price.model.PriceFilterDTO;
import com.inditex.price.modules.domain.usecase.GetPriceUseCase;
import com.inditex.price.modules.infrastructure.persistence.converter.PriceFilterDTOToPriceFilterConverter;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PriceController implements PriceApi {

    private final GetPriceUseCase getPriceUseCase;
    private final PriceFilterDTOToPriceFilterConverter priceFilterDTOToPriceFilterConverter;

    @Override
    public ResponseEntity<PriceDTO> _getPrice(PriceFilterDTO filter) {

        return ResponseEntity.ok(getPriceUseCase.getPrice(priceFilterDTOToPriceFilterConverter.convert(filter)));
    }
}