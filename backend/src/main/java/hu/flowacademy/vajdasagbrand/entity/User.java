package hu.flowacademy.vajdasagbrand.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import hu.flowacademy.vajdasagbrand.exception.ValidationException;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String fullName;
    private String taxNumber;
    private String address;
    private String email;
    private String password;
    private Type type;
    private boolean enabled;
    @JsonFormat(pattern = ("yyyy/MM/dd HH:mm:ss"))
    private LocalDateTime localDateTime;

    public User(String fullName, String taxNumber, String address, String email, String password, String type) throws ValidationException {
        this.fullName = fullName;
        this.taxNumber = taxNumber;
        this.address = address;
        this.email = email;
        this.password = password;
        if(type.equals("INDIVIDUAL")) {
            this.type = Type.INDIVIDUAL;
        } else if(type.equals("COMPANY")) {
            this.type = Type.COMPANY;
        } else {
            throw new ValidationException("Bad input");
        }
        this.enabled = true;
        this.localDateTime = LocalDateTime.now();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", taxNumber='" + taxNumber + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
