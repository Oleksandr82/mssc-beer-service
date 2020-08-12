package tech.nautilus.msscbeerservice.services.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tech.nautilus.brewery.model.BeerOrderDto;
import tech.nautilus.msscbeerservice.repositories.BeerRepository;

import java.util.function.Predicate;

@Slf4j
@RequiredArgsConstructor
@Component
public class BeerOrderValidator {

    private final BeerRepository beerRepository;

    public Boolean validateOrder(BeerOrderDto beerOrderDto) {

        return beerOrderDto.getBeerOrderLines().stream()
                .map(beerOrderLineDto -> beerRepository.findByUpc(beerOrderLineDto.getUpc()) != null)
                .allMatch(Predicate.isEqual(true));

//        AtomicInteger beersNotFound = new AtomicInteger();
//        beerOrderDto.getBeerOrderLines().forEach(beerOrderLineDto -> {
//            if (beerRepository.findByUpc(beerOrderLineDto.getUpc()) == null) {
//                beersNotFound.incrementAndGet();
//            }
//        });
//        return beersNotFound.get() == 0;
    }
}
