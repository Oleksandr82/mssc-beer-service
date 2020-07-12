package tech.nautilus.msscbeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import tech.nautilus.msscbeerservice.services.BeerService;
import tech.nautilus.msscbeerservice.web.model.BeerDto;
import tech.nautilus.msscbeerservice.web.model.BeerStyle;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BeerController.class)
class MvcExceptionHandlerTest {

    BeerDto validBeer;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BeerService beerService;

    @BeforeEach
    public void setUp() {
        validBeer = BeerDto.builder()
                .beerName("IPA")
                .beerStyle(BeerStyle.ALE)
                .price(BigDecimal.valueOf(11.45))
                .upc("123456789")
                .quantityOnHand(1)
                .build();
    }

    @Test
    public void handlePostWithNotValidObject_shouldReturnBadRequest() throws Exception {
        //given
        BeerDto beerDto = validBeer;
        beerDto.setId(UUID.randomUUID());
        beerDto.setBeerName(null);
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(post("/api/v1/beer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(
                        "[\"beerDto.beerName: must not be blank\",\"beerDto.id: must be null\"]"));
    }
}