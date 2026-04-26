package com.doris.eventbooking.exception;

public class EventNotFoundException extends RuntimeException {

    public EventNotFoundException(String id) {
        super("Event not found with id: " + id);
    }
}
