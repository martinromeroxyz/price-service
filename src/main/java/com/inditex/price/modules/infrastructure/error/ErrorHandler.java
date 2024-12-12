package com.inditex.price.modules.infrastructure.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler({ PriceException.class })
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage priceException(Exception exception) {
        if (log.isDebugEnabled()) { log.debug(exception.getMessage(), exception); }
        return ErrorMessage.builder().error(exception.getMessage()).build();
    }

    @ExceptionHandler({ Exception.class })
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage internalServerErrorException(Exception exception) {
        if (log.isDebugEnabled()) { log.debug(exception.getMessage(), exception); }
        return ErrorMessage.builder().error(exception.getMessage()).build();
    }
}
