package hu.flowacademy.vajdasagbrand.dto;

import hu.flowacademy.vajdasagbrand.persistence.entity.Category;
import com.fasterxml.jackson.annotation.JsonFormat;
import hu.flowacademy.vajdasagbrand.persistence.entity.Language;
import hu.flowacademy.vajdasagbrand.persistence.entity.Subcategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CegAdminItemDTO {
    private String id;
    private String name;
    private String score;
    private String bio;
    private String address;
    private String contact;
    private String city;
    private String email;
    private Category category;
    private Subcategory subcategory;
    private String coordinateX;
    private String coordinateY;
    private String phone;
    private String website;
    private String facebook;
    private String instagram;
    @JsonFormat(pattern = ("yyyy.MM.dd HH:mm:ss"))
    private LocalDateTime deletedAt;
    private String ownerId;
    private Map<Language, SimpleItemDTO> language;
}