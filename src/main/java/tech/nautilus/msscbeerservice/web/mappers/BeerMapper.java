package tech.nautilus.msscbeerservice.web.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tech.nautilus.msscbeerservice.domain.Beer;
import tech.nautilus.brewery.model.BeerDto;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

    @Mapping(target = "quantityOnHand", ignore = true)
    BeerDto beerToBeerDto(Beer beer);

    public BeerDto beerToBeerDtoWithInventory(Beer beer);

    @Mapping(target = "minOnHand", ignore = true)
    @Mapping(target = "quantityToBrew", ignore = true)
    Beer beerDtoToBeer(BeerDto dto);
}
