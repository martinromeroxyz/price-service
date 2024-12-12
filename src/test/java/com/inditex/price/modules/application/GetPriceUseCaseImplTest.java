package com.inditex.price.modules.application;

import com.inditex.price.model.PriceDTO;
import com.inditex.price.modules.domain.model.Price;
import com.inditex.price.modules.domain.repository.PriceRepository;
import com.inditex.price.modules.infrastructure.error.PriceException;
import com.inditex.price.modules.infrastructure.web.converter.PriceToPriceDTOConverter;
import com.inditex.price.modules.infrastructure.web.filter.PriceFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class GetPriceUseCaseImplTest {

    @Mock
    private PriceRepository priceRepository;

    @Mock
    private PriceToPriceDTOConverter priceToPriceDTOConverter;

    @InjectMocks
    private GetPriceUseCaseImpl getPriceUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnPriceWhenPriceExists() {
        when(priceRepository.findPriceByProductIdAndBrandIdAndDate(mockPriceFilter())).thenReturn(List.of(Price.builder().build()));
        when(priceToPriceDTOConverter.convert(Price.builder().build())).thenReturn(mockPriceDTO());

        PriceDTO result = getPriceUseCase.getPrice(mockPriceFilter());

        assertNotNull(result);
        assertEquals(mockPriceDTO(), result);
    }

    @Test
    void shouldThrowExceptionWhenPriceDoesNotExist() {
        when(priceRepository.findPriceByProductIdAndBrandIdAndDate(PriceFilter.builder().build())).thenReturn(Collections.emptyList());

        assertThrows(PriceException.class, () -> getPriceUseCase.getPrice(PriceFilter.builder().build()));
    }

    private PriceFilter mockPriceFilter() {
        return PriceFilter.builder()
                .productId("35455")
                .brandId("1")
                .date(LocalDateTime.of(2020, 6, 14, 21, 0, 0))
                .build();
    }

    private PriceDTO mockPriceDTO() {
        return PriceDTO.builder()
                .productId("35455")
                .brandId("1")
                .priceList("1")
                .startDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0))
                .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                .price(new BigDecimal(35.50))
                .currency("EUR")
                .build();
    }
}