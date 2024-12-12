package com.inditex.price.modules.infrastructure.web.controller;

import com.inditex.price.api.PriceApi;
import com.inditex.price.model.PriceDTO;
import com.inditex.price.modules.domain.usecase.GetPriceUseCase;
import com.inditex.price.modules.infrastructure.web.filter.PriceFilter;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
public class PriceController implements PriceApi {

    private GetPriceUseCase getPriceUseCase;

    @Override
    public ResponseEntity<PriceDTO> _getPrice(String productId, String brandId, LocalDateTime date) {

        return ResponseEntity.ok(getPriceUseCase.getPrice(PriceFilter.builder().productId(productId).brandId(brandId).date(date).build()));
    }
}
