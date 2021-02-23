package hu.flowacademy.vajdasagbrand.configuration.persistence.sql.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@Table(name = "ItemTable")
public class Item {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String name;
    private String score;
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
