package hu.flowacademy.vajdasagbrand.persistence.entity;

import com.google.cloud.Timestamp;
import hu.flowacademy.vajdasagbrand.util.TimestampConverter;
import hu.flowacademy.vajdasagbrand.dto.EventDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder(toBuilder = true)
@Data
public class Event {

    private String id;
    private String name;
    private String bio;
    private Timestamp eventstart;
    private Timestamp eventend;
    private String place;
    private String itemId;

    public static Event fromDTO(EventDTO eventDTO) {
        return Event.builder()
                .id(eventDTO.getId())
                .name(eventDTO.getName())
                .bio(eventDTO.getBio())
                .place(eventDTO.getPlace())
                .eventstart(TimestampConverter.toTimestamp(eventDTO.getEventstart()))
                .eventend(TimestampConverter.toTimestamp(eventDTO.getEventend()))
                .itemId(eventDTO.getItemId())
                .build();
    }

    public EventDTO toDTO() {
        return EventDTO.builder()
                .id(id)
                .name(name)
                .bio(bio)
                .eventstart(TimestampConverter.toLocalDateTime(eventstart))
                .eventend(TimestampConverter.toLocalDateTime(eventend))
                .place(place)
                .itemId(itemId)
                .build();
    }
}
