package hu.flowacademy.vajdasagbrand.repository;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import hu.flowacademy.vajdasagbrand.dto.EventDTO;
import hu.flowacademy.vajdasagbrand.persistence.entity.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class EventRepository {
    
    public static final String EVENTS = "events";
    public static final String CITIES = "cities";
    public static final String LANGUAGES = "languages";
    private final Firestore firestore;

    public EventDTO save(EventDTO eventDTO) {
        Event event = Event.fromDTO(eventDTO);
        DocumentReference document = firestore.collection(CITIES).document(eventDTO.getPlace().toLowerCase())
                .collection(LANGUAGES).document("hu");
        if(Objects.isNull(event.getId())) {
            event.setId(UUID.randomUUID().toString());
        }
        document.collection(EVENTS).document(event.getId()).set(event);
        return event.toDTO();
    }

    public Page<EventDTO> findAllEvents(Pageable pageable) {
        return eventRepository.findAll(pageable).map(Event::toDTO);
    }
}