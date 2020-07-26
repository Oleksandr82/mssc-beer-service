package tech.nautilus.events;

import lombok.NoArgsConstructor;
import tech.nautilus.brewery.model.BeerDto;

@NoArgsConstructor
public class BrewBeerEvent extends BeerEvent {

    public BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
