package tech.nautilus.msscbeerservice.services.inventory;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import tech.nautilus.msscbeerservice.services.inventory.model.BeerInventoryDto;

import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@Component
public class BeerInventoryServiceFailoverFeignClientImpl implements BeerInventoryServiceFeignClient {

    private final BeerInventoryServiceFailoverFeignClient failoverFeignClient;

    @Override
    public ResponseEntity<List<BeerInventoryDto>> getOnHandInventory(UUID beerId) {
        return failoverFeignClient.getOnHandInventory();
    }
}
