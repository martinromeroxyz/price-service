package com.inditex.price.modules.domain.filter;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PriceFilter {

    private String productId;
    private String brandId;
    private LocalDateTime date;
}
