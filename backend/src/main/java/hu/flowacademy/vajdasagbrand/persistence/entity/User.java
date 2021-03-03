package hu.flowacademy.vajdasagbrand.persistence.entity;

import com.google.cloud.Timestamp;
import hu.flowacademy.vajdasagbrand.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
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
    private Timestamp registeredAt;
    private Timestamp deletedAt;

    public static User fromDTO(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .fullName(userDTO.getFullName())
                .taxNumber(userDTO.getTaxNumber())
                .address(userDTO.getAddress())
                .email(userDTO.getEmail())
                .type(userDTO.getType())
                .enabled(userDTO.isEnabled())
                .registeredAt(Timestamp.of(Date.from(userDTO.getRegisteredAt().atZone(ZoneId.systemDefault()).toInstant())))
                .deletedAt(Optional.ofNullable(userDTO.getDeletedAt())
                        .map(localDateTime -> Timestamp.of(
                                Date.from(userDTO.getDeletedAt().atZone(
                                        ZoneId.systemDefault()).toInstant())
                                )
                        )
                        .orElse(null))
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
                .registeredAt(registeredAt.toDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                .deletedAt(Optional.ofNullable(deletedAt).map(Timestamp::toDate)
                        .map(Date::toInstant)
                        .map(instant -> instant.atZone(ZoneId.systemDefault()))
                        .map(ZonedDateTime::toLocalDateTime)
                        .orElse(null))
                .build();
    }
}
