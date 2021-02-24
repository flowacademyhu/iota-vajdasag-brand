package hu.flowacademy.vajdasagbrand.repository;

import hu.flowacademy.vajdasagbrand.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, String> {
}
