package hu.flowacademy.vajdasagbrand.service;

import hu.flowacademy.vajdasagbrand.entity.User;
import hu.flowacademy.vajdasagbrand.exception.ValidationException;
import hu.flowacademy.vajdasagbrand.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final KeycloakClientService keycloakClientService;

    public void deleteById(String id) throws ValidationException {
       Optional<User> user = userRepository.findById(id);
       if(user.isEmpty()) {
           throw new ValidationException("No user with given id: " + id);
       }
       if(user.get().getDeletedAt() != null) {
           throw new ValidationException("User already deleted");
       }
       User deleted = user.get();
       //keycloakClientService.deleteUser(deleted.getEmail());
       deleted.setDeletedAt(LocalDateTime.now().withNano(0));
       userRepository.save(deleted);
    }
}
