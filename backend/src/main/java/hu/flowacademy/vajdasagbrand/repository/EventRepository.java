package hu.flowacademy.vajdasagbrand.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import hu.flowacademy.vajdasagbrand.dto.EventDTO;
import hu.flowacademy.vajdasagbrand.persistence.entity.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;
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

    public Page<EventDTO> findAllEvents(Pageable pageable, Optional<String> itemId) {
        try {
            Query collection = firestore.collectionGroup(EVENTS);
            var query = pageable.getSort().stream().findFirst()
                    .map(order -> itemId.map(s -> collection.whereEqualTo("itemId", s)).orElse(collection).orderBy(order.getProperty(),
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

    public Optional<EventDTO> findById(String id) {
        ApiFuture<QuerySnapshot> collections = firestore.collectionGroup(EVENTS).whereEqualTo("id", id).get();
        try {
            return collections.get().getDocuments().stream()
                    .map(queryDocumentSnapshot -> queryDocumentSnapshot.toObject(Event.class))
                    .map(Event::toDTO)
                    .findFirst();
        } catch (InterruptedException | ExecutionException e) {
            log.error("No event found given id");
            return Optional.empty();
        }
    }
}