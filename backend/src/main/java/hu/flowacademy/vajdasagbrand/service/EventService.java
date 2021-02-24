package hu.flowacademy.vajdasagbrand.service;

import hu.flowacademy.vajdasagbrand.entity.Event;
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

    public Event createEvent(Event event) throws ValidationException {
        validateEventData(event);

        return eventRepository.save(event);
    }

    private void validateEventData(Event event) throws ValidationException{
        if(!StringUtils.hasText(event.getName())) {
            throw new ValidationException("Didn't get name");
        }
        if(!StringUtils.hasText(event.getBio())) {
            throw new ValidationException("Didn't get bio");
        }
        if(!StringUtils.hasText(event.getPlace())) {
            throw new ValidationException("Didn't get place");
        }
        if(event.getProgramstart() == null) {
            throw new ValidationException("Didn't get time for program start");
        }
        if(event.getProgramend() == null) {
            throw new ValidationException("Didn't get time for program end");
        }
        if(!(event.getProgramstart().isAfter(event.getProgramend()))) {
            throw new ValidationException("Program start is after the program end time");
        }
    }
}
