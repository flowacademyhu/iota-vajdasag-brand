package hu.flowacademy.vajdasagbrand.persistence.repository;

import hu.flowacademy.vajdasagbrand.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJPARepository extends JpaRepository<User, String>{
    Optional<User> findByEmail(String email);
}
