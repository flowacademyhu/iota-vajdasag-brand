package hu.flowacademy.vajdasagbrand.dto;


import hu.flowacademy.vajdasagbrand.configuration.persistence.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CegAdminItemDTO {
    private String id;
    private String address;
    private String city;
    private Category category;
}