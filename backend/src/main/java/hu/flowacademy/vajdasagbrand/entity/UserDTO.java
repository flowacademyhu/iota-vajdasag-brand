package hu.flowacademy.vajdasagbrand.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
