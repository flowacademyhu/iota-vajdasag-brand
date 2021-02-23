package hu.flowacademy.vajdasagbrand.repository;

import hu.flowacademy.vajdasagbrand.configuration.persistence.sql.repository.ItemRepository;
import hu.flowacademy.vajdasagbrand.dto.ItemDTO;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ItemRepositoryImpl implements CommonItemRepository{

    private ItemRepository itemRepository;

    public ItemRepositoryImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public ItemDTO save(ItemDTO itemDTO) {
        return itemRepository.save(itemDTO);
    }

    @Override
    public Optional<ItemDTO> findById(String id) {
        return itemRepository.findById(id);
    }

    @Override
    public Optional<ItemDTO> findFirstByIdAndDeletedAtNull(String id) {
        return itemRepository.findFirstByIdAndDeletedAtNull(id);
    }
}
