package hu.flowacademy.vajdasagbrand.service;

import hu.flowacademy.vajdasagbrand.dto.UserDTO;
import hu.flowacademy.vajdasagbrand.persistence.entity.Category;
import hu.flowacademy.vajdasagbrand.persistence.entity.Subcategory;
import hu.flowacademy.vajdasagbrand.dto.ItemDTO;
import hu.flowacademy.vajdasagbrand.dto.CegAdminItemDTO;
import hu.flowacademy.vajdasagbrand.dto.SuperAdminItemDTO;
import hu.flowacademy.vajdasagbrand.exception.ValidationException;
import hu.flowacademy.vajdasagbrand.persistence.entity.Type;
import hu.flowacademy.vajdasagbrand.repository.ItemRepository;
import hu.flowacademy.vajdasagbrand.repository.UserRepository;
import io.restassured.internal.RestAssuredResponseOptionsImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    private static final String REGISTRATION_ID = "1234L";
    private static final String OWNER_ID = "1234L";
    private static final String NAME = "Something";
    private static final String BIO = "Something useful thing";
    private static final String SCORE = "50";
    private static final String ADDRESS = "6771 Szeged, Makai út 5.";
    private static final String CITY = "Szeged";
    private static final String COORDINATE_X = "21353.35146";
    private static final String COORDINATE_Y = "12154.6485";
    private static final String PHONE = "30/5475520";
    private static final String WEBSITE = "www.hotelglass.com";
    private static final String FACEBOOK = "www.facebook.com/hotelglass";
    private static final String INSTAGRAM = "www.instagram.com/hotelglass";
    private static final String CONTACT = "Kis Pista";
    private static final String EMAIL = "kispista@email.com";
    private static final String OWNER_NAME = "Savoyai Eugen";
    private static final String TAX_NUMBER = "123";
    private static final String USER_PASSWORD = "123456";
    private static final boolean ENABLED_REGISTRATION = true;
    private static final LocalDateTime DELETED_AT = LocalDateTime.of(2015,
            Month.JULY, 29, 19, 30, 40);
    private static final LocalDateTime CREATED_AT = LocalDateTime.of(2014,
            Month.JULY, 29, 19, 30, 40);

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ItemService itemService;

    @Test
    public void givenItem_whenCreatingItem_thenCreatedSuccessfully() throws ValidationException {
        givenItemRepositorySavingItem();
        ItemDTO itemData = givenItem();
        ItemDTO itemResult = itemService.createItem(itemData);
        verify(itemRepository, times(1)).save(itemData);
        verifyNoMoreInteractions(itemRepository);
    }

    @Test
    public void givenExistingItem_whenCallingUpdate_thenItemIsUpdated() throws ValidationException {
        givenExistingItemWhenUpdate();
        ItemDTO item = givenItemWithId();
        ItemDTO updatedItem = itemService.updateItem(item, REGISTRATION_ID);
        verify(itemRepository, times(1)).save(updatedItem);
        assertThat(updatedItem, notNullValue());
        assertThat(updatedItem.getId(), is(item.getId()));
        assertThat(updatedItem.getName(), is(item.getName()));
        assertThat(updatedItem.getBio(), is(item.getBio()));
        assertThat(updatedItem.getScore(), is(item.getScore()));
        assertThat(updatedItem.getAddress(), is(item.getAddress()));
        assertThat(updatedItem.getCity(), is(item.getCity()));
        assertThat(updatedItem.getCategory(), is(item.getCategory()));
        assertThat(updatedItem.getCoordinateX(), is(item.getCoordinateX()));
        assertThat(updatedItem.getCoordinateY(), is(item.getCoordinateY()));
        assertThat(updatedItem.getPhone(), is(item.getPhone()));
        assertThat(updatedItem.getFacebook(), is(item.getFacebook()));
        assertThat(updatedItem.getInstagram(), is(item.getInstagram()));
        verifyNoMoreInteractions(itemRepository);
    }

    @Test
    public void givenItemMissingName_whenCreatingItem_thenExceptionIsThrown() {
        ItemDTO itemData = givenItemMissingName();

        assertThrows(ValidationException.class, () -> itemService.createItem(itemData));
    }

    @Test
    public void givenItemMissingScore_whenCreatingItem_thenExceptionIsThrown() {
        ItemDTO itemData = givenItemMissingScore();

        assertThrows(ValidationException.class, () -> itemService.createItem(itemData));
    }

    @Test
    public void givenItemMissingBio_whenCreatingItem_thenExceptionIsThrown() {
        ItemDTO itemData = givenItemMissingBio();

        assertThrows(ValidationException.class, () -> itemService.createItem(itemData));
    }

    @Test
    public void givenItemMissingAddress_whenCreatingItem_thenExceptionIsThrown() {
        ItemDTO itemData = givenItemMissingAddress();

        assertThrows(ValidationException.class, () -> itemService.createItem(itemData));
    }

    @Test
    public void givenItemMissingCity_whenCreatingItem_thenExceptionIsThrown() {
        ItemDTO itemData = givenItemMissingCity();

        assertThrows(ValidationException.class, () -> itemService.createItem(itemData));
    }

    @Test
    public void givenItemMissingCategory_whenCreatingItem_thenExceptionIsThrown() {
        ItemDTO itemData = givenItemMissingCategory();

        assertThrows(ValidationException.class, () -> itemService.createItem(itemData));
    }

    @Test
    public void givenItemMissingCoordinationX_whenCreatingItem_thenExceptionIsThrown() {
        ItemDTO itemData = givenItemMissingCoordinate_x();

        assertThrows(ValidationException.class, () -> itemService.createItem(itemData));
    }

    @Test
    public void givenItemMissingCoordinationY_whenCreatingItem_thenExceptionIsThrown() {
        ItemDTO itemData = givenItemMissingCoordinate_y();

        assertThrows(ValidationException.class, () -> itemService.createItem(itemData));
    }

    @Test
    public void givenItemMissingPhone_whenCreatingItem_thenExceptionIsThrown() {
        ItemDTO itemData = givenItemMissingPhone();

        assertThrows(ValidationException.class, () -> itemService.createItem(itemData));
    }

    @Test
    public void givenItemMissingWebsite_whenCreatingItem_thenExceptionIsThrown() {
        ItemDTO itemData = givenItemMissingWebsite();

        assertThrows(ValidationException.class, () -> itemService.createItem(itemData));
    }

    @Test
    public void givenItemMissingFacebook_whenCreatingItem_thenExceptionIsThrown() {
        ItemDTO itemData = givenItemMissingFacebook();

        assertThrows(ValidationException.class, () -> itemService.createItem(itemData));
    }

    @Test
    public void givenItemMissingInstagram_whenCreatingItem_thenExceptionIsThrown() {
        ItemDTO itemData = givenItemMissingInstagram();

        assertThrows(ValidationException.class, () -> itemService.createItem(itemData));
    }

    @Test
    public void givenExistingItem_whenCallingDelete_thenItemDeletedSuccessfully() throws ValidationException {
        givenItemRepositoryWhenCallingDelete();
        ItemDTO deleted = itemService.deleteById(REGISTRATION_ID);
        verify(itemRepository, times(1)).findFirstById(REGISTRATION_ID);
        verify(itemRepository, times(1)).save(deleted);
        verifyNoMoreInteractions(itemRepository);

        assertThat(deleted, notNullValue());
        assertNotNull(deleted.getDeletedAt());
    }

    @Test
    public void givenUnExistingId_whenCallingDelete_thenExceptionIsThrown() {
        givenItem();

        assertThrows(ValidationException.class, () -> itemService.deleteById(REGISTRATION_ID));
    }

    @Test
    public void givenItemMissingName_whenUpdatingItem_thenExceptionIsThrown() {
        ItemDTO itemData = givenItemMissingName();

        assertThrows(ValidationException.class, () -> itemService.updateItem(itemData, UUID.randomUUID().toString()));
    }

    @Test
    public void givenItemMissingScore_whenUpdatingItem_thenExceptionIsThrown() {
        ItemDTO itemData = givenItemMissingScore();

        assertThrows(ValidationException.class, () -> itemService.updateItem(itemData, UUID.randomUUID().toString()));
    }

    @Test
    public void givenItemMissingBio_whenUpdatingItem_thenExceptionIsThrown() {
        ItemDTO itemData = givenItemMissingBio();

        assertThrows(ValidationException.class, () -> itemService.updateItem(itemData, UUID.randomUUID().toString()));
    }

    @Test
    public void givenItemMissingAddress_whenUpdatingItem_thenExceptionIsThrown() {
        ItemDTO itemData = givenItemMissingAddress();

        assertThrows(ValidationException.class, () -> itemService.updateItem(itemData, UUID.randomUUID().toString()));
    }

    @Test
    public void givenItemMissingCity_whenUpdatingItem_thenExceptionIsThrown() {
        ItemDTO itemData = givenItemMissingCity();

        assertThrows(ValidationException.class, () -> itemService.updateItem(itemData, UUID.randomUUID().toString()));
    }

    @Test
    public void givenItemMissingCategory_whenUpdatingItem_thenExceptionIsThrown() {
        ItemDTO itemData = givenItemMissingCategory();

        assertThrows(ValidationException.class, () -> itemService.updateItem(itemData, UUID.randomUUID().toString()));
    }

    @Test
    public void givenItemMissingCoordinateX_whenUpdatingItem_thenExceptionIsThrown() {
        ItemDTO itemData = givenItemMissingCoordinate_x();

        assertThrows(ValidationException.class, () -> itemService.updateItem(itemData, UUID.randomUUID().toString()));
    }

    @Test
    public void givenItemMissingCoordinateY_whenUpdatingItem_thenExceptionIsThrown() {
        ItemDTO itemData = givenItemMissingCoordinate_y();

        assertThrows(ValidationException.class, () -> itemService.updateItem(itemData, UUID.randomUUID().toString()));
    }

    @Test
    public void givenItemMissingPhone_whenUpdatingItem_thenExceptionIsThrown() {
        ItemDTO itemData = givenItemMissingPhone();

        assertThrows(ValidationException.class, () -> itemService.updateItem(itemData, UUID.randomUUID().toString()));
    }

    @Test
    public void givenItemMissingWebsite_whenUpdatingItem_thenExceptionIsThrown() {
        ItemDTO itemData = givenItemMissingWebsite();

        assertThrows(ValidationException.class, () -> itemService.updateItem(itemData, UUID.randomUUID().toString()));
    }

    @Test
    public void givenItemMissingFacebook_whenUpdatingItem_thenExceptionIsThrown() {
        ItemDTO itemData = givenItemMissingFacebook();

        assertThrows(ValidationException.class, () -> itemService.updateItem(itemData, UUID.randomUUID().toString()));
    }

    @Test
    public void givenItemMissingInstagram_whenUpdatingItem_thenExceptionIsThrown() {
        ItemDTO itemData = givenItemMissingInstagram();

        assertThrows(ValidationException.class, () -> itemService.updateItem(itemData, UUID.randomUUID().toString()));
    }

    @Test
    public void givenNonExistingAuthorization_whenListingItems_thenExceptionIsThrown() {
        Optional<Authentication> authentication = Optional.empty();
        Optional<String> ownerId = Optional.empty();
        assertThrows(ValidationException.class, () -> itemService.listProducts(authentication, ownerId));
    }

    @Test
    public void givenUnauthorizedUser_whenListingItems_thenExceptionIsThrown() {
        assertThrows(ValidationException.class, () -> itemService
                .listProducts(givenUnauthorizedUserListingItems(), Optional.of(OWNER_ID)));
    }
    @Disabled
    @Test
    public void givenSuperAdmin_whenListingItems_thenSuperAdminDtoIsReturned() throws ValidationException {
        givenItemRepositoryListingItems();
        givenUserRepositoryListingItems();

        assertThat(itemService.listProducts(givenSuperAdminListingItems(), Optional.of(OWNER_ID)), is(givenSuperAdminItemDtoList()));
        verify(itemRepository, times(1)).findAll();
        verify(itemRepository, times(1)).findByOwnerId(OWNER_ID);
        verifyNoMoreInteractions(itemRepository);
    }
    @Disabled
    @Test
    public void givenCegAdmin_whenListingItems_thenCegAdminDtoIsReturned() throws ValidationException {
        givenItemRepositoryListingItems();

        assertThat(itemService.listProducts(givenCegAdminListingItems(), Optional.of(OWNER_ID)), is(givenCegAdminItemDtoList()));
        verify(itemRepository, times(1)).findAll();
        verify(itemRepository, times(1)).findByOwnerId(OWNER_ID);
        verifyNoMoreInteractions(itemRepository);
    }

    @Test
    public void givenItemRepository_whenFindingOneItem_thenCegAdminDtoIsReturned() throws ValidationException {
        givenItemRepositoryFindingOneItemById();

        assertThat(itemService.findOneProduct(REGISTRATION_ID), is(givenCegAdminItemDTO()));
        verify(itemRepository, times(1)).findById(REGISTRATION_ID);
        verifyNoMoreInteractions(itemRepository);
    }

    @Test
    public void givenFalseId_whenFindingOneItem_thenExceptionIsThrown() {
        givenItemRepositoryNotFindingAnItemById();

        assertThrows(ValidationException.class, () -> itemService
                .findOneProduct(REGISTRATION_ID));
    }

    private void givenItemRepositoryListingItems() {
        when(itemRepository.findAll()).thenReturn(List.of(givenItemWithDeletedAt()));
        when(itemRepository.findByOwnerId(OWNER_ID)).thenReturn(List.of(givenItemWithDeletedAt()));
    }

    public void givenItemRepositoryFindingOneItemById() {
        when(itemRepository.findById(REGISTRATION_ID)).thenReturn(Optional.of(givenItemWithDeletedAt()));
    }

    public void givenItemRepositoryNotFindingAnItemById() {
        when(itemRepository.findById(REGISTRATION_ID)).thenReturn(Optional.empty());
    }

    private void givenUserRepositoryListingItems() {
        when(userRepository.findById(OWNER_ID)).thenReturn(Optional.of(givenUser()));
    }

    private Optional<Authentication> givenUnauthorizedUserListingItems() {
        return Optional.of(new UsernamePasswordAuthenticationToken("", "", List.of(new SimpleGrantedAuthority("ROLE_User"))));
    }

    private Optional<Authentication> givenSuperAdminListingItems() {
        return Optional.of(new UsernamePasswordAuthenticationToken("", "", List.of(new SimpleGrantedAuthority("ROLE_SuperAdmin"))));
    }

    private Optional<Authentication> givenCegAdminListingItems() {
        return Optional.of(new UsernamePasswordAuthenticationToken("", "", List.of(new SimpleGrantedAuthority("ROLE_CegAdmin"))));
    }

    private Optional<String> givenCegAdminListingItemsWithOwnerId() {
        return Optional.of(OWNER_ID);
    }

    private List<SuperAdminItemDTO> givenSuperAdminItemDtoList() {
        return List.of(new SuperAdminItemDTO(REGISTRATION_ID, NAME, SCORE, BIO, ADDRESS, CONTACT, CITY, EMAIL, Category.ATTRACTION, Subcategory.FAMOUS_BUILDINGS, COORDINATE_X, COORDINATE_Y, PHONE, WEBSITE, FACEBOOK, INSTAGRAM, DELETED_AT, OWNER_ID, OWNER_NAME));
    }
    private List<CegAdminItemDTO> givenCegAdminItemDtoList() {
        return List.of(new CegAdminItemDTO(REGISTRATION_ID, NAME, SCORE, BIO, ADDRESS, CONTACT, CITY, EMAIL, Category.ATTRACTION, Subcategory.FAMOUS_BUILDINGS, COORDINATE_X, COORDINATE_Y, PHONE, WEBSITE, FACEBOOK, INSTAGRAM, DELETED_AT, OWNER_ID));
    }

    private CegAdminItemDTO givenCegAdminItemDTO() {
        return new CegAdminItemDTO(REGISTRATION_ID, NAME, SCORE, BIO, ADDRESS, CONTACT, CITY, EMAIL, Category.ATTRACTION, Subcategory.FAMOUS_BUILDINGS, COORDINATE_X, COORDINATE_Y, PHONE, WEBSITE, FACEBOOK, INSTAGRAM, DELETED_AT, OWNER_ID);
    }

    @Test
    public void givenItemMissingContact_whenUpdatingItem_thenExceptionIsThrown() {
        ItemDTO item = givenItemMissingContact();

        assertThrows(ValidationException.class, () -> itemService.updateItem(item, UUID.randomUUID().toString()));
    }

    @Test
    public void givenItemMissingEmail_whenUpdatingItem_thenExceptionIsThrown() {
        ItemDTO item = givenItemMissingEmail();

        assertThrows(ValidationException.class, () -> itemService.updateItem(item, UUID.randomUUID().toString()));
    }

    @Test
    public void givenItemMissingSubcategory_whenUpdatingItem_thenExceptionIsThrown() {
        ItemDTO item = givenItemMissingSubcategory();

        assertThrows(ValidationException.class, () -> itemService.updateItem(item, UUID.randomUUID().toString()));
    }

    @Test
    public void givenItemMissingOwnerId_whenCreatingItem_thenExceptionIsThrown() {
        ItemDTO item = givenItemMissingOwnerId();

        assertThrows(ValidationException.class, () -> itemService.createItem(item));
    }

    private void givenItemRepositorySavingItem() {
        when(itemRepository.save(any(ItemDTO.class))).thenAnswer(invocationOnMock -> {
            ItemDTO created = invocationOnMock.getArgument(0);
            created.setId(REGISTRATION_ID);
            return created;
        });
    }

    private void givenItemRepositoryWhenCallingDelete() {
        ItemDTO itemToBeDeleted = givenItem();
        itemToBeDeleted.setId(REGISTRATION_ID);
        when(itemRepository.findFirstById(anyString())).thenReturn(Optional.of(itemToBeDeleted));
        when(itemRepository.save(any(ItemDTO.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
    }

    private UserDTO givenUser() {
        return new UserDTO(OWNER_ID, OWNER_NAME, TAX_NUMBER, ADDRESS, EMAIL, Type.INDIVIDUAL, USER_PASSWORD, ENABLED_REGISTRATION, CREATED_AT, DELETED_AT);
    }

    private ItemDTO givenItem() {
        ItemDTO item = new ItemDTO();
        item.setName(NAME);
        item.setScore(SCORE);
        item.setContact(CONTACT);
        item.setBio(BIO);
        item.setAddress(ADDRESS);
        item.setCity(CITY);
        item.setCategory(Category.ATTRACTION);
        item.setCoordinateX(COORDINATE_X);
        item.setCoordinateY(COORDINATE_Y);
        item.setPhone(PHONE);
        item.setEmail(EMAIL);
        item.setSubcategory(Subcategory.HONOURABLES);
        item.setWebsite(WEBSITE);
        item.setFacebook(FACEBOOK);
        item.setInstagram(INSTAGRAM);
        item.setOwnerId(OWNER_ID);

        return item;
    }

    private ItemDTO givenItemWithId() {
        ItemDTO item = new ItemDTO();
        item.setName(NAME);
        item.setId(REGISTRATION_ID);
        item.setScore(SCORE);
        item.setContact(CONTACT);
        item.setBio(BIO);
        item.setAddress(ADDRESS);
        item.setCity(CITY);
        item.setCategory(Category.ATTRACTION);
        item.setCoordinateX(COORDINATE_X);
        item.setCoordinateY(COORDINATE_Y);
        item.setEmail(EMAIL);
        item.setSubcategory(Subcategory.HONOURABLES);
        item.setPhone(PHONE);
        item.setWebsite(WEBSITE);
        item.setFacebook(FACEBOOK);
        item.setInstagram(INSTAGRAM);
        item.setOwnerId(OWNER_ID);

        return item;
    }

    private ItemDTO givenItemWithDeletedAt() {
        ItemDTO item = new ItemDTO();

        item.setName(NAME);
        item.setId(REGISTRATION_ID);
        item.setScore(SCORE);
        item.setBio(BIO);
        item.setAddress(ADDRESS);
        item.setContact(CONTACT);
        item.setCity(CITY);
        item.setEmail(EMAIL);
        item.setCategory(Category.ATTRACTION);
        item.setSubcategory(Subcategory.FAMOUS_BUILDINGS);
        item.setCoordinateX(COORDINATE_X);
        item.setCoordinateY(COORDINATE_Y);
        item.setPhone(PHONE);
        item.setWebsite(WEBSITE);
        item.setFacebook(FACEBOOK);
        item.setInstagram(INSTAGRAM);
        item.setDeletedAt(DELETED_AT);
        item.setOwnerId(OWNER_ID);

        return item;
    }

    private ItemDTO givenItemMissingName() {
        ItemDTO item = new ItemDTO();
        item.setScore(SCORE);
        item.setBio(BIO);
        item.setAddress(ADDRESS);
        item.setContact(CONTACT);
        item.setCity(CITY);
        item.setCategory(Category.ATTRACTION);
        item.setCoordinateX(COORDINATE_X);
        item.setCoordinateY(COORDINATE_Y);
        item.setEmail(EMAIL);
        item.setSubcategory(Subcategory.HONOURABLES);
        item.setPhone(PHONE);
        item.setWebsite(WEBSITE);
        item.setFacebook(FACEBOOK);
        item.setInstagram(INSTAGRAM);
        item.setOwnerId(OWNER_ID);
        return item;
    }

    private ItemDTO givenItemMissingScore() {
        ItemDTO item = new ItemDTO();
        item.setName(NAME);
        item.setBio(BIO);
        item.setAddress(ADDRESS);
        item.setCity(CITY);
        item.setContact(CONTACT);
        item.setCategory(Category.ATTRACTION);
        item.setCoordinateX(COORDINATE_X);
        item.setCoordinateY(COORDINATE_Y);
        item.setEmail(EMAIL);
        item.setSubcategory(Subcategory.HONOURABLES);
        item.setPhone(PHONE);
        item.setWebsite(WEBSITE);
        item.setFacebook(FACEBOOK);
        item.setInstagram(INSTAGRAM);
        item.setOwnerId(OWNER_ID);
        return item;
    }

    private ItemDTO givenItemMissingBio() {
        ItemDTO item = new ItemDTO();
        item.setName(NAME);
        item.setScore(SCORE);
        item.setAddress(ADDRESS);
        item.setCity(CITY);
        item.setContact(CONTACT);
        item.setCategory(Category.ATTRACTION);
        item.setCoordinateX(COORDINATE_X);
        item.setCoordinateY(COORDINATE_Y);
        item.setEmail(EMAIL);
        item.setSubcategory(Subcategory.HONOURABLES);
        item.setPhone(PHONE);
        item.setWebsite(WEBSITE);
        item.setFacebook(FACEBOOK);
        item.setInstagram(INSTAGRAM);
        item.setOwnerId(OWNER_ID);
        return item;
    }

    private ItemDTO givenItemMissingAddress() {
        ItemDTO item = new ItemDTO();
        item.setName(NAME);
        item.setScore(SCORE);
        item.setBio(BIO);
        item.setCity(CITY);
        item.setContact(CONTACT);
        item.setCategory(Category.ATTRACTION);
        item.setCoordinateX(COORDINATE_X);
        item.setCoordinateY(COORDINATE_Y);
        item.setEmail(EMAIL);
        item.setSubcategory(Subcategory.HONOURABLES);
        item.setPhone(PHONE);
        item.setWebsite(WEBSITE);
        item.setFacebook(FACEBOOK);
        item.setInstagram(INSTAGRAM);
        item.setOwnerId(OWNER_ID);
        return item;
    }

    private ItemDTO givenItemMissingCity() {
        ItemDTO item = new ItemDTO();
        item.setName(NAME);
        item.setScore(SCORE);
        item.setBio(BIO);
        item.setAddress(ADDRESS);
        item.setContact(CONTACT);
        item.setCategory(Category.ATTRACTION);
        item.setCoordinateX(COORDINATE_X);
        item.setCoordinateY(COORDINATE_Y);
        item.setEmail(EMAIL);
        item.setSubcategory(Subcategory.HONOURABLES);
        item.setPhone(PHONE);
        item.setWebsite(WEBSITE);
        item.setFacebook(FACEBOOK);
        item.setInstagram(INSTAGRAM);
        item.setOwnerId(OWNER_ID);
        return item;
    }

    private ItemDTO givenItemMissingCategory() {
        ItemDTO item = new ItemDTO();
        item.setName(NAME);
        item.setScore(SCORE);
        item.setBio(BIO);
        item.setAddress(ADDRESS);
        item.setContact(CONTACT);
        item.setCity(CITY);
        item.setCoordinateX(COORDINATE_X);
        item.setCoordinateY(COORDINATE_Y);
        item.setEmail(EMAIL);
        item.setSubcategory(Subcategory.HONOURABLES);
        item.setPhone(PHONE);
        item.setWebsite(WEBSITE);
        item.setFacebook(FACEBOOK);
        item.setInstagram(INSTAGRAM);
        item.setOwnerId(OWNER_ID);
        return item;
    }

    private ItemDTO givenItemMissingCoordinate_x() {
        ItemDTO item = new ItemDTO();
        item.setName(NAME);
        item.setScore(SCORE);
        item.setBio(BIO);
        item.setAddress(ADDRESS);
        item.setContact(CONTACT);
        item.setCity(CITY);
        item.setCategory(Category.ATTRACTION);
        item.setCoordinateY(COORDINATE_Y);
        item.setEmail(EMAIL);
        item.setSubcategory(Subcategory.HONOURABLES);
        item.setPhone(PHONE);
        item.setWebsite(WEBSITE);
        item.setFacebook(FACEBOOK);
        item.setInstagram(INSTAGRAM);
        item.setOwnerId(OWNER_ID);
        return item;
    }

    private ItemDTO givenItemMissingCoordinate_y() {
        ItemDTO item = new ItemDTO();
        item.setName(NAME);
        item.setScore(SCORE);
        item.setBio(BIO);
        item.setAddress(ADDRESS);
        item.setCity(CITY);
        item.setContact(CONTACT);
        item.setCategory(Category.ATTRACTION);
        item.setCoordinateX(COORDINATE_X);
        item.setEmail(EMAIL);
        item.setSubcategory(Subcategory.HONOURABLES);
        item.setPhone(PHONE);
        item.setWebsite(WEBSITE);
        item.setFacebook(FACEBOOK);
        item.setInstagram(INSTAGRAM);
        item.setOwnerId(OWNER_ID);
        return item;
    }

    private ItemDTO givenItemMissingPhone() {
        ItemDTO item = new ItemDTO();
        item.setName(NAME);
        item.setScore(SCORE);
        item.setBio(BIO);
        item.setContact(CONTACT);
        item.setAddress(ADDRESS);
        item.setCity(CITY);
        item.setCategory(Category.ATTRACTION);
        item.setCoordinateX(COORDINATE_X);
        item.setCoordinateY(COORDINATE_Y);
        item.setEmail(EMAIL);
        item.setSubcategory(Subcategory.HONOURABLES);
        item.setWebsite(WEBSITE);
        item.setFacebook(FACEBOOK);
        item.setInstagram(INSTAGRAM);
        item.setOwnerId(OWNER_ID);
        return item;
    }

    private ItemDTO givenItemMissingWebsite() {
        ItemDTO item = new ItemDTO();
        item.setName(NAME);
        item.setScore(SCORE);
        item.setBio(BIO);
        item.setContact(CONTACT);
        item.setAddress(ADDRESS);
        item.setCity(CITY);
        item.setCategory(Category.ATTRACTION);
        item.setCoordinateX(COORDINATE_X);
        item.setCoordinateY(COORDINATE_Y);
        item.setEmail(EMAIL);
        item.setSubcategory(Subcategory.HONOURABLES);
        item.setPhone(PHONE);
        item.setFacebook(FACEBOOK);
        item.setInstagram(INSTAGRAM);
        item.setOwnerId(OWNER_ID);
        return item;
    }

    private ItemDTO givenItemMissingFacebook() {
        ItemDTO item = new ItemDTO();
        item.setName(NAME);
        item.setScore(SCORE);
        item.setBio(BIO);
        item.setAddress(ADDRESS);
        item.setCity(CITY);
        item.setContact(CONTACT);
        item.setCategory(Category.ATTRACTION);
        item.setCoordinateX(COORDINATE_X);
        item.setCoordinateY(COORDINATE_Y);
        item.setEmail(EMAIL);
        item.setSubcategory(Subcategory.HONOURABLES);
        item.setPhone(PHONE);
        item.setWebsite(WEBSITE);
        item.setInstagram(INSTAGRAM);
        item.setOwnerId(OWNER_ID);
        return item;
    }

    private ItemDTO givenItemMissingInstagram() {
        ItemDTO item = new ItemDTO();
        item.setName(NAME);
        item.setScore(SCORE);
        item.setBio(BIO);
        item.setAddress(ADDRESS);
        item.setCity(CITY);
        item.setContact(CONTACT);
        item.setCategory(Category.ATTRACTION);
        item.setCoordinateX(COORDINATE_X);
        item.setCoordinateY(COORDINATE_Y);
        item.setEmail(EMAIL);
        item.setSubcategory(Subcategory.HONOURABLES);
        item.setPhone(PHONE);
        item.setWebsite(WEBSITE);
        item.setFacebook(FACEBOOK);
        item.setOwnerId(OWNER_ID);
        return item;
    }

    private ItemDTO givenItemMissingContact() {
        ItemDTO item = new ItemDTO();
        item.setName(NAME);
        item.setScore(SCORE);
        item.setBio(BIO);
        item.setAddress(ADDRESS);
        item.setCity(CITY);
        item.setCategory(Category.ATTRACTION);
        item.setCoordinateX(COORDINATE_X);
        item.setCoordinateY(COORDINATE_Y);
        item.setEmail(EMAIL);
        item.setSubcategory(Subcategory.HONOURABLES);
        item.setPhone(PHONE);
        item.setWebsite(WEBSITE);
        item.setInstagram(INSTAGRAM);
        item.setFacebook(FACEBOOK);
        item.setOwnerId(OWNER_ID);
        return item;
    }

    private ItemDTO givenItemMissingEmail() {
        ItemDTO item = new ItemDTO();
        item.setName(NAME);
        item.setScore(SCORE);
        item.setBio(BIO);
        item.setAddress(ADDRESS);
        item.setCity(CITY);
        item.setCategory(Category.ATTRACTION);
        item.setCoordinateX(COORDINATE_X);
        item.setCoordinateY(COORDINATE_Y);
        item.setSubcategory(Subcategory.HONOURABLES);
        item.setPhone(PHONE);
        item.setWebsite(WEBSITE);
        item.setInstagram(INSTAGRAM);
        item.setFacebook(FACEBOOK);
        item.setOwnerId(OWNER_ID);
        return item;
    }

    private ItemDTO givenItemMissingSubcategory() {
        ItemDTO item = new ItemDTO();
        item.setName(NAME);
        item.setScore(SCORE);
        item.setBio(BIO);
        item.setAddress(ADDRESS);
        item.setCity(CITY);
        item.setCategory(Category.ATTRACTION);
        item.setCoordinateX(COORDINATE_X);
        item.setCoordinateY(COORDINATE_Y);
        item.setEmail(EMAIL);
        item.setPhone(PHONE);
        item.setWebsite(WEBSITE);
        item.setInstagram(INSTAGRAM);
        item.setFacebook(FACEBOOK);
        item.setOwnerId(OWNER_ID);
        return item;
    }

    private ItemDTO givenItemMissingOwnerId() {
        ItemDTO item = new ItemDTO();
        item.setName(NAME);
        item.setScore(SCORE);
        item.setBio(BIO);
        item.setAddress(ADDRESS);
        item.setContact(CONTACT);
        item.setCity(CITY);
        item.setCategory(Category.ATTRACTION);
        item.setCoordinateX(COORDINATE_X);
        item.setCoordinateY(COORDINATE_Y);
        item.setEmail(EMAIL);
        item.setSubcategory(Subcategory.HONOURABLES);
        item.setPhone(PHONE);
        item.setWebsite(WEBSITE);
        item.setFacebook(FACEBOOK);
        item.setInstagram(INSTAGRAM);
        return item;
    }

    private void givenExistingItemWhenUpdate() {
        ItemDTO item = givenItem();
        item.setId(REGISTRATION_ID);
        when(itemRepository.findById(REGISTRATION_ID)).thenReturn(Optional.of(item));
        when(itemRepository.save(any(ItemDTO.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
    }
}