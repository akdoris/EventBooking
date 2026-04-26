package com.auca.events.controller;

import com.auca.events.model.Event;
import com.auca.events.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CONTROLLER - Handles HTTP requests for Events
 * 
 * Endpoints (as per exam):
 *   GET  /API/Event        -> get all events
 *   POST /API/Event        -> create new event
 */
@RestController
@RequestMapping("/API/Event")
@CrossOrigin(origins = "http://localhost:5173")  // Allow Vue frontend
@Tag(name = "Event", description = "Manage campus events")
public class EventController {

    @Autowired
    private EventService eventService;

    /**
     * GET /API/Event
     * Returns all events
     */
    @GetMapping
    @Operation(summary = "Get all events", description = "Returns a list of all campus events")
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    /**
     * GET /API/Event/{id}
     * Returns one event by ID
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get event by ID")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        return eventService.getEventById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * POST /API/Event
     * Creates a new event
     * Body: { "title": "Tech Talk", "date": "2025-05-10", "availableSeats": 50 }
     */
    @PostMapping
    @Operation(summary = "Create a new event")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event saved = eventService.createEvent(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    /**
     * DELETE /API/Event/{id}
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an event")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
