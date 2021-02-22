package hu.flowacademy.vajdasagbrand.dto;

import hu.flowacademy.vajdasagbrand.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CegAdminItemDTO {

    private String address;
    private String city;
    private Category category;
}