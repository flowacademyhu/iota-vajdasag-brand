package hu.flowacademy.vajdasagbrand.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String fullName;
    private String taxNumber;
    private String address;
    private String email;
    private Type type;
}
