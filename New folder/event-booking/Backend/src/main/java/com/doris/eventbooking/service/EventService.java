package com.auca.events.service;

import com.auca.events.model.Event;
import com.auca.events.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * SERVICE LAYER - Business logic for Events
 * Part of MVC: Model (Entity) -> Service -> Controller -> View
 */
@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    // GET all events
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // GET single event by ID
    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    // POST create new event
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    // DELETE event
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}
