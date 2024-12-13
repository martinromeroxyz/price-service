package com.inditex.price.modules.infrastructure.web;

import com.inditex.price.model.PriceDTO;
import com.inditex.price.modules.domain.filter.PriceFilter;
import com.inditex.price.modules.domain.usecase.GetPriceUseCase;
import com.inditex.price.modules.infrastructure.error.ErrorHandler;
import com.inditex.price.modules.infrastructure.error.PriceException;
import com.inditex.price.modules.infrastructure.persistence.converter.PriceFilterDTOToPriceFilterConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith( MockitoExtension.class )
class PriceControllerTest {

    private MockMvc mockMvc;
    private static final String GET_PRICE_URL = "/price";

    @InjectMocks
    private PriceController priceController;

    @Mock
    private GetPriceUseCase getPriceUseCase;

    private PriceFilterDTOToPriceFilterConverter priceFilterDTOToPriceFilterConverter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.priceFilterDTOToPriceFilterConverter = new PriceFilterDTOToPriceFilterConverter();
        this.priceController = new PriceController(getPriceUseCase, priceFilterDTOToPriceFilterConverter);

        this.mockMvc = MockMvcBuilders.standaloneSetup( priceController )
                .setControllerAdvice(new ErrorHandler()).build();
    }

    @Test
    public void shouldReturnPriceWhenValidRequest() throws Exception {
        when(getPriceUseCase.getPrice(any(PriceFilter.class))).thenReturn(mockPriceDTO());
        mockMvc.perform(get(GET_PRICE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .param("date", "2020-06-14T21:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price", is(Double.valueOf("35.5"))));
    }

    @Test
    public void shouldThrowExceptionWhenNotFound() throws Exception {
        when(getPriceUseCase.getPrice(any(PriceFilter.class))).thenThrow(new PriceException("Product does not exist or there is not a price for this date"));

        mockMvc.perform(get(GET_PRICE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .param("date", "2020-06-14T21:00:00"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldThrowExceptionWhenIsBadRequest() throws Exception {
        mockMvc.perform(get(GET_PRICE_URL)).andExpect(status().isBadRequest()).andReturn();
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