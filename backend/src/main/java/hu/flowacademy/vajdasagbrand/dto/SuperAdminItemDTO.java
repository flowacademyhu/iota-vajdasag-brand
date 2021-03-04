package hu.flowacademy.vajdasagbrand.dto;


import hu.flowacademy.vajdasagbrand.persistence.entity.Category;
import hu.flowacademy.vajdasagbrand.persistence.entity.Language;
import hu.flowacademy.vajdasagbrand.persistence.entity.Subcategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuperAdminItemDTO extends CegAdminItemDTO {
    private String ownerName;

    public SuperAdminItemDTO(String id, String name, String score, String bio, String address, String contact, String city, String email, Category category, Subcategory subcategory, String coordinateX, String coordinateY, String phone, String website, String facebook, String instagram, LocalDateTime deletedAt, String ownerId, String ownerName, Map<Language, SimpleItemDTO> language) {
        super(id, name, score, bio, address, contact, city, email, category, subcategory, coordinateX, coordinateY, phone, website, facebook, instagram, deletedAt, ownerId, language);
        this.ownerName = ownerName;
    }

}