package hu.flowacademy.vajdasagbrand.persistence.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import hu.flowacademy.vajdasagbrand.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@Table(name = "UserTable")
public class User {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String fullName;
    private String taxNumber;
    private String address;
    private String email;
    private Type type;
    private boolean enabled;
    @JsonFormat(pattern = ("yyyy.MM.dd HH:mm:ss"))
    private LocalDateTime registeredAt;
    @JsonFormat(pattern = ("yyyy.MM.dd HH:mm:ss"))
    private LocalDateTime deletedAt;

    public static User fromDTO(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .fullName(userDTO.getFullName())
                .taxNumber(userDTO.getTaxNumber())
                .address(userDTO.getAddress())
                .email(userDTO.getEmail())
                .type(userDTO.getType())
                .enabled(userDTO.isEnabled())
                .registeredAt(userDTO.getRegisteredAt())
                .deletedAt(userDTO.getDeletedAt())
                .build();
    }

    public UserDTO toDTO() {
        return UserDTO.builder()
                .id(id)
                .fullName(fullName)
                .taxNumber(taxNumber)
                .address(address)
                .email(email)
                .type(type)
                .enabled(enabled)
                .registeredAt(registeredAt)
                .deletedAt(deletedAt)
                .build();
    }
}
