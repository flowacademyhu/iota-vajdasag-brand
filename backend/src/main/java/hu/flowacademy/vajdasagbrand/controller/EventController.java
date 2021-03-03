package hu.flowacademy.vajdasagbrand.controller;

import hu.flowacademy.vajdasagbrand.dto.EventDTO;

import hu.flowacademy.vajdasagbrand.dto.ItemDTO;
import hu.flowacademy.vajdasagbrand.exception.ValidationException;
import hu.flowacademy.vajdasagbrand.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class EventController {

    private final EventService eventService;

    @RolesAllowed({"SuperAdmin", "CegAdmin"})
    @PostMapping("/events")
    @ResponseStatus(HttpStatus.CREATED)
    public void createEvent(@RequestBody EventDTO eventDTO) throws ValidationException {

        eventService.createEvent(eventDTO);
    }

    @RolesAllowed({"SuperAdmin", "CegAdmin"})
    @PutMapping("/events/{id}")
    public EventDTO updateEvent(@PathVariable("id") String id,
                                @RequestBody EventDTO event) throws ValidationException {
        return eventService.updateEvent(event, id);
    }
}
