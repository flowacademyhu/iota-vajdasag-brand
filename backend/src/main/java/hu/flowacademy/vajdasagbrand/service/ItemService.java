package hu.flowacademy.vajdasagbrand.service;

import hu.flowacademy.vajdasagbrand.dto.CegAdminItemDTO;
import hu.flowacademy.vajdasagbrand.dto.SuperAdminItemDTO;
import hu.flowacademy.vajdasagbrand.entity.Item;
import hu.flowacademy.vajdasagbrand.exception.ValidationException;
import hu.flowacademy.vajdasagbrand.repository.ItemRepository;
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

    public Item createItem(Item item) throws ValidationException {
        validateItemData(item);

        return itemRepository.save(item);
    }

    public Item deleteById(String id) throws ValidationException {
        return itemRepository.save(itemRepository.findFirstByIdAndDeletedAtNull(id).orElseThrow(
                () -> new ValidationException("No item found with given id"))
                .toBuilder().deletedAt(LocalDateTime.now()).build());
    }

    public Item updateItem(Item item, String id) throws ValidationException {
        validateItemData(item);
        Item founded = itemRepository.findById(id).orElseThrow(() -> new ValidationException("Can not find this id"));
        modifyItems(item, founded);
        return itemRepository.save(founded);
    }

    private void validateItemData(Item item) throws ValidationException {
        if (!StringUtils.hasText(item.getName())) {
            throw new ValidationException("Didn't get name");
        }
        if (!StringUtils.hasText(item.getBio())) {
            throw new ValidationException("Didn't get bio");
        }
        if (item.getScore() == 0 || item.getScore() > 100) {
            throw new ValidationException("Impossible value");
        }
        if (!StringUtils.hasText(item.getAddress())) {
            throw new ValidationException("Didn't get address");
        }
        if (!StringUtils.hasText(item.getCity())) {
            throw new ValidationException("Didn't get city");
        }
        if (item.getCategory() == null) {
            throw new ValidationException("Didn't get category");
        }
        if (!StringUtils.hasText(item.getCoordinateX())) {
            throw new ValidationException("Didn't get coordinate_x");
        }
        if (!StringUtils.hasText(item.getCoordinateY())) {
            throw new ValidationException("Didn't get coordinate_y");
        }
        if (!StringUtils.hasText(item.getPhone())) {
            throw new ValidationException("Didn't get phone");
        }
        if (!StringUtils.hasText(item.getWebsite())) {
            throw new ValidationException("Didn't get website");
        }
        if (!StringUtils.hasText(item.getFacebook())) {
            throw new ValidationException("Didn't get facebook");
        }
        if (!StringUtils.hasText(item.getInstagram())) {
            throw new ValidationException("Didn't get instagram");
        }
    }

    private void modifyItems(Item item, Item tempItem) {
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
    }

    public List<CegAdminItemDTO> listProducts(Optional<Authentication> authentication) throws ValidationException {
        List<String> roles = authentication.map(Authentication::getAuthorities)
                .map(grantedAuthorities -> grantedAuthorities
                        .stream().map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .orElseThrow(() -> new ValidationException("User has no authorization"));

        if (roles.contains("ROLE_SuperAdmin")) {
            return itemRepository.findAll().stream()
                    .map(item -> new SuperAdminItemDTO(item.getAddress(), item.getCity(),
                            item.getCategory(), item.getName()))
                    .collect(Collectors.toList());
        } else if (roles.contains("ROLE_CegAdmin")) {
            return itemRepository.findAll().stream()
                    .map(item -> new CegAdminItemDTO(item.getAddress(), item.getCity(),
                            item.getCategory()))
                    .collect(Collectors.toList());
        } else {
            throw new ValidationException("User has no authorization.");
        }
    }
}