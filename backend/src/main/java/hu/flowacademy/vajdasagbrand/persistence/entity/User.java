package hu.flowacademy.vajdasagbrand.persistence.entity;

import com.google.cloud.Timestamp;
import hu.flowacademy.vajdasagbrand.dto.UserDTO;
import hu.flowacademy.vajdasagbrand.util.TimestampConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
public class User {

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
                .registeredAt(TimestampConverter.toTimestamp(userDTO.getRegisteredAt()))
                .deletedAt(TimestampConverter.toTimestamp(userDTO.getDeletedAt()))
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
                .registeredAt(TimestampConverter.toLocalDateTime(registeredAt))
                .deletedAt(TimestampConverter.toLocalDateTime(deletedAt))
                .build();
    }
}
