package hu.flowacademy.vajdasagbrand.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
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
}
