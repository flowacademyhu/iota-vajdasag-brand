package hu.flowacademy.vajdasagbrand.controller;

import hu.flowacademy.vajdasagbrand.dto.ItemDTO;
import hu.flowacademy.vajdasagbrand.entity.Item;
import hu.flowacademy.vajdasagbrand.exception.ValidationException;
import hu.flowacademy.vajdasagbrand.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Slf4j
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/items")
    @ResponseStatus(HttpStatus.CREATED)
    @RolesAllowed({"CegAdmin", "SuperAdmin"})
    public void createItem(@RequestBody ItemDTO itemDTO) throws ValidationException {
        Item item = new Item();
        BeanUtils.copyProperties(itemDTO, item);
        itemService.createItem(item);
    }

    @DeleteMapping("/delete/{id}")
    @RolesAllowed({"SuperAdmin", "CegAdmin"})
    public Item deleteItem(@PathVariable("id") String id) throws ValidationException {
        return itemService.deleteById(id);
    }
}
