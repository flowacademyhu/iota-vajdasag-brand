package hu.flowacademy.vajdasagbrand.persistence.entity;

import com.google.cloud.Timestamp;
import hu.flowacademy.vajdasagbrand.dto.ItemDTO;
import hu.flowacademy.vajdasagbrand.util.TimestampConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
public class Item {

    private String id;
    private String name;
    private String score;
    private String bio;
    private String address;
    private String city;
    private Category category;
    private Subcategory subcategory;
    private String coordinateX;
    private String coordinateY;
    private String contact;
    private String phone;
    private String website;
    private String facebook;
    private String instagram;
    private Timestamp deletedAt;
    private String ownerId;

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
                .website(itemDTO.getWebsite())
                .facebook(itemDTO.getFacebook())
                .instagram(itemDTO.getInstagram())
                .deletedAt(TimestampConverter.toTimestamp(itemDTO.getDeletedAt()))
                .ownerId(itemDTO.getOwnerId())
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
                .website(website)
                .facebook(facebook)
                .instagram(instagram)
                .deletedAt(TimestampConverter.toLocalDateTime(deletedAt))
                .ownerId(Optional.ofNullable(owner).map(User::getId).orElse(null))
                .build();
    }
}