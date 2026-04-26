package com.auca.events.service;

import com.auca.events.model.Event;
import com.auca.events.model.Student;
import com.auca.events.repository.EventRepository;
import com.auca.events.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SERVICE LAYER - Business logic for Registration (Many-to-Many)
 */
@Service
public class RegistrationService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EventRepository eventRepository;

    // GET all students (registrations)
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // POST: Register a student for an event
    public Student registerStudentForEvent(Long studentId, Long eventId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + eventId));

        if (event.getAvailableSeats() <= 0) {
            throw new RuntimeException("No available seats for this event!");
        }

        // Add relationship (many-to-many)
        student.getRegisteredEvents().add(event);
        event.setAvailableSeats(event.getAvailableSeats() - 1);

        eventRepository.save(event);
        return studentRepository.save(student);
    }

    // POST: Create a new student
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }
}
