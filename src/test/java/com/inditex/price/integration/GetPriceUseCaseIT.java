package com.inditex.price.integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.stream.Stream;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class GetPriceUseCaseIT {

    private static final String NON_EXISTING_PRODUCT_ID = "UNEXISTENT";

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    static Stream<Arguments> parameterizedArguments() {
        return Stream.of(
                Arguments.of("2020-06-14T10:00:00", "35.50"),
                Arguments.of("2020-06-14T16:00:00", "25.45"),
                Arguments.of("2020-06-14T21:00:00", "35.50"),
                Arguments.of("2020-06-15T10:00:00", "30.50"),
                Arguments.of("2020-06-16T21:00:00", "38.95")
        );
    }

    @ParameterizedTest
    @MethodSource("parameterizedArguments")
    public void shouldReturnCorrectPriceForGivenDate(String inputDate, String expectedPrice) throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(get("/price")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .param("date", inputDate)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.price", is(Double.valueOf(expectedPrice))));
    }

    @Test
    void shouldReturn404NotFoundWhenProductIdDoesNotExist() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(get("/price")
                        .param("productId", NON_EXISTING_PRODUCT_ID)
                        .param("brandId", "1")
                        .param("date", "2020-06-14T10:00:00"))
                .andExpect(status().isNotFound());
    }
}