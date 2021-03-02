package hu.flowacademy.vajdasagbrand.service;

import hu.flowacademy.vajdasagbrand.dto.CegAdminItemDTO;
import hu.flowacademy.vajdasagbrand.dto.SuperAdminItemDTO;
import hu.flowacademy.vajdasagbrand.dto.ItemDTO;
import hu.flowacademy.vajdasagbrand.dto.UserDTO;
import hu.flowacademy.vajdasagbrand.exception.ValidationException;
import hu.flowacademy.vajdasagbrand.persistence.entity.Category;
import hu.flowacademy.vajdasagbrand.persistence.entity.User;
import hu.flowacademy.vajdasagbrand.persistence.repository.ItemJPARepository;
import hu.flowacademy.vajdasagbrand.repository.ItemRepository;
import hu.flowacademy.vajdasagbrand.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private static final String SUPERADMIN = "ROLE_SuperAdmin";
    private static final String CEGADMIN = "ROLE_CegAdmin";

    public ItemDTO createItem(ItemDTO item) throws ValidationException {
        validateItemData(item);
        if (!Category.ATTRACTION.equals(item.getCategory())) {
            item.setSubcategory(null);
        }
        return itemRepository.save(item);
    }

    public ItemDTO deleteById(String id) throws ValidationException {
        return itemRepository.save(itemRepository.findFirstById(id).orElseThrow(
                () -> new ValidationException("No item found with given id"))
                .toBuilder().deletedAt(LocalDateTime.now()).build());
    }

    public ItemDTO updateItem(ItemDTO item, String id) throws ValidationException {
        validateItemData(item);
        if (!Category.ATTRACTION.equals(item.getCategory())) {
            item.setSubcategory(null);
        }
        ItemDTO founded = itemRepository.findById(id).orElseThrow(() -> new ValidationException("Can not find this id"));
        modifyItems(item, founded);
        return itemRepository.save(founded);
    }

    private void subcategoryValidation(ItemDTO item) throws ValidationException {
        if (Category.ATTRACTION.equals(item.getCategory()) && item.getSubcategory() == null) {
            throw new ValidationException("Attraction category have to have subcategory");
        }
    }

    private void validateItemData(ItemDTO item) throws ValidationException {
        subcategoryValidation(item);
        if (item.getName() == null) {
            throw new ValidationException("No name given");
        }
        if (item.getBio() == null) {
            throw new ValidationException("Didn't get bio");
        }
        if (item.getScore() == null) {
            throw new ValidationException("Impossible value");
        }
        if (item.getAddress() == null) {
            throw new ValidationException("Didn't get address");
        }
        if (item.getCity() == null) {
            throw new ValidationException("Didn't get city");
        }
        if (item.getCategory() == null) {
            throw new ValidationException("Didn't get category");
        }
        if (item.getCoordinateX() == null) {
            throw new ValidationException("Didn't get coordinate_x");
        }
        if (item.getCoordinateY() == null) {
            throw new ValidationException("Didn't get coordinate_y");
        }
        if (item.getPhone() == null) {
            throw new ValidationException("Didn't get phone");
        }
        if (item.getWebsite() == null) {
            throw new ValidationException("Didn't get website");
        }
        if (item.getContact() == null) {
            throw new ValidationException("No contact given");
        }
        if (item.getFacebook() == null) {
            throw new ValidationException("No facebook given");
        }
        if (item.getInstagram() == null) {
            throw new ValidationException("No instagram given");
        }
        if (item.getOwnerId() == null) {
            throw new ValidationException("No owner with this id");
        }
    }

    private void modifyItems(ItemDTO item, ItemDTO tempItem) {
        tempItem.setName(item.getName());
        tempItem.setBio(item.getBio());
        tempItem.setScore(item.getScore());
        tempItem.setAddress(item.getAddress());
        tempItem.setCity(item.getCity());
        tempItem.setCategory(item.getCategory());
        tempItem.setCoordinateX(item.getCoordinateX());
        tempItem.setCoordinateY(item.getCoordinateY());
        tempItem.setPhone(item.getPhone());
        tempItem.setWebsite(item.getWebsite());
        tempItem.setFacebook(item.getFacebook());
        tempItem.setInstagram(item.getInstagram());
        tempItem.setSubcategory(item.getSubcategory());
        tempItem.setContact(item.getContact());
        tempItem.setEmail(item.getEmail());
    }

    public List<CegAdminItemDTO> listProducts(Optional<Authentication> authentication) throws ValidationException {
        List<String> roles = authentication.map(Authentication::getAuthorities)
                .map(grantedAuthorities -> grantedAuthorities
                        .stream().map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .orElseThrow(() -> new ValidationException("User has no authorization"));

        if (roles.contains(SUPERADMIN)) {
            return itemRepository.findAll().stream()
                    .map(word -> {
                        try {
                            return createSuperAdminDTO(word);
                        } catch (Exception e) {
                            throw new RuntimeException("No item found.");
                        }
                    }).collect(Collectors.toList());
        } else if (roles.contains(CEGADMIN)) {
            return itemRepository.findAll().stream()
                    .map(this::createCegAdminDTO)
                    .collect(Collectors.toList());
        } else {
            throw new ValidationException("User has no authorization.");
        }
    }

    public SuperAdminItemDTO createSuperAdminDTO(ItemDTO item) throws ValidationException {
        UserDTO itemOwner = userRepository.findById(item.getOwnerId()).orElseThrow(() -> new ValidationException("No user found with given id"));

        return new SuperAdminItemDTO(item.getId(), item.getName(), item.getScore(), item.getBio(), item.getAddress(), item.getContact(), item.getCity(),
                item.getEmail(), item.getCategory(), item.getSubcategory(), item.getCoordinateX(), item.getCoordinateY(), item.getPhone(), item.getWebsite(), item.getFacebook(), item.getInstagram(), item.getDeletedAt(),
                item.getOwnerId(), itemOwner.getFullName());
    }

    public CegAdminItemDTO createCegAdminDTO(ItemDTO item) {
        return new CegAdminItemDTO(item.getId(), item.getName(), item.getScore(), item.getBio(), item.getAddress(), item.getContact(), item.getCity(),
                item.getEmail(), item.getCategory(), item.getSubcategory(), item.getCoordinateX(), item.getCoordinateY(), item.getPhone(), item.getWebsite(), item.getFacebook(), item.getInstagram(), item.getDeletedAt(), item.getOwnerId());
    }

    public CegAdminItemDTO findOneProduct(String id) throws ValidationException {
        ItemDTO itemDTO = itemRepository.findById(id).orElseThrow(
                () -> new ValidationException("No item found with given id"));
        return createCegAdminDTO(itemDTO);
    }
}