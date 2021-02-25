package hu.flowacademy.vajdasagbrand.controller;

import hu.flowacademy.vajdasagbrand.dto.EventDTO;
import hu.flowacademy.vajdasagbrand.entity.Event;
import hu.flowacademy.vajdasagbrand.entity.Item;
import hu.flowacademy.vajdasagbrand.exception.ValidationException;
import hu.flowacademy.vajdasagbrand.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
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
    public Event createEvent(@RequestBody EventDTO eventDTO) throws ValidationException {
        Event event = new Event();
        BeanUtils.copyProperties(eventDTO, event);
        return eventService.createEvent(event.toBuilder().item(Item.builder().id(eventDTO.getItemId()).build()).build());
    }
}
