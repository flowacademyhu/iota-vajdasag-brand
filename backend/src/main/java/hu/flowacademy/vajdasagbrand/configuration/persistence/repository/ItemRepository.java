package hu.flowacademy.vajdasagbrand.configuration.persistence.repository;

import hu.flowacademy.vajdasagbrand.configuration.persistence.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {

    Optional<Item> findFirstByIdAndDeletedAtNull(String id);
}