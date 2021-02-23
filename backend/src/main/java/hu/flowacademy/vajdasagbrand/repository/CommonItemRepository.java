package hu.flowacademy.vajdasagbrand.repository;

import hu.flowacademy.vajdasagbrand.dto.ItemDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CommonItemRepository extends JpaRepository<ItemDTO, String> {

    Optional<ItemDTO> findFirstByIdAndDeletedAtNull(String id);

}
