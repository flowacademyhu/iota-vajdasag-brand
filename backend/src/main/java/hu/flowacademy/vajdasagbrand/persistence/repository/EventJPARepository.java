package hu.flowacademy.vajdasagbrand.persistence.repository;

import hu.flowacademy.vajdasagbrand.persistence.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventJPARepository extends JpaRepository<Event, String> {
}
