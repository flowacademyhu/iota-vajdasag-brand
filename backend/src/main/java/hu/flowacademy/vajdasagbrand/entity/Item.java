package hu.flowacademy.vajdasagbrand.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "ItemTable")
public class Item {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String name;
    private int score;
    private String bio;
    private String address;
    private String city;
    private Category category;
    private String coordinate_x;
    private String coordinate_y;
    private String phone;
    private String website;
    private String facebook;
    private String instagram;
}
