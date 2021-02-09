package hu.flowacademy.vajdasagbrand.userRepository;

import hu.flowacademy.vajdasagbrand.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
