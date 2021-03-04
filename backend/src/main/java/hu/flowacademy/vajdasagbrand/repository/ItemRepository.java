package hu.flowacademy.vajdasagbrand.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import hu.flowacademy.vajdasagbrand.dto.SimpleItemDTO;
import hu.flowacademy.vajdasagbrand.persistence.entity.Item;
import hu.flowacademy.vajdasagbrand.dto.ItemDTO;
import hu.flowacademy.vajdasagbrand.persistence.entity.Language;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cyrlat.CyrillicLatinConverter;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ItemRepository {

    public static final String COLLECTION_CITIES = "cities";
    public static final String LOCATION = "location";
    public static final String CITYNAME = "cityname";
    public static final String COLLECTION_CATEGORIES = "categories";
    public static final String NAME = "name";
    public static final String COLLECTION_SUBCATEGORIES = "subcategories";
    public static final String COLLECTION_PLACES = "places";
    public static final String COLLECTION_LANGUAGES = "languages";
    private final Firestore firestore;


    public ItemDTO save(ItemDTO itemDTO) {
        DocumentReference cities = firestore.collection(COLLECTION_CITIES).document(itemDTO.getCity().toLowerCase());
        cities.set(Map.of(LOCATION, new GeoPoint(Double.parseDouble(itemDTO.getCoordinateX()), Double.parseDouble(itemDTO.getCoordinateY()))));
        final String id = Objects.isNull(itemDTO.getId()) ? UUID.randomUUID().toString() : itemDTO.getId();
        itemDTO.getLanguage().forEach((key, value) -> {
            DocumentReference lang = cities.collection(COLLECTION_LANGUAGES).document(key.name());
            lang.set(Map.of(CITYNAME, itemDTO.getCity()));
            DocumentReference categories = lang.collection(COLLECTION_CATEGORIES).document(Integer.toString(itemDTO.getCategory().getIndex()));
            categories.set(Map.of(NAME, itemDTO.getCategory().name())); //TODO translate
            if (itemDTO.getSubcategory() != null) {
                categories = categories.collection(COLLECTION_SUBCATEGORIES).document(Integer.toString(itemDTO.getSubcategory().getIndex()));
                categories.set(Map.of(NAME, itemDTO.getSubcategory().name())); //TODO translate
            }
            Item item = Item.fromDTO(extendDTOAndTranslate(itemDTO, key, value));
            item.setId(id);
            item.setLanguage(key);
            categories.collection(COLLECTION_PLACES).document(item.getId()).set(item);
        });


        return itemDTO;
    }

    private ItemDTO extendDTOAndTranslate(ItemDTO itemDTO, Language key, SimpleItemDTO value) {
        return itemDTO.toBuilder()
                .bio(key.translate(value.getBio()))
                .name(key.translate(value.getName()))
                .website(key.translate(value.getWebsite())).build();
    }

    public Optional<ItemDTO> findById(String id) {
        ApiFuture<QuerySnapshot> collection = firestore.collectionGroup(COLLECTION_PLACES).whereEqualTo("id", id).get();
        try {
            var items = collection.get().getDocuments().stream()
                    .map(queryDocumentSnapshot -> queryDocumentSnapshot.toObject(Item.class))
                    .collect(Collectors.toList());

            return items.stream()
                    .findFirst()
                    .map(Item::toDTO)
                    .map(itemDTO -> itemDTO.toBuilder().language(
                            items.stream()
                                    .collect(Collectors.toMap(Item::getLanguage, this::getSimpleItemDTO))
                    ).build());
        } catch (InterruptedException | ExecutionException e) {
            log.error("No item with given id", e);
            return Optional.empty();
        }
    }

    private SimpleItemDTO getSimpleItemDTO(Item i) {
        return SimpleItemDTO.builder().bio(i.getBio()).name(i.getName()).website(i.getWebsite()).build();
    }

    public Optional<ItemDTO> findFirstById(String id) {
        ApiFuture<QuerySnapshot> collection = firestore.collectionGroup(COLLECTION_PLACES).whereEqualTo("id", id).get();
        try {
            var items = collection.get().getDocuments().stream()
                    .map(queryDocumentSnapshot -> queryDocumentSnapshot.toObject(Item.class))
                    .collect(Collectors.toList());

            return items.stream()
                    .findFirst()
                    .filter(item -> item.getDeletedAt() == null)
                    .map(Item::toDTO)
                    .map(itemDTO -> itemDTO.toBuilder().language(
                            items.stream()
                                    .collect(Collectors.toMap(Item::getLanguage, this::getSimpleItemDTO))
                    ).build());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public List<ItemDTO> findAll(Language language) {
        ApiFuture<QuerySnapshot> collection = firestore.collectionGroup(COLLECTION_PLACES).whereEqualTo("language", language.name()).get();
        try {
            return collection.get().getDocuments()
                    .stream()
                    .map(queryDocumentSnapshot -> queryDocumentSnapshot.toObject(Item.class))
                    .map(Item::toDTO)
                    .collect(Collectors.toList());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public List<ItemDTO> findByOwnerId(String oid, Language language) {
        ApiFuture<QuerySnapshot> collection = firestore.collectionGroup(COLLECTION_PLACES)
                .whereEqualTo("language", language.name())
                .whereEqualTo("ownerId", oid).get();
        try {
            return collection.get().getDocuments().stream()
                    .map(queryDocumentSnapshot -> queryDocumentSnapshot.toObject(Item.class))
                    .map(Item::toDTO)
                    .collect(Collectors.toList());
        } catch (InterruptedException | ExecutionException e) {
            log.error("No item found by given owner id", e);
            return List.of();
        }
    }
}
