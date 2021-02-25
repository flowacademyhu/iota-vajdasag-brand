package hu.flowacademy.vajdasagbrand.dto;

import hu.flowacademy.vajdasagbrand.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuperAdminItemDTO extends CegAdminItemDTO {
    private String owner;

    public SuperAdminItemDTO(String id, String name, int score, String bio, String address, String city, Category category, String coordinateX, String coordinateY, String phone, String website, String facebook, String instagram, LocalDateTime deletedAt, String owner) {
        super(id, name, score, bio, address, city, category, coordinateX, coordinateY, phone, website, facebook, instagram, deletedAt);
        this.owner = owner;
    }
}