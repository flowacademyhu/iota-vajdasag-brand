package hu.flowacademy.vajdasagbrand.repository;


import hu.flowacademy.vajdasagbrand.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CommonUserRepository {

    UserDTO save(UserDTO userDTO);
    Optional<UserDTO> findById(String id);
    Page<UserDTO> findAll(Pageable pageable);
}
