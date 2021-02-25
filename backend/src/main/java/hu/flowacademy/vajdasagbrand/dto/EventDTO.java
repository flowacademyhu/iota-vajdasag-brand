package hu.flowacademy.vajdasagbrand.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {

    private String name;
    private String bio;
    @DateTimeFormat(pattern = "yyyy.MM.dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy.MM.dd HH:mm:ss")
    private LocalDateTime eventstart;
    @DateTimeFormat(pattern = "yyyy.MM.dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy.MM.dd HH:mm:ss")
    private LocalDateTime eventend;
    private String place;
    private String itemId;
}
