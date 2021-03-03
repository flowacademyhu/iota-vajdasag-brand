package hu.flowacademy.vajdasagbrand.persistence.entity;

import com.google.cloud.Timestamp;
import hu.flowacademy.vajdasagbrand.dto.EventDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.ZoneId;
import java.util.Date;

@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@Entity
public class Event {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String name;
    @Lob
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
                .eventstart(Timestamp.of(Date.from(eventDTO.getEventstart().atZone(ZoneId.systemDefault()).toInstant())))
                .eventend(Timestamp.of(Date.from(eventDTO.getEventend().atZone(ZoneId.systemDefault()).toInstant())))
                .itemId(eventDTO.getItemId())
                .build();
    }

    public EventDTO toDTO() {
        return EventDTO.builder()
                .id(id)
                .name(name)
                .bio(bio)
                .eventstart(eventstart.toDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                .eventend(eventend.toDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                .place(place)
                .itemId(itemId)
                .build();
    }
}
