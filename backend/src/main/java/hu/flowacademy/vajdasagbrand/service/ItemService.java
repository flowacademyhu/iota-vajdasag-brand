package hu.flowacademy.vajdasagbrand.service;

import hu.flowacademy.vajdasagbrand.dto.ItemDTO;
import hu.flowacademy.vajdasagbrand.exception.ValidationException;
import hu.flowacademy.vajdasagbrand.repository.CommonItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {

    private final CommonItemRepository itemRepository;

    public ItemDTO createItem(ItemDTO item) throws ValidationException {
        validateItemData(item);
        return itemRepository.save(item);
    }

    public ItemDTO deleteById(String id) throws ValidationException {
        return itemRepository.save(itemRepository.findFirstByIdAndDeletedAtNull(id).orElseThrow(
                () -> new ValidationException("No item found with given id"))
                .toBuilder().deletedAt(LocalDateTime.now()).build());
    }

    public ItemDTO updateItem(ItemDTO item, String id) throws ValidationException {
        validateItemData(item);
        ItemDTO founded = itemRepository.findById(id).orElseThrow(() -> new ValidationException("Can not find this id"));
        modifyItems(item, founded);
        return itemRepository.save(founded);
    }

    private void validateItemData(ItemDTO item) throws ValidationException {
        if (!StringUtils.hasText(item.getName())) {
            throw new ValidationException("No name given");
        }
        if(!StringUtils.hasText(item.getBio())){
            throw new ValidationException("Didn't get bio");
        }
        if(!StringUtils.hasText(item.getScore())) {
            throw new ValidationException("Impossible value");
        }
        if(!StringUtils.hasText(item.getAddress())){
            throw new ValidationException("Didn't get address");
        }
        if(!StringUtils.hasText(item.getCity())){
            throw new ValidationException("Didn't get city");
        }
        if(item.getCategory() == null) {
            throw new ValidationException("Didn't get category");
        }
        if(!StringUtils.hasText(item.getCoordinateX())) {
            throw new ValidationException("Didn't get coordinate_x");
        }
        if(!StringUtils.hasText(item.getCoordinateY())) {
            throw new ValidationException("Didn't get coordinate_y");
        }
        if(!StringUtils.hasText(item.getPhone())) {
            throw new ValidationException("Didn't get phone");
        }
        if(!StringUtils.hasText(item.getWeb())) {
            throw new ValidationException("Didn't get website");
        }
        if (!StringUtils.hasText(item.getContact())) {
            throw new ValidationException("No contact given");
        }
        if(item.getSubcategory() == null) {
            throw new ValidationException("No subcategory given");
        }
        if (!StringUtils.hasText(item.getFacebook())) {
            throw new ValidationException("No facebook given");
        }
        if (!StringUtils.hasText(item.getInstagram())) {
            throw new ValidationException("No instagram given");
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
        tempItem.setWeb(item.getWeb());
        tempItem.setFacebook(item.getFacebook());
        tempItem.setInstagram(item.getInstagram());
        tempItem.setSubcategory(item.getSubcategory());
        tempItem.setContact(item.getContact());
        tempItem.setEmail(item.getEmail());
    }
}
