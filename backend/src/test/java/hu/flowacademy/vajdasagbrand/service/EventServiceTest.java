package hu.flowacademy.vajdasagbrand.service;

import hu.flowacademy.vajdasagbrand.entity.Event;
import hu.flowacademy.vajdasagbrand.entity.Item;
import hu.flowacademy.vajdasagbrand.exception.ValidationException;
import hu.flowacademy.vajdasagbrand.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

    private static final String REGISTRATION_ID = "1234L";
    private static final String NAME = "New Event";
    private static final String BIO = "Information from the event";
    private static final String PLACE = "Szeged Partfürdő";
    private static final Item ITEM = new Item();
    private static final LocalDateTime EVENTSTART = LocalDateTime.of(2020,3,11,18,0,0);
    private static final LocalDateTime EVENTENDSAMETIME = LocalDateTime.of(2020,3,11,18,0,0);
    private static final LocalDateTime EVENTEND = LocalDateTime.of(2020, 3,11,20,0,0);
    private static final LocalDateTime EVENTENDUP = LocalDateTime.of(2020, 3,11,15,0,0);

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService eventService;

    @Test
    public void givenItem_whenCreatingItem_thenCreatedSuccessfully() throws ValidationException {
        givenEventRepositorySavingEvent();
        Event eventData = givenEvent();
        eventService.createEvent(eventData);
        verify(eventRepository, times(1)).save(eventData);
        verifyNoMoreInteractions(eventRepository);
    }

    @Test
    public void givenEventMissingName_whenCreatingEvent_thenExceptionIsThrown() {
        Event eventData = givenEventMissingName();

        assertThrows(ValidationException.class, () -> eventService.createEvent(eventData));
    }

    @Test
    public void givenEventMissingBio_whenCreatingEvent_thenExceptionIsThrown() {
        Event eventData = givenEventMissingBio();

        assertThrows(ValidationException.class, () -> eventService.createEvent(eventData));
    }

    @Test
    public void givenEventMissingPlace_whenCreatingEvent_thenExceptionIsThrown() {
        Event eventData = givenEventMissingPlace();

        assertThrows(ValidationException.class, () -> eventService.createEvent(eventData));
    }

    @Test
    public void givenEventMissingEventStart_whenCreatingEvent_thenExceptionIsThrown() {
        Event eventData = givenEventMissingEventStart();

        assertThrows(ValidationException.class, () -> eventService.createEvent(eventData));
    }

    @Test
    public void givenEventMissingEventEnd_whenCreatingEvent_thenExceptionIsThrown() {
        Event eventData = givenEventMissingEventEnd();

        assertThrows(ValidationException.class, () -> eventService.createEvent(eventData));
    }

    @Test
    public void givenEventStartAferEndTime_whenCreatingEvent_thenExceptionIsThrown() {
        Event eventData = givenEventStartAfterEndTime();

        assertThrows(ValidationException.class, () -> eventService.createEvent(eventData));
    }

    @Test
    public void givenEventStartWhichIsEqualsEndTime_whenCreatingEvent_thenExceptionIsThrown() {
        Event eventData = givenEventStartEqualsEndTime();

        assertThrows(ValidationException.class, () -> eventService.createEvent(eventData));
    }

    @Test
    public void givenEventMissingItem_whenCreatingEvent_thenExceptionIsThrown() {
        Event eventData = givenEventMissingItem();

        assertThrows(ValidationException.class, () -> eventService.createEvent(eventData));
    }

    private void givenEventRepositorySavingEvent() {
        when(eventRepository.save(any(Event.class))).thenAnswer(invocationOnMock -> {
            Event created = invocationOnMock.getArgument(0);
            created.setId(REGISTRATION_ID);
            return created;
        });
    }

    private Event givenEvent(){

        Event event = new Event();
        event.setName(NAME);
        event.setBio(BIO);
        event.setPlace(PLACE);
        event.setEventstart(EVENTSTART);
        event.setEventend(EVENTEND);
        event.setItem(ITEM);

        return event;
    }

    private Event givenEventMissingName() {

        Event event = new Event();
        event.setBio(BIO);
        event.setPlace(PLACE);
        event.setEventstart(EVENTSTART);
        event.setEventend(EVENTEND);
        event.setItem(ITEM);

        return event;
    }

    private Event givenEventMissingBio() {

        Event event = new Event();
        event.setName(NAME);
        event.setPlace(PLACE);
        event.setEventstart(EVENTSTART);
        event.setEventend(EVENTEND);
        event.setItem(ITEM);

        return event;
    }

    private Event givenEventMissingPlace() {

        Event event = new Event();
        event.setName(NAME);
        event.setBio(BIO);
        event.setEventstart(EVENTSTART);
        event.setEventend(EVENTEND);
        event.setItem(ITEM);

        return event;
    }

    private Event givenEventMissingItem() {

        Event event = new Event();
        event.setName(NAME);
        event.setBio(BIO);
        event.setPlace(PLACE);
        event.setEventstart(EVENTSTART);
        event.setEventend(EVENTEND);

        return event;
    }

    private Event givenEventMissingEventStart() {

        Event event = new Event();
        event.setName(NAME);
        event.setBio(BIO);
        event.setPlace(PLACE);
        event.setEventend(EVENTEND);
        event.setItem(ITEM);

        return event;
    }

    private Event givenEventMissingEventEnd() {

        Event event = new Event();
        event.setName(NAME);
        event.setBio(BIO);
        event.setPlace(PLACE);
        event.setEventstart(EVENTSTART);
        event.setItem(ITEM);

        return event;
    }

    private Event givenEventStartAfterEndTime() {

        Event event = new Event();
        event.setName(NAME);
        event.setBio(BIO);
        event.setPlace(PLACE);
        event.setEventstart(EVENTSTART);
        event.setEventend(EVENTENDUP);
        event.setItem(ITEM);

        return event;
    }

    private Event givenEventStartEqualsEndTime() {

        Event event = new Event();
        event.setName(NAME);
        event.setBio(BIO);
        event.setPlace(PLACE);
        event.setEventstart(EVENTSTART);
        event.setEventend(EVENTENDSAMETIME);
        event.setItem(ITEM);
        return event;
    }
}
