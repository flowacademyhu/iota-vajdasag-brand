package hu.flowacademy.vajdasagbrand.service;

import hu.flowacademy.vajdasagbrand.dto.EventDTO;
import hu.flowacademy.vajdasagbrand.exception.ValidationException;
import hu.flowacademy.vajdasagbrand.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

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
            throw new ValidationException("No name was provided.");
        }
        if (!StringUtils.hasText(event.getBio())) {
            throw new ValidationException("No bio was provided.");
        }
        if (!StringUtils.hasText(event.getPlace())) {
            throw new ValidationException("No place was provided.");
        }
        if (event.getItemId() == null) {
            throw new ValidationException("No itemId was provided.");
        }
        if (event.getEventstart() == null) {
            throw new ValidationException("No time was provided for the start of the event.");
        }
        if (event.getEventend() == null) {
            throw new ValidationException("No time was provided for the end of the event.");
        }
        if (event.getEventstart().isAfter(event.getEventend())) {
            throw new ValidationException("Program start is after the program end time.");
        }
        if (event.getEventstart().equals(event.getEventend())) {
            throw new ValidationException("The event starting time is at the same time with the end time.");
        }
    }

    public Page<EventDTO> listEvents (String orderBy, int pageNum, int limit) {
        return eventRepository.findAllEvents(PageRequest.of(pageNum, limit, Sort.by(Sort.Direction.DESC, orderBy)));
    }
}