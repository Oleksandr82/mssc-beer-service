package tech.nautilus.msscbeerservice.web.mappers;


import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import tech.nautilus.msscbeerservice.domain.Beer;
import tech.nautilus.brewery.model.BeerDto;

@Mapper(uses = {DateMapper.class})
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {

    @Mapping(target = "quantityOnHand", ignore = true)
    BeerDto beerToBeerDto(Beer beer);

//    @Mapping(target = "quantityOnHand", ignore = true)
    BeerDto beerToBeerDtoWithInventory(Beer beer);

    @Mappings({
            @Mapping(target = "minOnHand", ignore = true),
            @Mapping(target = "quantityToBrew", ignore = true)
    })
    Beer beerDtoToBeer(BeerDto dto);
}
