package hu.flowacademy.vajdasagbrand.dto;

import hu.flowacademy.vajdasagbrand.persistence.entity.Category;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CegAdminItemDTO {
    private String id;
    private String name;
    private String score;
    private String bio;
    private String address;
    private String city;
    private Category category;
    private String coordinateX;
    private String coordinateY;
    private String phone;
    private String website;
    private String facebook;
    private String instagram;
    @JsonFormat(pattern = ("yyyy.MM.dd HH:mm:ss"))
    private LocalDateTime deletedAt;
}