package hu.flowacademy.vajdasagbrand.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import hu.flowacademy.vajdasagbrand.configuration.persistence.sql.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("tax_number")
    private String taxNumber;
    private String address;
    private String email;
    private Type type;
    private String password;
    private boolean enabled;
    @JsonFormat(pattern = ("yyyy.MM.dd HH:mm:ss"))
    private LocalDateTime registeredAt;
    @JsonFormat(pattern = ("yyyy.MM.dd HH:mm:ss"))
    private LocalDateTime deletedAt;
}
