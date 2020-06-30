package tech.nautilus.msscbeerservice.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tech.nautilus.msscbeerservice.domain.Beer;
import tech.nautilus.msscbeerservice.web.model.BeerDto;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

    @Mapping(target = "quantityOnHand", ignore = true)
    BeerDto beerToBeerDto(Beer beer);

    @Mapping(target = "minOnHand", ignore = true)
    @Mapping(target = "quantityToBrew", ignore = true)
    Beer beerDtoToBeer(BeerDto dto);
}
