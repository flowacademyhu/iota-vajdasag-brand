package hu.flowacademy.vajdasagbrand.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import hu.flowacademy.vajdasagbrand.dto.EventDTO;
import hu.flowacademy.vajdasagbrand.persistence.entity.Event;
import hu.flowacademy.vajdasagbrand.persistence.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Slf4j
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
        try {
            Query collection = firestore.collectionGroup(EVENTS);

            var query = pageable.getSort().stream().findFirst()
                    .map(order -> collection.orderBy(order.getProperty(),
                            order.isAscending() ? Query.Direction.ASCENDING : Query.Direction.DESCENDING))
                    .orElse(collection);

            return new PageImpl(query.limit(pageable.getPageSize()).offset((int) pageable.getOffset())
                    .get().get().getDocuments().stream()
                    .map(queryDocumentSnapshot -> queryDocumentSnapshot.toObject(Event.class))
                    .map(Event::toDTO)
                    .collect(Collectors.toList()));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return Page.empty();
        }
    }
}