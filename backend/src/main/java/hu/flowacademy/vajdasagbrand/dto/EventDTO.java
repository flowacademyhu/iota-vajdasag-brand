package hu.flowacademy.vajdasagbrand.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {

    private String name;
    private String bio;
    @NotNull
    @DateTimeFormat(pattern = "yyyy.MM.dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy.MM.dd hh:mm:ss")
    private LocalDateTime eventstart;
    @NotNull
    @DateTimeFormat(pattern = "yyyy.MM.dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy.MM.dd hh:mm:ss")
    private LocalDateTime eventend;
    private String place;
}
