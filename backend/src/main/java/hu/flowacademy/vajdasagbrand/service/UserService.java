package hu.flowacademy.vajdasagbrand.service;

import hu.flowacademy.vajdasagbrand.entity.User;
import hu.flowacademy.vajdasagbrand.exception.ValidationException;
import hu.flowacademy.vajdasagbrand.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User deleteById(String id) throws ValidationException {
       Optional<User> user = userRepository.findById(id);
       if(user.isEmpty()) {
           throw new ValidationException("No user with given id: " + id);
       }
       if(!user.get().getDeletedAt().equals(null)) {
           throw new ValidationException("User already deleted");
       }
       User deleted = user.get();
       deleted.setDeletedAt(LocalDateTime.now());
       userRepository.save(deleted);
       return deleted;
    }
}
