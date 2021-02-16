package hu.flowacademy.vajdasagbrand.service;

import hu.flowacademy.vajdasagbrand.entity.Category;
import hu.flowacademy.vajdasagbrand.entity.Item;
import hu.flowacademy.vajdasagbrand.exception.ValidationException;
import hu.flowacademy.vajdasagbrand.repository.ItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    private static final String REGISTRATION_ID = "1234L";
    private static final String NAME = "Something";
    private static final String BIO = "Something useful thing";
    private static final int SCORE = 50;
    private static final String ADDRESS = "6771 Szeged, Makai út 5.";
    private static final String CITY = "Szeged";
    private static final String COORDINATE_X = "21353.35146";
    private static final String COORDINATE_Y = "12154.6485";
    private static final String PHONE = "30/5475520";
    private static final String WEBSITE = "www.hotelglass.com";
    private static final String FACEBOOK = "www.facebook.com/hotelglass";
    private static final String INSTAGRAM = "www.instagram.com/hotelglass";

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService itemService;

    @Test
    public void givenItem_whenCreatingItem_thenCreatedSuccessfully() throws ValidationException {
        givenItemRepositorySavingItem();
        Item itemData = givenItem();
        Item itemResult = itemService.itemRegistrationData(itemData);
        verify(itemRepository, times(1)).save(itemData);
        verifyNoMoreInteractions(itemRepository);
    }

    @Test
    public void givenItemMissingName_whenCreatingItem_thenExceptionIsThrown() {
        Item itemData = givenItemMissingName();

        assertThrows(ValidationException.class, () -> itemService.itemRegistrationData(itemData));
    }

    @Test
    public void givenItemMissingScore_whenCreatingItem_thenExceptionIsThrown() {
        Item itemData = givenItemMissingScore();

        assertThrows(ValidationException.class, () -> itemService.itemRegistrationData(itemData));
    }

    @Test
    public void givenItemMissingBio_whenCreatingItem_thenExceptionIsThrown() {
        Item itemData = givenItemMissingBio();

        assertThrows(ValidationException.class, () -> itemService.itemRegistrationData(itemData));
    }

    @Test
    public void givenItemMissingAddress_whenCreatingItem_thenExceptionIsThrown() {
        Item itemData = givenItemMissingAddress();

        assertThrows(ValidationException.class, () -> itemService.itemRegistrationData(itemData));
    }

    @Test
    public void givenItemMissingCity_whenCreatingItem_thenExceptionIsThrown() {
        Item itemData = givenItemMissingCity();

        assertThrows(ValidationException.class, () -> itemService.itemRegistrationData(itemData));
    }

    @Test
    public void givenItemMissingCategory_whenCreatingItem_thenExceptionIsThrown() {
        Item itemData = givenItemMissingCategory();

        assertThrows(ValidationException.class, () -> itemService.itemRegistrationData(itemData));
    }

    @Test
    public void givenItemMissingCoordinationX_whenCreatingItem_thenExceptionIsThrown() {
        Item itemData = givenItemMissingCoordinate_x();

        assertThrows(ValidationException.class, () -> itemService.itemRegistrationData(itemData));
    }

    @Test
    public void givenItemMissingCoordinationY_whenCreatingItem_thenExceptionIsThrown() {
        Item itemData = givenItemMissingCoordinate_y();

        assertThrows(ValidationException.class, () -> itemService.itemRegistrationData(itemData));
    }

    @Test
    public void givenItemMissingPhone_whenCreatingItem_thenExceptionIsThrown() {
        Item itemData = givenItemMissingPhone();

        assertThrows(ValidationException.class, () -> itemService.itemRegistrationData(itemData));
    }

    @Test
    public void givenItemMissingWebsite_whenCreatingItem_thenExceptionIsThrown() throws ValidationException {
        Item itemData = givenItemMissingWebsite();

        assertThrows(ValidationException.class, () -> itemService.itemRegistrationData(itemData));
    }

    @Test
    public void givenItemMissingFacebook_whenCreatingItem_thenExceptionIsThrown() {
        Item itemData = givenItemMissingFacebook();

        assertThrows(ValidationException.class, () -> itemService.itemRegistrationData(itemData));
    }

    @Test
    public void givenItemMissingInstagram_whenCreatingItem_thenExceptionIsThrown() {
        Item itemData = givenItemMissingInstagram();

        assertThrows(ValidationException.class, () -> itemService.itemRegistrationData(itemData));
    }

    private void givenItemRepositorySavingItem() {
        when(itemRepository.save(any(Item.class))).thenAnswer(invocationOnMock -> {
            Item created = invocationOnMock.getArgument(0);
            created.setId(REGISTRATION_ID);
            return created;
        });
    }

    private Item givenItem(){

        Item item = new Item();
        item.setName(NAME);
        item.setScore(SCORE);
        item.setBio(BIO);
        item.setAddress(ADDRESS);
        item.setCity(CITY);
        item.setCategory(Category.ATTRACTION);
        item.setCoordinate_x(COORDINATE_X);
        item.setCoordinate_y(COORDINATE_Y);
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
        item.setCoordinate_x(COORDINATE_X);
        item.setCoordinate_y(COORDINATE_Y);
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
        item.setCoordinate_x(COORDINATE_X);
        item.setCoordinate_y(COORDINATE_Y);
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
        item.setCoordinate_x(COORDINATE_X);
        item.setCoordinate_y(COORDINATE_Y);
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
        item.setCoordinate_x(COORDINATE_X);
        item.setCoordinate_y(COORDINATE_Y);
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
        item.setCoordinate_x(COORDINATE_X);
        item.setCoordinate_y(COORDINATE_Y);
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
        item.setCoordinate_x(COORDINATE_X);
        item.setCoordinate_y(COORDINATE_Y);
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
        item.setCoordinate_y(COORDINATE_Y);
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
        item.setCoordinate_x(COORDINATE_X);
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
        item.setCoordinate_x(COORDINATE_X);
        item.setCoordinate_y(COORDINATE_Y);
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
        item.setCoordinate_x(COORDINATE_X);
        item.setCoordinate_y(COORDINATE_Y);
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
        item.setCoordinate_x(COORDINATE_X);
        item.setCoordinate_y(COORDINATE_Y);
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
        item.setCoordinate_x(COORDINATE_X);
        item.setCoordinate_y(COORDINATE_Y);
        item.setPhone(PHONE);
        item.setWebsite(WEBSITE);
        item.setFacebook(FACEBOOK);
        return item;
    }

}