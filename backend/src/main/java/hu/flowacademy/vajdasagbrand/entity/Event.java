package hu.flowacademy.vajdasagbrand.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EventTable")
public class Event {

    private String name;
    @Lob
    private String bio;
    @JsonFormat(pattern = ("yyyy.MM.dd HH:mm:ss"))
    private LocalDateTime programstart;
    @JsonFormat(pattern = ("yyyy.MM.dd HH:mm:ss"))
    private LocalDateTime programend;
    private String place;
}
