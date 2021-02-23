package hu.flowacademy.vajdasagbrand.repository;

import hu.flowacademy.vajdasagbrand.configuration.persistence.sql.entity.Item;
import hu.flowacademy.vajdasagbrand.dto.ItemDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommonItemRepository extends JpaRepository<ItemDTO, String> {

    Optional<ItemDTO> findFirstByIdAndDeletedAtNull(String id);

}
