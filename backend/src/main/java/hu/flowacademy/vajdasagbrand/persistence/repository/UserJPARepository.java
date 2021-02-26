package hu.flowacademy.vajdasagbrand.persistence.repository;

import hu.flowacademy.vajdasagbrand.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJPARepository extends JpaRepository<User, String>{
}
