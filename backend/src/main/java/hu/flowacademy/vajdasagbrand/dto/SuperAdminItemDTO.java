package hu.flowacademy.vajdasagbrand.dto;

import hu.flowacademy.vajdasagbrand.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuperAdminItemDTO extends CegAdminItemDTO{
    // TODO: create Owner property and add DB connection
    private String owner;

    public SuperAdminItemDTO(String address, String city, Category category, String owner) {
        super(address, city, category);
        this.owner = owner;
    }
}