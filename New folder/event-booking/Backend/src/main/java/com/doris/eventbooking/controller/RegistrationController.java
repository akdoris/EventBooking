package com.auca.events.controller;

import com.auca.events.model.Student;
import com.auca.events.service.RegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CONTROLLER - Handles HTTP requests for Registrations
 *
 * Endpoints (as per exam):
 *   GET  /API/Registration          -> get all registrations/students
 *   POST /API/Registration          -> create/register student
 *   POST /API/Registration/{studentId}/event/{eventId} -> register student to event
 */
@RestController
@RequestMapping("/API/Registration")
@CrossOrigin(origins = "http://localhost:5173")
@Tag(name = "Registration", description = "Student event registration")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    /**
     * GET /API/Registration
     * Returns all students and their registered events
     */
    @GetMapping
    @Operation(summary = "Get all registrations (students)")
    public ResponseEntity<List<Student>> getAllRegistrations() {
        return ResponseEntity.ok(registrationService.getAllStudents());
    }

    /**
     * POST /API/Registration
     * Creates a new student
     * Body: { "name": "Alice", "stdId": "AUCA2024001" }
     */
    @PostMapping
    @Operation(summary = "Register a new student")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student saved = registrationService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    /**
     * POST /API/Registration/{studentId}/event/{eventId}
     * Register an existing student for an event (Many-to-Many link)
     */
    @PostMapping("/{studentId}/event/{eventId}")
    @Operation(summary = "Register student for an event")
    public ResponseEntity<?> registerForEvent(
            @PathVariable Long studentId,
            @PathVariable Long eventId) {
        try {
            Student updated = registrationService.registerStudentForEvent(studentId, eventId);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
