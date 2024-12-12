package com.inditex.price.modules.infrastructure.error;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorMessage< T > {
    private T error;
}
