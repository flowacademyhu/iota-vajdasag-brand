package hu.flowacademy.vajdasagbrand.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import hu.flowacademy.vajdasagbrand.dto.UserDTO;
import hu.flowacademy.vajdasagbrand.persistence.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;


@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRepository {

    private static final String COLLECTION = "users";
    private final Firestore firestore;

    public UserDTO save(UserDTO userDTO) {
        User user = User.fromDTO(userDTO);
        user.setId(UUID.randomUUID().toString());
        firestore.collection(COLLECTION).document(user.getId()).set(user);
        return user.toDTO();
    }

    public Optional<UserDTO> findById(String id) {
        try {
            return Optional.ofNullable(firestore.collection(COLLECTION).document(id).get().get().toObject(User.class)).map(User::toDTO);
        } catch (InterruptedException | ExecutionException e) {
            log.error("No user with given id", e);
            return Optional.empty();
        }
    }

//    public Page<UserDTO> findAllUsers(Pageable pageable) {
//        ApiFuture<QuerySnapshot> documents = firestore.collection(COLLECTION).get();
//        try {
//          return new PageImpl<>(documents.get().getDocuments().stream()
//                    .map(queryDocumentSnapshot -> queryDocumentSnapshot.toObject(User.class))
//                    .skip(pageable.getOffset())
//                    .limit(pageable.getPageSize())
//                    .sorted(Comparator.comparing(User::getRegisteredAt))
//                    .map(User::toDTO)
//                    .collect(Collectors.toList()));
//        } catch (InterruptedException | ExecutionException e) {
//            log.error("Exception occurred while retrieving all document", e);
//            return Page.empty();
//        }
//    }

    public Optional<UserDTO> findByEmail(String email) {
        ApiFuture<QuerySnapshot> documents = firestore.collection(COLLECTION).get();
        try {
            return documents.get().getDocuments().stream()
                    .map(queryDocumentSnapshot -> queryDocumentSnapshot.toObject(User.class))
                    .filter(user -> user.getEmail().equals(email))
                    .findFirst()
                    .map(User::toDTO);
        } catch (InterruptedException | ExecutionException e) {
            log.error("No user with given email", e);
            return Optional.empty();
        }
    }

    public Page<UserDTO> findAllUsers(Pageable pageable) {
        CollectionReference collection = firestore.collection(COLLECTION);
        try {
            pageable.getSort().stream()
                    .forEach(order -> collection.orderBy(order.getProperty(),
                            order.isAscending() ? Query.Direction.ASCENDING : Query.Direction.DESCENDING));

            return new PageImpl<>(collection.get().get().getDocuments().stream()
                    .map(queryDocumentSnapshot -> queryDocumentSnapshot.toObject(User.class))
                    .map(User::toDTO)
                    .collect(Collectors.toList()));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return Page.empty();
        }
    }
}
