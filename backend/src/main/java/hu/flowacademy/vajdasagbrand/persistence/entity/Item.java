package hu.flowacademy.vajdasagbrand.persistence.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import hu.flowacademy.vajdasagbrand.dto.ItemDTO;
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
    private Subcategory subcategory;
    private String coordinateX;
    private String coordinateY;
    private String contact;
    private String phone;
    private String website;
    private String facebook;
    private String instagram;
    @JsonFormat(pattern = ("yyyy.MM.dd HH:mm:ss"))
    private LocalDateTime deletedAt;

    public static Item fromDTO(ItemDTO itemDTO) {
        return Item.builder()
                .id(itemDTO.getId())
                .name(itemDTO.getName())
                .score(itemDTO.getScore())
                .bio(itemDTO.getBio())
                .address(itemDTO.getAddress())
                .city(itemDTO.getCity())
                .category(itemDTO.getCategory())
                .subcategory(itemDTO.getSubcategory())
                .coordinateX(itemDTO.getCoordinateX())
                .coordinateY(itemDTO.getCoordinateY())
                .contact(itemDTO.getContact())
                .phone(itemDTO.getPhone())
                .website(itemDTO.getWeb())
                .facebook(itemDTO.getFacebook())
                .instagram(itemDTO.getInstagram())
                .deletedAt(itemDTO.getDeletedAt())
                .build();
    }

    public ItemDTO toDTO() {
        return ItemDTO.builder()
                .id(id)
                .name(name)
                .score(score)
                .bio(bio)
                .address(address)
                .city(city)
                .category(category)
                .subcategory(subcategory)
                .coordinateX(coordinateX)
                .coordinateY(coordinateY)
                .contact(contact)
                .phone(phone)
                .web(website)
                .facebook(facebook)
                .instagram(instagram)
                .deletedAt(deletedAt)
                .build();
    }
}
