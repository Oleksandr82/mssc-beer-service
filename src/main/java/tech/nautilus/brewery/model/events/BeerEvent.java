package tech.nautilus.brewery.model.events;

import lombok.*;
import tech.nautilus.brewery.model.BeerDto;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BeerEvent implements Serializable {

    static final long serialVersionUID = 3994880345303200719L;

    private BeerDto beerDto;
}
