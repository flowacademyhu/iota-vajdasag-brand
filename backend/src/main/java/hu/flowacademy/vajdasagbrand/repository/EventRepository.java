package hu.flowacademy.vajdasagbrand.repository;

import hu.flowacademy.vajdasagbrand.dto.EventDTO;
import hu.flowacademy.vajdasagbrand.persistence.entity.Event;
import hu.flowacademy.vajdasagbrand.persistence.repository.EventJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EventRepository {

    private final EventJPARepository eventRepository;

    public EventDTO save(EventDTO eventDTO) {
        return eventRepository.save(Event.fromEventDTO(eventDTO)).toEventDTO();
    }

    public Optional<EventDTO> findById(String id) {
        return eventRepository.findById(id).map(Event::toEventDTO);
    }
}