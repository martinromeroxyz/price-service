package com.inditex.price.modules.domain.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

@Data
@Builder
public class Price {

    private Long id;
    private Brand brand;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String priceList;
    private String productId;
    private Integer priority;
    private BigDecimal price;
    private Currency currency;
}
