package hu.flowacademy.vajdasagbrand.dto;

import hu.flowacademy.vajdasagbrand.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuperAdminItemDTO extends CegAdminItemDTO{
    private String owner;

    public SuperAdminItemDTO(String id, String address, String city, Category category, String owner) {
        super(id, address, city, category);
        this.owner = owner;
    }
}