package hu.flowacademy.vajdasagbrand.service;

import hu.flowacademy.vajdasagbrand.dto.CegAdminItemDTO;
import hu.flowacademy.vajdasagbrand.dto.ItemDTO;
import hu.flowacademy.vajdasagbrand.dto.SuperAdminItemDTO;
import hu.flowacademy.vajdasagbrand.dto.UserDTO;
import hu.flowacademy.vajdasagbrand.exception.ValidationException;
import hu.flowacademy.vajdasagbrand.persistence.entity.Category;
import hu.flowacademy.vajdasagbrand.persistence.entity.Language;
import hu.flowacademy.vajdasagbrand.repository.ItemRepository;
import hu.flowacademy.vajdasagbrand.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
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
            throw new ValidationException("Attraction category must have a subcategory.");
        }
    }

    private void validateItemData(ItemDTO item) throws ValidationException {
        subcategoryValidation(item);
        if (item.getName() == null) {
            throw new ValidationException("No name was provided.");
        }
        if (item.getBio() == null) {
            throw new ValidationException("No bio-description was provided.");
        }
        if (item.getScore() == null) {
            throw new ValidationException("Impossible value for score.");
        }
        if (item.getAddress() == null) {
            throw new ValidationException("No address was provided.");
        }
        if (item.getCity() == null) {
            throw new ValidationException("No city was provided.");
        }
        if (item.getCategory() == null) {
            throw new ValidationException("No category was provided.");
        }
        if (item.getCoordinateX() == null) {
            throw new ValidationException("No latitude-coordinate_X was provided.");
        }
        if (item.getCoordinateY() == null) {
            throw new ValidationException("No longitude-coordinate_Y was provided");
        }
        if (item.getPhone() == null) {
            throw new ValidationException("No phone number was provided");
        }
        if (item.getWebsite() == null) {
            throw new ValidationException("No website was provided.");
        }
        if (item.getContact() == null) {
            throw new ValidationException("No contact was provided.");
        }
        if (item.getFacebook() == null) {
            throw new ValidationException("No facebook was provided.");
        }
        if (item.getInstagram() == null) {
            throw new ValidationException("No instagram was provided.");
        }
        if (item.getOwnerId() == null) {
            throw new ValidationException("No owner was found with this ID.");
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

    public List<CegAdminItemDTO> listProducts(Optional<Authentication> authentication, Optional<String> ownerId, Optional<Language> language) throws ValidationException {
        List<ItemDTO> items = ownerId.map(oid -> itemRepository.findByOwnerId(oid, language.orElse(Language.hu)))
                .orElseGet(() -> itemRepository.findAll(language.orElse(Language.hu)));
        if (isUserSuperAdmin(authentication)) {
            return items.stream()
                    .map(this::createSuperAdminDTO)
                    .collect(Collectors.toList());
        } else if (isUserCegAdmin(authentication)) {
            return items.stream()
                    .map(this::createCegAdminDTO)
                    .collect(Collectors.toList());
        } else {
            throw new ValidationException("User has no authorization.");
        }
    }

    public boolean isUserSuperAdmin(Optional<Authentication> authentication) throws ValidationException {
        return authentication.map(Authentication::getAuthorities)
                .map(grantedAuthorities -> grantedAuthorities
                        .stream().map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .orElseThrow(() -> new ValidationException("User has no authorization")).contains(SUPERADMIN);
    }

    public boolean isUserCegAdmin(Optional<Authentication> authentication) throws ValidationException {
        return authentication.map(Authentication::getAuthorities)
                .map(grantedAuthorities -> grantedAuthorities
                        .stream().map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .orElseThrow(() -> new ValidationException("User has no authorization")).contains(CEGADMIN);
    }

    public SuperAdminItemDTO createSuperAdminDTO(ItemDTO item) {
        return new SuperAdminItemDTO(item.getId(), item.getName(), item.getScore(), item.getBio(), item.getAddress(), item.getContact(), item.getCity(),
                item.getEmail(), item.getCategory(), item.getSubcategory(), item.getCoordinateX(), item.getCoordinateY(), item.getPhone(), item.getWebsite(), item.getFacebook(), item.getInstagram(), item.getDeletedAt(),
                item.getOwnerId(), userRepository.findById(item.getOwnerId())
                .map(UserDTO::getFullName).orElse(""));
    }

    public CegAdminItemDTO createCegAdminDTO(ItemDTO item) {
        return new CegAdminItemDTO(item.getId(), item.getName(), item.getScore(), item.getBio(), item.getAddress(), item.getContact(), item.getCity(),
                item.getEmail(), item.getCategory(), item.getSubcategory(), item.getCoordinateX(), item.getCoordinateY(), item.getPhone(), item.getWebsite(), item.getFacebook(), item.getInstagram(), item.getDeletedAt(), item.getOwnerId());
    }

    public CegAdminItemDTO findOneProduct(String id) throws ValidationException {
        ItemDTO itemDTO = itemRepository.findById(id).orElseThrow(
                () -> new ValidationException("No item was found with the given id"));
        return createCegAdminDTO(itemDTO);
    }
}