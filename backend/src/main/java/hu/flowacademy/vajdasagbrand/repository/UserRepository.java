package hu.flowacademy.vajdasagbrand.repository;

import hu.flowacademy.vajdasagbrand.persistence.entity.User;
import hu.flowacademy.vajdasagbrand.persistence.repository.UserJPARepository;
import hu.flowacademy.vajdasagbrand.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl {

    private final UserJPARepository userJPARepository;

    @Override
    public UserDTO save(UserDTO userDTO) {
        log.debug("user in repo: {} ", userDTO);
        return userJPARepository.save(User.fromDTO(userDTO)).toDTO();
    }

    @Override
    public Optional<UserDTO> findById(String id) {
        return userJPARepository.findById(id).map(User::toDTO);
    }

    @Override
    public Page<UserDTO> findAllUsers(Pageable pageable) {
        return userJPARepository.findAll(pageable).map(User::toDTO);
    }
}
