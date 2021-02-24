package hu.flowacademy.vajdasagbrand.service;

import hu.flowacademy.vajdasagbrand.repository.EventRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

    private static final String NAME = "New Event";
    private static final String BIO = "Information from the event";
    private static final String PLACE = "Szeged Partfürdő";
    private static final LocalDateTime EVENTSTART = LocalDateTime.parse("2020.03.11 15:00:15");

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService eventService;


}
