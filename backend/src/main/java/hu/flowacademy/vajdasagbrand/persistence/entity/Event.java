package hu.flowacademy.vajdasagbrand.persistence.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import hu.flowacademy.vajdasagbrand.dto.EventDTO;
import hu.flowacademy.vajdasagbrand.dto.ItemDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@Table(name = "EventTable")
@Entity
public class Event {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String name;
    @Lob
    private String bio;
    @JsonFormat(pattern = ("yyyy.MM.dd HH:mm:ss"))
    private LocalDateTime eventstart;
    @JsonFormat(pattern = ("yyyy.MM.dd HH:mm:ss"))
    private LocalDateTime eventend;
    private String place;
    @ManyToOne
    private Item item;

    public static Event fromDTO(EventDTO eventDTO) {
        return Event.builder()
                .id(eventDTO.getId())
                .name(eventDTO.getName())
                .bio(eventDTO.getBio())
                .place(eventDTO.getPlace())
                .eventstart(eventDTO.getEventstart())
                .eventend(eventDTO.getEventend())
                .item(Item.builder().id(eventDTO.getItemId()).build())
                .build();
    }

    public EventDTO toDTO() {
        return EventDTO.builder()
                .id(id)
                .name(name)
                .bio(bio)
                .eventstart(eventstart)
                .eventend(eventend)
                .place(place)
                .itemId(item.getId())
                .build();
    }
}
