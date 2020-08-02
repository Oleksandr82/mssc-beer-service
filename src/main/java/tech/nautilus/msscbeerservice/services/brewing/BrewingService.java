package tech.nautilus.msscbeerservice.services.brewing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tech.nautilus.brewery.model.events.BrewBeerEvent;
import tech.nautilus.msscbeerservice.domain.Beer;
import tech.nautilus.msscbeerservice.repositories.BeerRepository;
import tech.nautilus.msscbeerservice.services.inventory.BeerInventoryService;
import tech.nautilus.msscbeerservice.web.mappers.BeerMapper;

import java.util.List;

import static tech.nautilus.msscbeerservice.config.JmsConfig.BREWING_REQUEST_QUEUE;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrewingService {

    private final BeerRepository beerRepository;
    private final BeerInventoryService beerInventoryService;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper beerMapper;

    @Scheduled(fixedRate = 5000)
    public void checkForLowInventory() {
        List<Beer> beer = beerRepository.findAll();

        beer.stream()
                .map(b -> Pair.of(b, beerInventoryService.getOnHandInventory(b.getId())))
                .filter(p -> p.getFirst().getMinOnHand() > p.getSecond())
                .forEach(p -> {
                    log.debug("Beer {}, minOnHand: {}, actual: {}",
                            p.getFirst().getId(), p.getFirst().getMinOnHand(), p.getSecond());
                    jmsTemplate.convertAndSend(BREWING_REQUEST_QUEUE,
                            new BrewBeerEvent(beerMapper.beerToBeerDto(p.getFirst())));
                });
    }

}
