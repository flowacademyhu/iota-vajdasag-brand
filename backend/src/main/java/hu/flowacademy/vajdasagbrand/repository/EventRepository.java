package hu.flowacademy.vajdasagbrand.repository;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import hu.flowacademy.vajdasagbrand.dto.EventDTO;
import hu.flowacademy.vajdasagbrand.persistence.entity.Event;
import hu.flowacademy.vajdasagbrand.persistence.repository.EventJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class EventRepository {

    public static final String PLACE = "places";
    public static final String EVENTS = "events";
    private final Firestore firestore;

    public EventDTO save(EventDTO eventDTO) {
        DocumentReference events = firestore.collection(EVENTS).document(eventDTO.getName().toLowerCase());
        DocumentReference places = events.collection(PLACE).document(eventDTO.getPlace().toLowerCase());
        Event event = Event.fromDTO(eventDTO);
        event.setId(UUID.randomUUID().toString());
        places.collection(EVENTS).document(event.getId()).set(event);
        return event.toDTO();
    }
}