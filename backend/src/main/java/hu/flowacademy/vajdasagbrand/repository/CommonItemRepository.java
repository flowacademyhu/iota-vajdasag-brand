package hu.flowacademy.vajdasagbrand.repository;

import hu.flowacademy.vajdasagbrand.dto.CegAdminItemDTO;
import hu.flowacademy.vajdasagbrand.dto.ItemDTO;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;


public interface CommonItemRepository {

    ItemDTO save(ItemDTO itemDTO);
    Optional<ItemDTO> findById(String id);
    Optional<ItemDTO> findFirstById(String id);
    List<ItemDTO> findAll();

}
