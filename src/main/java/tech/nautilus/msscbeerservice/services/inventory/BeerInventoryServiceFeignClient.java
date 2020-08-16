package tech.nautilus.msscbeerservice.services.inventory;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tech.nautilus.msscbeerservice.services.inventory.model.BeerInventoryDto;

import java.util.List;
import java.util.UUID;

import static tech.nautilus.msscbeerservice.services.inventory.BeerInventoryService.INVENTORY_PATH;

@FeignClient(name = "inventory-service", fallback = BeerInventoryServiceFailoverFeignClientImpl.class)
public interface BeerInventoryServiceFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = INVENTORY_PATH)
    ResponseEntity<List<BeerInventoryDto>> getOnHandInventory(@PathVariable UUID beerId);
}
