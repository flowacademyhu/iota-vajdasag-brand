package hu.flowacademy.vajdasagbrand.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import hu.flowacademy.vajdasagbrand.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CegAdminItemDTO {

    private String id;
    private String name;
    private int score;
    @Lob
    private String bio;
    private String address;
    private String city;
    @Enumerated(EnumType.STRING)
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