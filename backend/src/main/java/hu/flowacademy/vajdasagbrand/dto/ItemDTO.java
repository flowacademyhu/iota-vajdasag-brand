package hu.flowacademy.vajdasagbrand.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import hu.flowacademy.vajdasagbrand.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {

    private String name;
    private int score;
    private String bio;
    private String address;
    private String city;
    private String email;
    private Category category;
    private String coordinateX;
    private String coordinateY;
    private String phone;
    private String website;
    private String facebook;
    private String instagram;
}