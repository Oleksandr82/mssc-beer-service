package tech.nautilus.msscbeerservice.services.brewing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.nautilus.brewery.model.BeerDto;
import tech.nautilus.brewery.model.events.BrewBeerEvent;
import tech.nautilus.brewery.model.events.NewInventoryEvent;
import tech.nautilus.msscbeerservice.domain.Beer;
import tech.nautilus.msscbeerservice.repositories.BeerRepository;

import static tech.nautilus.msscbeerservice.config.JmsConfig.BREWING_REQUEST_QUEUE;
import static tech.nautilus.msscbeerservice.config.JmsConfig.NEW_INVENTORY_QUEUE;

@Service
@RequiredArgsConstructor
@Slf4j
public class BrewBeerListener {

    private final BeerRepository beerRepository;
    private final JmsTemplate jmsTemplate;

    @Transactional
    @JmsListener(destination = BREWING_REQUEST_QUEUE)
    public void listen(BrewBeerEvent event) {
        BeerDto beerDto = event.getBeerDto();

        Beer beer = beerRepository.getOne(beerDto.getId());

        beerDto.setQuantityOnHand(beer.getQuantityToBrew());

        NewInventoryEvent newInventoryEvent = new NewInventoryEvent(beerDto);

        log.debug("Brewed beer: {}, QOH: {}", beerDto.getBeerName(), beerDto.getQuantityOnHand());

        jmsTemplate.convertAndSend(NEW_INVENTORY_QUEUE, newInventoryEvent);
    }
}
