package tech.nautilus.msscbeerservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import tech.nautilus.msscbeerservice.services.BeerService;

@SpringBootTest
class MsscBeerServiceApplicationTests {

    @MockBean
    BeerService beerService;

    @Test
    void contextLoads() {
    }

}
