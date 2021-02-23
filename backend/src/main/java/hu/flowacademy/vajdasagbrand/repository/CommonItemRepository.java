package hu.flowacademy.vajdasagbrand.repository;

import hu.flowacademy.vajdasagbrand.dto.ItemDTO;
import java.util.Optional;


public interface CommonItemRepository {

    ItemDTO save(ItemDTO itemDTO);
    Optional<ItemDTO> findById(String id);
    Optional<ItemDTO> findFirstByIdAndDeletedAtNull(String id);

}
