package hu.flowacademy.vajdasagbrand.service;

import hu.flowacademy.vajdasagbrand.dto.EventDTO;
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
    private static final String ITEMID = "16548651L";
    private static final LocalDateTime EVENT_START = LocalDateTime.of(2020,3,11,18,0,0);
    private static final LocalDateTime EVENT_END_IN_SAME_TIME = LocalDateTime.of(2020,3,11,18,0,0);
    private static final LocalDateTime EVENT_END = LocalDateTime.of(2020, 3,11,20,0,0);
    private static final LocalDateTime EVENT_END_UP = LocalDateTime.of(2020, 3,11,15,0,0);

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
        event.setEventstart(EVENT_START);
        event.setEventend(EVENT_END);
        event.setItemId(ITEMID);

        return event;
    }

    private EventDTO givenEventMissingName() {

        EventDTO event = new EventDTO();
        event.setBio(BIO);
        event.setPlace(PLACE);
        event.setEventstart(EVENT_START);
        event.setEventend(EVENT_END);
        event.setItemId(ITEMID);

        return event;
    }

    private EventDTO givenEventMissingBio() {

        EventDTO event = new EventDTO();
        event.setName(NAME);
        event.setPlace(PLACE);
        event.setEventstart(EVENT_START);
        event.setEventend(EVENT_END);
        event.setItemId(ITEMID);

        return event;
    }

    private EventDTO givenEventMissingPlace() {

        EventDTO event = new EventDTO();
        event.setName(NAME);
        event.setBio(BIO);
        event.setEventstart(EVENT_START);
        event.setEventend(EVENT_END);
        event.setItemId(ITEMID);

        return event;
    }

    private EventDTO givenEventMissingItemId() {

        EventDTO event = new EventDTO();
        event.setName(NAME);
        event.setBio(BIO);
        event.setPlace(PLACE);
        event.setEventstart(EVENT_START);
        event.setEventend(EVENT_END);

        return event;
    }

    private EventDTO givenEventMissingEventStart() {

        EventDTO event = new EventDTO();
        event.setName(NAME);
        event.setBio(BIO);
        event.setPlace(PLACE);
        event.setEventend(EVENT_END);
        event.setItemId(ITEMID);

        return event;
    }

    private EventDTO givenEventMissingEventEnd() {

        EventDTO event = new EventDTO();
        event.setName(NAME);
        event.setBio(BIO);
        event.setPlace(PLACE);
        event.setEventstart(EVENT_START);
        event.setItemId(ITEMID);

        return event;
    }

    private EventDTO givenEventStartAfterEndTime() {

        EventDTO event = new EventDTO();
        event.setName(NAME);
        event.setBio(BIO);
        event.setPlace(PLACE);
        event.setEventstart(EVENT_START);
        event.setEventend(EVENT_END_UP);
        event.setItemId(ITEMID);

        return event;
    }

    private EventDTO givenEventStartEqualsEndTime() {

        EventDTO event = new EventDTO();
        event.setName(NAME);
        event.setBio(BIO);
        event.setPlace(PLACE);
        event.setEventstart(EVENT_START);
        event.setEventend(EVENT_END_IN_SAME_TIME);
        event.setItemId(ITEMID);
        return event;
    }
}
