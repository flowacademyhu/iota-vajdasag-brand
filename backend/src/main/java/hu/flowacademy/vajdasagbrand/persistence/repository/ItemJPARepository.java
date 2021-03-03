package hu.flowacademy.vajdasagbrand.persistence.repository;

import hu.flowacademy.vajdasagbrand.dto.ItemDTO;
import hu.flowacademy.vajdasagbrand.persistence.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemJPARepository extends JpaRepository<Item, String> {

    Optional<Item> findFirstByIdAndDeletedAtNull(String id);

    List<ItemDTO> findByOwner_Id(String id);
}