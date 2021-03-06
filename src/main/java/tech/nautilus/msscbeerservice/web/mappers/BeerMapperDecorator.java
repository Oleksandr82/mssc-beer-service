package tech.nautilus.msscbeerservice.web.mappers;

import lombok.Setter;
import tech.nautilus.brewery.model.BeerDto;
import tech.nautilus.msscbeerservice.domain.Beer;
import tech.nautilus.msscbeerservice.services.inventory.BeerInventoryService;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class BeerMapperDecorator implements BeerMapper {

    private BeerInventoryService beerInventoryService;
    private BeerMapper mapper;

    @Autowired
    public void setBeerInventoryService(BeerInventoryService beerInventoryService) {
        this.beerInventoryService = beerInventoryService;
    }

    @Autowired
    public void setMapper(BeerMapper mapper) {
        this.mapper = mapper;
    }

//    @Override
//    public BeerDto beerToBeerDto(Beer beer) {
//        return mapper.beerToBeerDto(beer);
//    }

    @Override
    public BeerDto beerToBeerDtoWithInventory(Beer beer) {
        BeerDto dto = mapper.beerToBeerDto(beer);
        dto.setQuantityOnHand(beerInventoryService.getOnHandInventory(beer.getId()));
        return dto;
    }

//    @Override
//    public Beer beerDtoToBeer(BeerDto beerDto) {
//        return mapper.beerDtoToBeer(beerDto);
//    }
}