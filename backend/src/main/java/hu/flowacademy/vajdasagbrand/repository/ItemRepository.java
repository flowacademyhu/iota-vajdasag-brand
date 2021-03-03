package hu.flowacademy.vajdasagbrand.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import hu.flowacademy.vajdasagbrand.persistence.entity.Item;
import hu.flowacademy.vajdasagbrand.dto.ItemDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ItemRepository{

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
        cities.set(Map.of(LOCATION, new GeoPoint(Double.parseDouble(itemDTO.getCoordinateX()),Double.parseDouble(itemDTO.getCoordinateY()))));
        DocumentReference lang = cities.collection(COLLECTION_LANGUAGES).document("hu");
        lang.set(Map.of(CITYNAME, itemDTO.getCity()));
        DocumentReference categories = lang.collection(COLLECTION_CATEGORIES).document(Integer.toString(itemDTO.getCategory().getIndex()));
        categories.set(Map.of(NAME, itemDTO.getCategory().name())); //TODO translate
        if (itemDTO.getSubcategory() != null) {
            categories = categories.collection(COLLECTION_SUBCATEGORIES).document("1");
            categories.set(Map.of(NAME, itemDTO.getSubcategory().name())); //TODO translate
        }
        Item item = Item.fromDTO(itemDTO);
        item.setId(UUID.randomUUID().toString());
        categories.collection(COLLECTION_PLACES).document(item.getId()).set(item);
        return item.toDTO();
    }

    public Optional<ItemDTO> findById(String id) {
        ApiFuture<QuerySnapshot> collection = firestore.collectionGroup(COLLECTION_CITIES).whereEqualTo("id", id).get();
        try {
            return collection.get().getDocuments().stream().map(queryDocumentSnapshot -> queryDocumentSnapshot.toObject(Item.class))
                    .map(Item::toDTO)
                    .findFirst();
        } catch (InterruptedException | ExecutionException e) {
            log.error("No item with given id", e);
            return Optional.empty();
        }
    }

    public Optional<ItemDTO> findFirstById(String id) {
        ApiFuture<QuerySnapshot> collection = firestore.collection(COLLECTION_PLACES).whereEqualTo("id", id).get();
        try {
            return collection.get().getDocuments().stream()
                    .map(queryDocumentSnapshot -> queryDocumentSnapshot.toObject(Item.class))
                    .findFirst()
                    .filter(item -> item.getDeletedAt() != null)
                    .map(Item::toDTO);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public List<ItemDTO> findAll() {
        ApiFuture<QuerySnapshot> collection = firestore.collectionGroup(COLLECTION_PLACES).get();
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
}
