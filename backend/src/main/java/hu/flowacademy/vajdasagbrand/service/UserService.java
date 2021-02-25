package hu.flowacademy.vajdasagbrand.service;

import hu.flowacademy.vajdasagbrand.configuration.persistence.entity.Type;
import hu.flowacademy.vajdasagbrand.dto.UserDTO;
import hu.flowacademy.vajdasagbrand.exception.UserNotEnabledException;
import hu.flowacademy.vajdasagbrand.repository.CommonUserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import hu.flowacademy.vajdasagbrand.exception.ValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final CommonUserRepository userRepository;
    private final KeycloakClientService keycloakClientService;

    public UserDTO deleteById(String id) throws ValidationException, UserNotEnabledException {
        Optional<UserDTO> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new ValidationException("No user with given id: " + id);
        }
        if (!user.get().isEnabled()) {
            throw new UserNotEnabledException("User is not enabled");
        }
        UserDTO deleted = user.get();
        if (!keycloakClientService.deleteUser(deleted.getEmail())) {
            throw new ValidationException("No user with id in Keycloak");
        }
        deleted.setDeletedAt(LocalDateTime.now().withNano(0));
        deleted.setEnabled(false);
        keycloakClientService.deleteUser(deleted.getEmail());
        userRepository.save(deleted);
        return deleted;
    }

    public UserDTO userRegistrationData(UserDTO user, String password) throws ValidationException {
        validateUserData(user);
        keycloakClientService.createAccount(user.getEmail(), password);
        user.setRegisteredAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    private void validateUserData(UserDTO user) throws ValidationException {
        if (!StringUtils.hasText(user.getFullName())) {
            throw new ValidationException("Didn't get full name");
        }
        if (!StringUtils.hasText(user.getAddress())) {
            throw new ValidationException("Didn't get address");
        }
        if (!StringUtils.hasText(user.getEmail())) {
            throw new ValidationException("Didn't get email");
        }
        if (!EmailValidator.getInstance().isValid(user.getEmail())) {
            throw new ValidationException("Invalid email");
        }
        if (!StringUtils.hasText(String.valueOf(user.getType()))) {
            throw new ValidationException("Didn't get type");
        }
        if ((user.getTaxNumber()).isEmpty() && user.getType() == Type.COMPANY) {
            throw new ValidationException("Can not add tax number for individual members");
        }
    }

    public Page<UserDTO> getUsers(String orderBy, int pageNum, int limit) {
        return userRepository.findAll(PageRequest.of(pageNum, limit, Sort.by(Sort.Direction.DESC, orderBy)));
    }
}
