package hu.flowacademy.vajdasagbrand.service;

import hu.flowacademy.vajdasagbrand.dto.EventDTO;
import hu.flowacademy.vajdasagbrand.dto.ItemDTO;
import hu.flowacademy.vajdasagbrand.exception.ValidationException;
import hu.flowacademy.vajdasagbrand.persistence.entity.Category;
import hu.flowacademy.vajdasagbrand.persistence.entity.Item;
import hu.flowacademy.vajdasagbrand.persistence.entity.Subcategory;
import hu.flowacademy.vajdasagbrand.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

    private static final String REGISTRATION_ID = "1234L";
    private static final String NAME = "New Event";
    private static final String BIO = "Information from the event";
    private static final String PLACE = "Szeged Partfürdő";
    private static final String ITEMID = "16548651L";
    private static final LocalDateTime EVENTSTART = LocalDateTime.of(2020,3,11,18,0,0);
    private static final LocalDateTime EVENTENDSAMETIME = LocalDateTime.of(2020,3,11,18,0,0);
    private static final LocalDateTime EVENTEND = LocalDateTime.of(2020, 3,11,20,0,0);
    private static final LocalDateTime EVENTENDUP = LocalDateTime.of(2020, 3,11,15,0,0);

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService eventService;

    @Test
    public void givenEvent_whenCreatingEvent_thenCreatedSuccessfully() throws ValidationException {
        givenEventRepositorySavingEvent();
        EventDTO eventData = givenEvent();
        eventService.createEvent(eventData);
        verify(eventRepository, times(1)).save(eventData);
        verifyNoMoreInteractions(eventRepository);
    }

    @Test
    public void givenExistingEvent_whenCallingUpdate_thenEventIsUpdated() throws ValidationException {
        givenExistingEventWhenUpdate();
        EventDTO event = givenEventWithId();
        EventDTO updatedEvent = eventService.updateEvent(event, REGISTRATION_ID);
        verify(eventRepository, times(1)).save(updatedEvent);
        assertThat(updatedEvent, notNullValue());
        assertThat(updatedEvent.getId(), is(event.getId()));
        assertThat(updatedEvent.getName(), is(event.getName()));
        assertThat(updatedEvent.getBio(), is(event.getBio()));
        assertThat(updatedEvent.getPlace(), is(event.getPlace()));
        assertThat(updatedEvent.getEventstart(), is(event.getEventstart()));
        assertThat(updatedEvent.getEventend(), is(event.getEventend()));
        assertThat(updatedEvent.getItemId(), is(event.getItemId()));
        verifyNoMoreInteractions(eventRepository);
    }

    @Test
    public void givenEventMissingName_whenCreatingEvent_thenExceptionIsThrown() {
        EventDTO eventData = givenEventMissingName();

        assertThrows(ValidationException.class, () -> eventService.createEvent(eventData));
    }

    @Test
    public void givenEventMissingBio_whenCreatingEvent_thenExceptionIsThrown() {
        EventDTO eventData = givenEventMissingBio();

        assertThrows(ValidationException.class, () -> eventService.createEvent(eventData));
    }

    @Test
    public void givenEventMissingPlace_whenCreatingEvent_thenExceptionIsThrown() {
        EventDTO eventData = givenEventMissingPlace();

        assertThrows(ValidationException.class, () -> eventService.createEvent(eventData));
    }

    @Test
    public void givenEventMissingEventStart_whenCreatingEvent_thenExceptionIsThrown() {
        EventDTO eventData = givenEventMissingEventStart();

        assertThrows(ValidationException.class, () -> eventService.createEvent(eventData));
    }

    @Test
    public void givenEventMissingEventEnd_whenCreatingEvent_thenExceptionIsThrown() {
        EventDTO eventData = givenEventMissingEventEnd();

        assertThrows(ValidationException.class, () -> eventService.createEvent(eventData));
    }

    @Test
    public void givenEventStartAferEndTime_whenCreatingEvent_thenExceptionIsThrown() {
        EventDTO eventData = givenEventStartAfterEndTime();

        assertThrows(ValidationException.class, () -> eventService.createEvent(eventData));
    }

    @Test
    public void givenEventStartWhichIsEqualsEndTime_whenCreatingEvent_thenExceptionIsThrown() {
        EventDTO eventData = givenEventStartEqualsEndTime();

        assertThrows(ValidationException.class, () -> eventService.createEvent(eventData));
    }

    @Test
    public void givenEventMissingItem_whenCreatingEvent_thenExceptionIsThrown() {
        EventDTO eventData = givenEventMissingItemId();

        assertThrows(ValidationException.class, () -> eventService.createEvent(eventData));
    }

    private void givenEventRepositorySavingEvent() {
        when(eventRepository.save(any(EventDTO.class))).thenAnswer(invocationOnMock -> {
            EventDTO created = invocationOnMock.getArgument(0);
            created.setId(REGISTRATION_ID);
            return created;
        });
    }

    private EventDTO givenEvent(){

        EventDTO event = new EventDTO();
        event.setName(NAME);
        event.setBio(BIO);
        event.setPlace(PLACE);
        event.setEventstart(EVENTSTART);
        event.setEventend(EVENTEND);
        event.setItemId(ITEMID);

        return event;
    }

    private EventDTO givenEventWithId() {

        EventDTO event = new EventDTO();
        event.setName(NAME);
        event.setId(REGISTRATION_ID);
        event.setBio(BIO);
        event.setPlace(PLACE);
        event.setEventstart(EVENTSTART);
        event.setEventend(EVENTEND);
        event.setItemId(ITEMID);

        return event;
    }

    private EventDTO givenEventMissingName() {

        EventDTO event = new EventDTO();
        event.setBio(BIO);
        event.setPlace(PLACE);
        event.setEventstart(EVENTSTART);
        event.setEventend(EVENTEND);
        event.setItemId(ITEMID);

        return event;
    }

    private EventDTO givenEventMissingBio() {

        EventDTO event = new EventDTO();
        event.setName(NAME);
        event.setPlace(PLACE);
        event.setEventstart(EVENTSTART);
        event.setEventend(EVENTEND);
        event.setItemId(ITEMID);

        return event;
    }

    private EventDTO givenEventMissingPlace() {

        EventDTO event = new EventDTO();
        event.setName(NAME);
        event.setBio(BIO);
        event.setEventstart(EVENTSTART);
        event.setEventend(EVENTEND);
        event.setItemId(ITEMID);

        return event;
    }

    private EventDTO givenEventMissingItemId() {

        EventDTO event = new EventDTO();
        event.setName(NAME);
        event.setBio(BIO);
        event.setPlace(PLACE);
        event.setEventstart(EVENTSTART);
        event.setEventend(EVENTEND);

        return event;
    }

    private EventDTO givenEventMissingEventStart() {

        EventDTO event = new EventDTO();
        event.setName(NAME);
        event.setBio(BIO);
        event.setPlace(PLACE);
        event.setEventend(EVENTEND);
        event.setItemId(ITEMID);

        return event;
    }

    private EventDTO givenEventMissingEventEnd() {

        EventDTO event = new EventDTO();
        event.setName(NAME);
        event.setBio(BIO);
        event.setPlace(PLACE);
        event.setEventstart(EVENTSTART);
        event.setItemId(ITEMID);

        return event;
    }

    private EventDTO givenEventStartAfterEndTime() {

        EventDTO event = new EventDTO();
        event.setName(NAME);
        event.setBio(BIO);
        event.setPlace(PLACE);
        event.setEventstart(EVENTSTART);
        event.setEventend(EVENTENDUP);
        event.setItemId(ITEMID);

        return event;
    }

    private EventDTO givenEventStartEqualsEndTime() {

        EventDTO event = new EventDTO();
        event.setName(NAME);
        event.setBio(BIO);
        event.setPlace(PLACE);
        event.setEventstart(EVENTSTART);
        event.setEventend(EVENTENDSAMETIME);
        event.setItemId(ITEMID);
        return event;
    }

    private void givenExistingEventWhenUpdate() {
        EventDTO event = givenEvent();
        event.setId(REGISTRATION_ID);
        when(eventRepository.findById(REGISTRATION_ID)).thenReturn(Optional.of(event));
        when(eventRepository.save(any(EventDTO.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
    }
}
