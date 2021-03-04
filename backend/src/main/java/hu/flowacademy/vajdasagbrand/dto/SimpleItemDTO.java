package hu.flowacademy.vajdasagbrand.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SimpleItemDTO {
    private String bio;
    private String name;
    private String website;
}
