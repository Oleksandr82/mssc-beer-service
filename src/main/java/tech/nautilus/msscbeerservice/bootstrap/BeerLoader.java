package tech.nautilus.msscbeerservice.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tech.nautilus.msscbeerservice.domain.Beer;
import tech.nautilus.msscbeerservice.repositories.BeerRepository;

import java.math.BigDecimal;

// Disabled. It is initialized using SQL script
@Component
@RequiredArgsConstructor
public class BeerLoader implements CommandLineRunner {

    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";

    private final BeerRepository beerRepository;

    @Override
    public void run(String... args) {
        if (beerRepository.count() == 0) {
            loadBeerObjects();
        }
    }

    private void loadBeerObjects() {
        beerRepository.save(Beer.builder()
                .beerName("Mango Bobs")
                .beerStyle("IPA")
                .quantityToBrew(200)
                .minOnHand(12)
                .upc(BEER_1_UPC)
                .price(new BigDecimal("12.95"))
                .version(1L)
                .build());

        beerRepository.save(Beer.builder()
                .beerName("Galaxy Cat")
                .beerStyle("PALE_ALE")
                .quantityToBrew(200)
                .minOnHand(12)
                .upc(BEER_2_UPC)
                .price(new BigDecimal("11.95"))
                .version(1L)
                .build());

        beerRepository.save(Beer.builder()
                .beerName("No Hamers On The Bar")
                .beerStyle("PALE_ALE")
                .quantityToBrew(200)
                .minOnHand(12)
                .upc(BEER_3_UPC)
                .price(new BigDecimal("11.95"))
                .version(1L)
                .build());
    }
}
