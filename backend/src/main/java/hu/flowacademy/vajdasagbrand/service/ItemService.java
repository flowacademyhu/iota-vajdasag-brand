package hu.flowacademy.vajdasagbrand.service;

import hu.flowacademy.vajdasagbrand.entity.Item;
import hu.flowacademy.vajdasagbrand.exception.ValidationException;
import hu.flowacademy.vajdasagbrand.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;

    public Item createItem(Item item) throws ValidationException {
        validateItemData(item);

        return itemRepository.save(item);
    }

    private void validateItemData(Item item) throws ValidationException {
        if (!StringUtils.hasText(item.getName())) {
            throw new ValidationException("Didn't get name");
        }
        if(!StringUtils.hasText(item.getBio())){
            throw new ValidationException("Didn't get bio");
        }
        if(item.getScore() == 0 || item.getScore() > 100) {
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
        if(!StringUtils.hasText(item.getWebsite())) {
            throw new ValidationException("Didn't get website");
        }
        if(!StringUtils.hasText(item.getFacebook())) {
            throw new ValidationException("Didn't get facebook");
        }
        if(!StringUtils.hasText(item.getInstagram())) {
            throw new ValidationException("Didn't get instagram");
        }
    }

}
