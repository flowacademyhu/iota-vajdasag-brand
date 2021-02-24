package hu.flowacademy.vajdasagbrand.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import hu.flowacademy.vajdasagbrand.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("tax_number")
    private String taxNumber;
    private String address;
    private String email;
    private Type type;
    private String password;
}
