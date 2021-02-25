package hu.flowacademy.vajdasagbrand.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import hu.flowacademy.vajdasagbrand.persistence.entity.Category;
import hu.flowacademy.vajdasagbrand.persistence.entity.Subcategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ItemDTO {

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
    private String web;
    private String instagram;
    private String facebook;
    @JsonFormat(pattern = ("yyyy.MM.dd HH:mm:ss"))
    private LocalDateTime deletedAt;
}