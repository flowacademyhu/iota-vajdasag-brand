package hu.flowacademy.vajdasagbrand.controller;

import hu.flowacademy.vajdasagbrand.dto.ItemDTO;
import hu.flowacademy.vajdasagbrand.entity.Item;
import hu.flowacademy.vajdasagbrand.exception.ValidationException;
import hu.flowacademy.vajdasagbrand.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ItemController {

    private final ItemService itemService;

    @RolesAllowed("SuperAdmin, CegAdmin")
    @PostMapping("/items")
    @ResponseStatus(HttpStatus.CREATED)
    public void createItem(@RequestBody ItemDTO itemDTO) throws ValidationException {
        Item item = new Item();
        BeanUtils.copyProperties(itemDTO, item);

        itemService.createItem(item);
    }

    @PutMapping("/items/{id}")
    public Item updateItem (@PathVariable("id") String id,
            @RequestBody Item item) throws ValidationException {
        try {
            return itemService.updateItem(item, id);
        } catch (ValidationException e) {
            throw new ValidationException("Can not update item datas");
        }
    }
}
