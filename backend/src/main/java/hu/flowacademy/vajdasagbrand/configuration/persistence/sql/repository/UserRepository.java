package hu.flowacademy.vajdasagbrand.configuration.persistence.sql.repository;

import hu.flowacademy.vajdasagbrand.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDTO, String>{
}
