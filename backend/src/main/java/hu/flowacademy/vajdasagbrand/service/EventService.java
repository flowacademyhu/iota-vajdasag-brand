package hu.flowacademy.vajdasagbrand.service;

import hu.flowacademy.vajdasagbrand.dto.EventDTO;
import hu.flowacademy.vajdasagbrand.exception.ValidationException;
import hu.flowacademy.vajdasagbrand.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Transactional
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public EventDTO createEvent(EventDTO event) throws ValidationException {
        validateEventData(event);
        return eventRepository.save(event);
    }

    private void validateEventData(EventDTO event) throws ValidationException {
        if (!StringUtils.hasText(event.getName())) {
            throw new ValidationException("Didn't get name");
        }
        if (!StringUtils.hasText(event.getBio())) {
            throw new ValidationException("Didn't get bio");
        }
        if (!StringUtils.hasText(event.getPlace())) {
            throw new ValidationException("Didn't get place");
        }
        if (event.getItemId() == null) {
            throw new ValidationException("Didn't get itemId");
        }
        if (event.getEventstart() == null) {
            throw new ValidationException("Didn't get time for event start");
        }
        if (event.getEventend() == null) {
            throw new ValidationException("Didn't get time for event end");
        }
        if (event.getEventstart().isAfter(event.getEventend())) {
            throw new ValidationException("Program start is after the program end time");
        }
        if (event.getEventstart().equals(event.getEventend())) {
            throw new ValidationException("The event start is the same time with event end");
        }
    }
}
