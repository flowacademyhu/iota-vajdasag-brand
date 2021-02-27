package hu.flowacademy.vajdasagbrand.dto;


import hu.flowacademy.vajdasagbrand.persistence.entity.Category;
import hu.flowacademy.vajdasagbrand.persistence.entity.Subcategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuperAdminItemDTO extends CegAdminItemDTO {
    private String owner;

    public SuperAdminItemDTO(String id, String name, String score, String bio, String address, String contact, String city, String email, Category category, Subcategory subcategory, String coordinateX, String coordinateY, String phone, String website, String facebook, String instagram, LocalDateTime deletedAt, String owner) {
        super(id, name, score, bio, address, contact, city, email, category, subcategory, coordinateX, coordinateY, phone, website, facebook, instagram, deletedAt);
        this.owner = owner;
    }
}