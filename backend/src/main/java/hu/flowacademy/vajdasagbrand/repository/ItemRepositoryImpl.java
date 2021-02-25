package hu.flowacademy.vajdasagbrand.repository;

import hu.flowacademy.vajdasagbrand.configuration.persistence.entity.Item;
import hu.flowacademy.vajdasagbrand.configuration.persistence.repository.ItemRepository;
import hu.flowacademy.vajdasagbrand.dto.CegAdminItemDTO;
import hu.flowacademy.vajdasagbrand.dto.ItemDTO;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ItemRepositoryImpl implements CommonItemRepository{

    private final ItemRepository itemRepository;

    public ItemRepositoryImpl(@Lazy ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public ItemDTO save(ItemDTO itemDTO) {
        return itemRepository.save(Item.fromDTO(itemDTO)).toDTO();
    }

    @Override
    public Optional<ItemDTO> findById(String id) {
        return itemRepository.findById(id).map(Item::toDTO);
    }

    @Override
    public Optional<ItemDTO> findFirstById(String id) {
        return itemRepository.findFirstByIdAndDeletedAtNull(id).map(Item::toDTO);
    }

    @Override
    public List<ItemDTO> findAll() {
        return itemRepository.findAll().stream().map(Item::toDTO).collect(Collectors.toList());
    }
}
