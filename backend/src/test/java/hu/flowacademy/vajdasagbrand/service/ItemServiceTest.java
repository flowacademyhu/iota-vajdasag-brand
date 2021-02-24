package hu.flowacademy.vajdasagbrand.service;

import hu.flowacademy.vajdasagbrand.dto.CegAdminItemDTO;
import hu.flowacademy.vajdasagbrand.dto.SuperAdminItemDTO;
import hu.flowacademy.vajdasagbrand.entity.Category;
import hu.flowacademy.vajdasagbrand.entity.Item;
import hu.flowacademy.vajdasagbrand.exception.ValidationException;
import hu.flowacademy.vajdasagbrand.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

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
    private static final String NAME = "Something";
    private static final String BIO = "Something useful thing";
    private static final int SCORE = 50;
    private static final int SCOREUPDATE = -50;
    private static final String ADDRESS = "6771 Szeged, Makai út 5.";
    private static final String CITY = "Szeged";
    private static final String COORDINATE_X = "21353.35146";
    private static final String COORDINATE_Y = "12154.6485";
    private static final String PHONE = "30/5475520";
    private static final String WEBSITE = "www.hotelglass.com";
    private static final String FACEBOOK = "www.facebook.com/hotelglass";
    private static final String INSTAGRAM = "www.instagram.com/hotelglass";
    private static final String OWNER = "Something";

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService itemService;

    @Test
    public void givenItem_whenCreatingItem_thenCreatedSuccessfully() throws ValidationException {
        givenItemRepositorySavingItem();
        Item itemData = givenItem();
        Item itemResult = itemService.createItem(itemData);
        verify(itemRepository, times(1)).save(itemData);
        verifyNoMoreInteractions(itemRepository);
    }

    @Test
    public void givenNewWorksheetObject_whenUpdateWorksheet_thenWorksheetUpdated() throws ValidationException {
        givenExistingItemWhenUpdate();
        Item item = givenItemWithId();
        Item updatedWorksheet = itemService.updateItem(item, REGISTRATION_ID);
        verify(itemRepository, times(1)).save(updatedWorksheet);
        assertThat(updatedWorksheet, notNullValue());
        assertThat(updatedWorksheet.getId(), is(item.getId()));
        assertThat(updatedWorksheet.getName(), is(item.getName()));
        assertThat(updatedWorksheet.getBio(), is(item.getBio()));
        assertThat(updatedWorksheet.getScore(), is(item.getScore()));
        assertThat(updatedWorksheet.getAddress(), is(item.getAddress()));
        assertThat(updatedWorksheet.getCity(), is(item.getCity()));
        assertThat(updatedWorksheet.getCategory(), is(item.getCategory()));
        assertThat(updatedWorksheet.getCoordinateX(), is(item.getCoordinateX()));
        assertThat(updatedWorksheet.getCoordinateY(), is(item.getCoordinateY()));
        assertThat(updatedWorksheet.getPhone(), is(item.getPhone()));
        assertThat(updatedWorksheet.getFacebook(), is(item.getFacebook()));
        assertThat(updatedWorksheet.getInstagram(), is(item.getInstagram()));
        verifyNoMoreInteractions(itemRepository);
    }

    @Test
    public void givenItemMissingName_whenCreatingItem_thenExceptionIsThrown() {
        Item itemData = givenItemMissingName();

        assertThrows(ValidationException.class, () -> itemService.createItem(itemData));
    }

    @Test
    public void givenItemMissingScore_whenCreatingItem_thenExceptionIsThrown() {
        Item itemData = givenItemMissingScore();

        assertThrows(ValidationException.class, () -> itemService.createItem(itemData));
    }

    @Test
    public void givenItemMissingBio_whenCreatingItem_thenExceptionIsThrown() {
        Item itemData = givenItemMissingBio();

        assertThrows(ValidationException.class, () -> itemService.createItem(itemData));
    }

    @Test
    public void givenItemMissingAddress_whenCreatingItem_thenExceptionIsThrown() {
        Item itemData = givenItemMissingAddress();

        assertThrows(ValidationException.class, () -> itemService.createItem(itemData));
    }

    @Test
    public void givenItemMissingCity_whenCreatingItem_thenExceptionIsThrown() {
        Item itemData = givenItemMissingCity();

        assertThrows(ValidationException.class, () -> itemService.createItem(itemData));
    }

    @Test
    public void givenItemMissingCategory_whenCreatingItem_thenExceptionIsThrown() {
        Item itemData = givenItemMissingCategory();

        assertThrows(ValidationException.class, () -> itemService.createItem(itemData));
    }

    @Test
    public void givenItemMissingCoordinationX_whenCreatingItem_thenExceptionIsThrown() {
        Item itemData = givenItemMissingCoordinate_x();

        assertThrows(ValidationException.class, () -> itemService.createItem(itemData));
    }

    @Test
    public void givenItemMissingCoordinationY_whenCreatingItem_thenExceptionIsThrown() {
        Item itemData = givenItemMissingCoordinate_y();

        assertThrows(ValidationException.class, () -> itemService.createItem(itemData));
    }

    @Test
    public void givenItemMissingPhone_whenCreatingItem_thenExceptionIsThrown() {
        Item itemData = givenItemMissingPhone();

        assertThrows(ValidationException.class, () -> itemService.createItem(itemData));
    }

    @Test
    public void givenItemMissingWebsite_whenCreatingItem_thenExceptionIsThrown() throws ValidationException {
        Item itemData = givenItemMissingWebsite();

        assertThrows(ValidationException.class, () -> itemService.createItem(itemData));
    }

    @Test
    public void givenItemMissingFacebook_whenCreatingItem_thenExceptionIsThrown() {
        Item itemData = givenItemMissingFacebook();

        assertThrows(ValidationException.class, () -> itemService.createItem(itemData));
    }

    @Test
    public void givenItemMissingInstagram_whenCreatingItem_thenExceptionIsThrown() {
        Item itemData = givenItemMissingInstagram();

        assertThrows(ValidationException.class, () -> itemService.createItem(itemData));
    }

    @Test
    public void givenExistingItem_whenCallingDelete_thenItemDeletedSuccessfully() throws ValidationException {
        givenItemRepositoryWhenCallingDelete();
        Item deleted = itemService.deleteById(REGISTRATION_ID);
        verify(itemRepository, times(1)).findFirstByIdAndDeletedAtNull(REGISTRATION_ID);
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
        Item itemData = givenItemMissingName();

        assertThrows(ValidationException.class, () -> itemService.updateItem(itemData, UUID.randomUUID().toString()));
    }

    @Test
    public void givenItemMissingScore_whenUpdatingItem_thenExceptionIsThrown() {
        Item itemData = givenItemMissingScore();

        assertThrows(ValidationException.class, () -> itemService.updateItem(itemData, UUID.randomUUID().toString()));
    }

    @Test
    public void givenItemMissingBio_whenUpdatingItem_thenExceptionIsThrown() {
        Item itemData = givenItemMissingBio();

        assertThrows(ValidationException.class, () -> itemService.updateItem(itemData, UUID.randomUUID().toString()));
    }

    @Test
    public void givenItemMissingAddress_whenUpdatingItem_thenExceptionIsThrown() {
        Item itemData = givenItemMissingAddress();

        assertThrows(ValidationException.class, () -> itemService.updateItem(itemData, UUID.randomUUID().toString()));
    }

    @Test
    public void givenItemMissingCity_whenUpdatingItem_thenExceptionIsThrown() {
        Item itemData = givenItemMissingCity();

        assertThrows(ValidationException.class, () -> itemService.updateItem(itemData, UUID.randomUUID().toString()));
    }

    @Test
    public void givenItemMissingCategory_whenUpdatingItem_thenExceptionIsThrown() {
        Item itemData = givenItemMissingCategory();

        assertThrows(ValidationException.class, () -> itemService.updateItem(itemData, UUID.randomUUID().toString()));
    }

    @Test
    public void givenItemMissingCoordinateX_whenUpdatingItem_thenExceptionIsThrown() {
        Item itemData = givenItemMissingCoordinate_x();

        assertThrows(ValidationException.class, () -> itemService.updateItem(itemData, UUID.randomUUID().toString()));
    }

    @Test
    public void givenItemMissingCoordinateY_whenUpdatingItem_thenExceptionIsThrown() {
        Item itemData = givenItemMissingCoordinate_y();

        assertThrows(ValidationException.class, () -> itemService.updateItem(itemData, UUID.randomUUID().toString()));
    }

    @Test
    public void givenItemMissingPhone_whenUpdatingItem_thenExceptionIsThrown() {
        Item itemData = givenItemMissingPhone();

        assertThrows(ValidationException.class, () -> itemService.updateItem(itemData, UUID.randomUUID().toString()));
    }

    @Test
    public void givenItemMissingWebsite_whenUpdatingItem_thenExceptionIsThrown() {
        Item itemData = givenItemMissingWebsite();

        assertThrows(ValidationException.class, () -> itemService.updateItem(itemData, UUID.randomUUID().toString()));
    }

    @Test
    public void givenItemMissingFacebook_whenUpdatingItem_thenExceptionIsThrown() {
        Item itemData = givenItemMissingFacebook();

        assertThrows(ValidationException.class, () -> itemService.updateItem(itemData, UUID.randomUUID().toString()));
    }

    @Test
    public void givenItemMissingInstagram_whenUpdatingItem_thenExceptionIsThrown() {
        Item itemData = givenItemMissingInstagram();

        assertThrows(ValidationException.class, () -> itemService.updateItem(itemData, UUID.randomUUID().toString()));
    }

    @Test
    public void givenNonExistingAuthorization_whenListingItems_thenExceptionIsThrown() {
        Optional<Authentication> authentication = Optional.empty();
        assertThrows(ValidationException.class, () -> itemService.listProducts(authentication));
    }

    @Test
    public void givenUnauthorizedUser_whenListingItems_thenExceptionIsThrown() {
        assertThrows(ValidationException.class, () -> itemService
                .listProducts(givenUnauthorizedUserListingItems()));
    }

    @Test
    public void givenSuperAdmin_whenListingItems_thenSuperAdminDtoIsReturned() throws ValidationException {
        givenItemRepositoryListingItems();

        assertThat(itemService.listProducts(givenSuperAdminListingItems()), is(givenSuperAdminItemDTO()));
        verify(itemRepository, times(1)).findAll();
        verifyNoMoreInteractions(itemRepository);
    }

    @Test
    public void givenCegAdmin_whenListingItems_thenCegAdminDtoIsReturned() throws ValidationException {
        givenItemRepositoryListingItems();

        assertThat(itemService.listProducts(givenCegAdminListingItems()), is(givenCegAdminItemDTO()));
        verify(itemRepository, times(1)).findAll();
        verifyNoMoreInteractions(itemRepository);
    }

    private void givenItemRepositoryListingItems() {
        when(itemRepository.findAll()).thenReturn(List.of(givenItemWithId()));
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

    private List<SuperAdminItemDTO> givenSuperAdminItemDTO() {
        return List.of(new SuperAdminItemDTO(REGISTRATION_ID, ADDRESS, CITY, Category.ATTRACTION, OWNER));
    }

    private List<CegAdminItemDTO> givenCegAdminItemDTO() {
        return List.of(new CegAdminItemDTO(REGISTRATION_ID, ADDRESS, CITY, Category.ATTRACTION));
    }

    private void givenItemRepositorySavingItem() {
        when(itemRepository.save(any(Item.class))).thenAnswer(invocationOnMock -> {
            Item created = invocationOnMock.getArgument(0);
            created.setId(REGISTRATION_ID);
            return created;
        });
    }

    private void givenItemRepositoryWhenCallingDelete() {
        Item itemToBeDeleted = givenItem();
        itemToBeDeleted.setId(REGISTRATION_ID);
        when(itemRepository.findFirstByIdAndDeletedAtNull(anyString())).thenReturn(Optional.of(itemToBeDeleted));
        when(itemRepository.save(any(Item.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
    }

    private Item givenItem() {
        Item item = new Item();
        item.setName(NAME);
        item.setScore(SCORE);
        item.setBio(BIO);
        item.setAddress(ADDRESS);
        item.setCity(CITY);
        item.setCategory(Category.ATTRACTION);
        item.setCoordinateX(COORDINATE_X);
        item.setCoordinateY(COORDINATE_Y);
        item.setPhone(PHONE);
        item.setWebsite(WEBSITE);
        item.setFacebook(FACEBOOK);
        item.setInstagram(INSTAGRAM);

        return item;
    }

    private Item givenItemWithId() {

        Item item = new Item();
        item.setName(NAME);
        item.setId(REGISTRATION_ID);
        item.setScore(SCORE);
        item.setBio(BIO);
        item.setAddress(ADDRESS);
        item.setCity(CITY);
        item.setCategory(Category.ATTRACTION);
        item.setCoordinateX(COORDINATE_X);
        item.setCoordinateY(COORDINATE_Y);
        item.setPhone(PHONE);
        item.setWebsite(WEBSITE);
        item.setFacebook(FACEBOOK);
        item.setInstagram(INSTAGRAM);

        return item;
    }

    private Item givenItemMissingName() {
        Item item = new Item();
        item.setScore(SCORE);
        item.setBio(BIO);
        item.setAddress(ADDRESS);
        item.setCity(CITY);
        item.setCategory(Category.ATTRACTION);
        item.setCoordinateX(COORDINATE_X);
        item.setCoordinateY(COORDINATE_Y);
        item.setPhone(PHONE);
        item.setWebsite(WEBSITE);
        item.setFacebook(FACEBOOK);
        item.setInstagram(INSTAGRAM);
        return item;
    }

    private Item givenItemMissingScore() {
        Item item = new Item();
        item.setName(NAME);
        item.setBio(BIO);
        item.setAddress(ADDRESS);
        item.setCity(CITY);
        item.setCategory(Category.ATTRACTION);
        item.setCoordinateX(COORDINATE_X);
        item.setCoordinateY(COORDINATE_Y);
        item.setPhone(PHONE);
        item.setWebsite(WEBSITE);
        item.setFacebook(FACEBOOK);
        item.setInstagram(INSTAGRAM);
        return item;
    }

    private Item givenItemMissingBio() {
        Item item = new Item();
        item.setName(NAME);
        item.setScore(SCORE);
        item.setAddress(ADDRESS);
        item.setCity(CITY);
        item.setCategory(Category.ATTRACTION);
        item.setCoordinateX(COORDINATE_X);
        item.setCoordinateY(COORDINATE_Y);
        item.setPhone(PHONE);
        item.setWebsite(WEBSITE);
        item.setFacebook(FACEBOOK);
        item.setInstagram(INSTAGRAM);
        return item;
    }

    private Item givenItemMissingAddress() {
        Item item = new Item();
        item.setName(NAME);
        item.setScore(SCORE);
        item.setBio(BIO);
        item.setCity(CITY);
        item.setCategory(Category.ATTRACTION);
        item.setCoordinateX(COORDINATE_X);
        item.setCoordinateY(COORDINATE_Y);
        item.setPhone(PHONE);
        item.setWebsite(WEBSITE);
        item.setFacebook(FACEBOOK);
        item.setInstagram(INSTAGRAM);
        return item;
    }

    private Item givenItemMissingCity() {
        Item item = new Item();
        item.setName(NAME);
        item.setScore(SCORE);
        item.setBio(BIO);
        item.setAddress(ADDRESS);
        item.setCategory(Category.ATTRACTION);
        item.setCoordinateX(COORDINATE_X);
        item.setCoordinateY(COORDINATE_Y);
        item.setPhone(PHONE);
        item.setWebsite(WEBSITE);
        item.setFacebook(FACEBOOK);
        item.setInstagram(INSTAGRAM);
        return item;
    }

    private Item givenItemMissingCategory() {
        Item item = new Item();
        item.setName(NAME);
        item.setScore(SCORE);
        item.setBio(BIO);
        item.setAddress(ADDRESS);
        item.setCity(CITY);
        item.setCoordinateX(COORDINATE_X);
        item.setCoordinateY(COORDINATE_Y);
        item.setPhone(PHONE);
        item.setWebsite(WEBSITE);
        item.setFacebook(FACEBOOK);
        item.setInstagram(INSTAGRAM);
        return item;
    }

    private Item givenItemMissingCoordinate_x() {
        Item item = new Item();
        item.setName(NAME);
        item.setScore(SCORE);
        item.setBio(BIO);
        item.setAddress(ADDRESS);
        item.setCity(CITY);
        item.setCategory(Category.ATTRACTION);
        item.setCoordinateY(COORDINATE_Y);
        item.setPhone(PHONE);
        item.setWebsite(WEBSITE);
        item.setFacebook(FACEBOOK);
        item.setInstagram(INSTAGRAM);
        return item;
    }

    private Item givenItemMissingCoordinate_y() {
        Item item = new Item();
        item.setName(NAME);
        item.setScore(SCORE);
        item.setBio(BIO);
        item.setAddress(ADDRESS);
        item.setCity(CITY);
        item.setCategory(Category.ATTRACTION);
        item.setCoordinateX(COORDINATE_X);
        item.setPhone(PHONE);
        item.setWebsite(WEBSITE);
        item.setFacebook(FACEBOOK);
        item.setInstagram(INSTAGRAM);
        return item;
    }

    private Item givenItemMissingPhone() {
        Item item = new Item();
        item.setName(NAME);
        item.setScore(SCORE);
        item.setBio(BIO);
        item.setAddress(ADDRESS);
        item.setCity(CITY);
        item.setCategory(Category.ATTRACTION);
        item.setCoordinateX(COORDINATE_X);
        item.setCoordinateY(COORDINATE_Y);
        item.setWebsite(WEBSITE);
        item.setFacebook(FACEBOOK);
        item.setInstagram(INSTAGRAM);
        return item;
    }

    private Item givenItemMissingWebsite() {
        Item item = new Item();
        item.setName(NAME);
        item.setScore(SCORE);
        item.setBio(BIO);
        item.setAddress(ADDRESS);
        item.setCity(CITY);
        item.setCategory(Category.ATTRACTION);
        item.setCoordinateX(COORDINATE_X);
        item.setCoordinateY(COORDINATE_Y);
        item.setPhone(PHONE);
        item.setFacebook(FACEBOOK);
        item.setInstagram(INSTAGRAM);
        return item;
    }

    private Item givenItemMissingFacebook() {
        Item item = new Item();
        item.setName(NAME);
        item.setScore(SCORE);
        item.setBio(BIO);
        item.setAddress(ADDRESS);
        item.setCity(CITY);
        item.setCategory(Category.ATTRACTION);
        item.setCoordinateX(COORDINATE_X);
        item.setCoordinateY(COORDINATE_Y);
        item.setPhone(PHONE);
        item.setWebsite(WEBSITE);
        item.setInstagram(INSTAGRAM);
        return item;
    }

    private Item givenItemMissingInstagram() {
        Item item = new Item();
        item.setName(NAME);
        item.setScore(SCORE);
        item.setBio(BIO);
        item.setAddress(ADDRESS);
        item.setCity(CITY);
        item.setCategory(Category.ATTRACTION);
        item.setCoordinateX(COORDINATE_X);
        item.setCoordinateY(COORDINATE_Y);
        item.setPhone(PHONE);
        item.setWebsite(WEBSITE);
        item.setFacebook(FACEBOOK);
        return item;
    }

    private void givenExistingItemWhenUpdate() {
        Item item = givenItem();
        item.setId(REGISTRATION_ID);
        when(itemRepository.findById(REGISTRATION_ID)).thenReturn(Optional.of(item));
        when(itemRepository.save(any(Item.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
    }

}