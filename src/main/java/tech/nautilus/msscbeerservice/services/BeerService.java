package tech.nautilus.msscbeerservice.services;

import org.springframework.data.domain.PageRequest;
import tech.nautilus.brewery.model.BeerDto;
import tech.nautilus.brewery.model.BeerPagedList;
import tech.nautilus.brewery.model.BeerStyle;

import java.util.UUID;

public interface BeerService {

    BeerPagedList listBeers(String beerName, BeerStyle beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand);

    BeerDto getById(UUID beerId, Boolean showInventoryOnHand);

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto updateBeer(UUID beerId, BeerDto beerDto);

    BeerDto getByUpc(String upc);
}
