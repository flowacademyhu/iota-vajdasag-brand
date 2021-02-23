package hu.flowacademy.vajdasagbrand.repository;

import hu.flowacademy.vajdasagbrand.configuration.persistence.sql.repository.UserRepository;
import hu.flowacademy.vajdasagbrand.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements CommonUserRepository {

    private UserRepository userRepository;

    public UserRepositoryImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        return userRepository.save(userDTO);
    }

    @Override
    public Optional<UserDTO> findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public Page<UserDTO> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
