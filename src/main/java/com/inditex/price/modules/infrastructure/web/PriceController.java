package com.inditex.price.modules.infrastructure.web;

import com.inditex.price.api.PriceApi;
import com.inditex.price.model.PriceDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PriceController implements PriceApi {

    @Override
    public ResponseEntity<PriceDTO> _getPrice(String productId, String brandId, String date) {
        return null;
    }
}
