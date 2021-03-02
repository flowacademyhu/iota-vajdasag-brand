package hu.flowacademy.vajdasagbrand.repository;

import hu.flowacademy.vajdasagbrand.persistence.entity.User;
import hu.flowacademy.vajdasagbrand.persistence.repository.UserJPARepository;
import hu.flowacademy.vajdasagbrand.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final UserJPARepository userJPARepository;

    public UserDTO save(UserDTO userDTO) {
        return userJPARepository.save(User.fromDTO(userDTO)).toDTO();
    }

    public Optional<UserDTO> findById(String id) {
        return userJPARepository.findById(id).map(User::toDTO);
    }

    public Page<UserDTO> findAllUsers(Pageable pageable) {
        return userJPARepository.findAll(pageable).map(User::toDTO);
    }

    public Optional<UserDTO> findByEmail(String email) {
        return userJPARepository.findByEmail(email).map(User::toDTO);
    }
}
