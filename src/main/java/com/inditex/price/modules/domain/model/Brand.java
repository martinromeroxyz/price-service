package com.inditex.price.modules.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Brand {

    private Long brandId;
    private String brandName;
}
