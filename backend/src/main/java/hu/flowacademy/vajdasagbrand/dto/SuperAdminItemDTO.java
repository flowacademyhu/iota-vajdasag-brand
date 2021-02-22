package hu.flowacademy.vajdasagbrand.dto;

import hu.flowacademy.vajdasagbrand.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuperAdminItemDTO extends CegAdminItemDTO{
    private String name;

    public SuperAdminItemDTO(String address, String city, Category category, String name) {
        super(address, city, category);
        this.name = name;
    }
}