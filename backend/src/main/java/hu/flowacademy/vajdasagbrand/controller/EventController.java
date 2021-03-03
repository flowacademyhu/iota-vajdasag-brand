package hu.flowacademy.vajdasagbrand.controller;

import hu.flowacademy.vajdasagbrand.dto.EventDTO;
import hu.flowacademy.vajdasagbrand.exception.ValidationException;
import hu.flowacademy.vajdasagbrand.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class EventController {

    private final EventService eventService;

    @RolesAllowed({"SuperAdmin", "CegAdmin"})
    @PostMapping("/events")
    @ResponseStatus(HttpStatus.CREATED)
    public EventDTO createEvent(@RequestBody EventDTO eventDTO) throws ValidationException {
        return eventService.createEvent(eventDTO);
    }

    @RolesAllowed({"SuperAdmin", "CegAdmin"})
    @GetMapping("/events")
    public Page<EventDTO> getEvents(@RequestParam(value = "order_by", required = false) Optional<String> orderBy,
                                    @RequestParam(value = "page", required = false) Optional<Integer> pageNum,
                                    @RequestParam(value = "limit", required = false) Optional<Integer> limit) {
        return eventService.listEvents(orderBy, pageNum, limit);
    }
}
