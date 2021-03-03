package hu.flowacademy.vajdasagbrand.repository;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import hu.flowacademy.vajdasagbrand.dto.EventDTO;
import hu.flowacademy.vajdasagbrand.persistence.entity.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class EventRepository {
    
    public static final String EVENTS = "events";
    public static final String CITIES = "cities";
    public static final String LANGUAGES = "languages";
    private final Firestore firestore;

    public EventDTO save(EventDTO eventDTO) {
        DocumentReference places = firestore.collection(CITIES).document(eventDTO.getPlace().toLowerCase());
        DocumentReference lang = places.collection(LANGUAGES).document("hu");
        Event event = Event.fromDTO(eventDTO);
        event.setId(UUID.randomUUID().toString());
        lang.collection(EVENTS).document(event.getId()).set(event);
        return event.toDTO();
    }
}