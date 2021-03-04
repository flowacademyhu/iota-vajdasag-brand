package hu.flowacademy.vajdasagbrand.controller;

import hu.flowacademy.vajdasagbrand.dto.CegAdminItemDTO;
import hu.flowacademy.vajdasagbrand.dto.ItemDTO;
import hu.flowacademy.vajdasagbrand.exception.ValidationException;
import hu.flowacademy.vajdasagbrand.persistence.entity.Language;
import hu.flowacademy.vajdasagbrand.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ItemController {

    private final ItemService itemService;

    @RolesAllowed({"SuperAdmin", "CegAdmin"})
    @PostMapping("/items")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDTO createItem(@RequestBody ItemDTO itemDTO) throws ValidationException {

        return itemService.createItem(itemDTO);
    }

    @RolesAllowed({"SuperAdmin", "CegAdmin"})
    @DeleteMapping("/items/{id}")
    public ItemDTO deleteItem(@PathVariable("id") String id) throws ValidationException {
        return itemService.deleteById(id);
    }

    @RolesAllowed({"SuperAdmin", "CegAdmin"})
    @PutMapping("/items/{id}")
    public ItemDTO updateItem(@PathVariable("id") String id,
                              @RequestBody ItemDTO item) throws ValidationException {
        return itemService.updateItem(item, id);
    }

    @RolesAllowed({"SuperAdmin", "CegAdmin"})
    @GetMapping("/items")
    public List<CegAdminItemDTO> getProducts(Authentication authentication,
                                             @RequestParam(value = "ownerId", required = false) Optional<String> ownerId,
                                             @RequestParam(value = "language", required = false) Optional<Language> language) throws ValidationException {
        return itemService.listProducts(Optional.ofNullable(authentication), ownerId, language);
    }

    @RolesAllowed({"SuperAdmin", "CegAdmin"})
    @GetMapping("/items/{id}")
    public CegAdminItemDTO getOneProduct(@PathVariable("id") String id) throws ValidationException {
        return itemService.findOneProduct(id);
    }
}