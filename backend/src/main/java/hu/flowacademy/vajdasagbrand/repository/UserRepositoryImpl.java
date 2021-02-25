package hu.flowacademy.vajdasagbrand.repository;

import hu.flowacademy.vajdasagbrand.configuration.persistence.entity.User;
import hu.flowacademy.vajdasagbrand.configuration.persistence.repository.UserRepository;
import hu.flowacademy.vajdasagbrand.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
public class UserRepositoryImpl implements CommonUserRepository {

    private final UserRepository userRepository;

    public UserRepositoryImpl(@Lazy UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        log.debug("user in repo: {} ", userDTO);
        return userRepository.save(User.fromDTO(userDTO)).toDTO();
    }

    @Override
    public Optional<UserDTO> findById(String id) {
        return userRepository.findById(id).map(User::toDTO);
    }

    @Override
    public Page<UserDTO> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).map(User::toDTO);
    }
}
