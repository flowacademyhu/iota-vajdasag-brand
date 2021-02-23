package hu.flowacademy.vajdasagbrand.configuration.persistence.sql.repository;

import hu.flowacademy.vajdasagbrand.configuration.persistence.sql.entity.Item;
import hu.flowacademy.vajdasagbrand.dto.ItemDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<ItemDTO, String>{

    Optional<ItemDTO> findFirstByIdAndDeletedAtNull(String id);
}
