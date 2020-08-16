package tech.nautilus.msscbeerservice.services.inventory;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tech.nautilus.msscbeerservice.services.inventory.model.BeerInventoryDto;

import java.util.List;

@FeignClient(name = "inventory-failover-service")
public interface BeerInventoryServiceFailoverFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/inventory-failover")
    ResponseEntity<List<BeerInventoryDto>> getOnHandInventory();
}
