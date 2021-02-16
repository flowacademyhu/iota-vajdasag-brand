package hu.flowacademy.vajdasagbrand.controller;

import hu.flowacademy.vajdasagbrand.dto.ItemDTO;
import hu.flowacademy.vajdasagbrand.entity.Item;
import hu.flowacademy.vajdasagbrand.exception.ValidationException;
import hu.flowacademy.vajdasagbrand.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/itemregistration")
    @ResponseStatus(HttpStatus.CREATED)
    @PermitAll
    public void ItemRegistration(@RequestBody ItemDTO itemDTO) throws ValidationException {
        Item item = new Item();
        BeanUtils.copyProperties(itemDTO, item);

        itemService.itemRegistrationData(item);
    }
}
