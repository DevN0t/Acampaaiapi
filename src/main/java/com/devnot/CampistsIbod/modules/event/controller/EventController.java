package com.devnot.CampistsIbod.modules.event.controller;

import com.devnot.CampistsIbod.modules.event.dto.EventCreateDTO;
import com.devnot.CampistsIbod.modules.event.infra.model.Event;
import com.devnot.CampistsIbod.modules.event.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/events/")
    public ResponseEntity<List<Event>> getEvents(
            @RequestHeader("Authorization") String jwt
    ) {
        return eventService.findAllByBranch(jwt);
    }

    @GetMapping("/event/")
    public ResponseEntity<Event> getEvent(
            @RequestHeader("Authorization") String jwt,
            @RequestParam Integer id
    ) {
        return eventService.findById(jwt,id);
    }

    @PutMapping("/event/")
    public ResponseEntity<String> updateEvent(
            @RequestHeader("Authorization") String jwt,
            @RequestParam Integer id,
            @RequestBody EventCreateDTO event
    ) {
        return eventService.update(event, jwt,id);
    }

    @PostMapping("/event/")
    public ResponseEntity<String> createEvent(
            @RequestHeader("Authorization") String jwt,
            @RequestBody EventCreateDTO event
    ) {
        return eventService.create(event, jwt);
    }
}
