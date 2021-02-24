package hu.flowacademy.vajdasagbrand.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {

    private String name;
    private String bio;
    private LocalDateTime programstart;
    private LocalDateTime programend;
    private String place;
}
