package hu.flowacademy.vajdasagbrand.repository;

import hu.flowacademy.vajdasagbrand.persistence.entity.Item;
import hu.flowacademy.vajdasagbrand.dto.ItemDTO;
import hu.flowacademy.vajdasagbrand.persistence.repository.ItemJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ItemRepository{

    private final ItemJPARepository itemRepository;


    public ItemDTO save(ItemDTO itemDTO) {
        return itemRepository.save(Item.fromDTO(itemDTO)).toDTO();
    }

    public Optional<ItemDTO> findById(String id) {
        return itemRepository.findById(id).map(Item::toDTO);
    }

    public Optional<ItemDTO> findFirstById(String id) {
        return itemRepository.findFirstByIdAndDeletedAtNull(id).map(Item::toDTO);
    }

    public List<ItemDTO> findAll() {
        return itemRepository.findAll().stream().map(Item::toDTO).collect(Collectors.toList());
    }
}
