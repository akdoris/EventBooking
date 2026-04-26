package com.auca.events.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * STUDENT Entity
 * Fields: id, name, stdId
 * Relationship: MANY-TO-MANY with Event
 * NOTE: Foreign key (eventId) lives in the JOIN TABLE on the student side
 */
@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String stdId; // Student ID e.g. "AUCA2024001"

    /**
     * MANY-TO-MANY Relationship
     * The JOIN TABLE (student_event) holds the foreign keys:
     *   - student_id  (FK -> students.id)
     *   - event_id    (FK -> events.id)   <-- "foreign key in student table" per exam
     */
    @ManyToMany
    @JoinTable(
        name = "student_event",          // join/registration table
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "event_id")  // eventID as foreign key
    )
    private Set<Event> registeredEvents = new HashSet<>();
}
