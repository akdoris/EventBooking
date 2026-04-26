package com.auca.events.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * EVENT Entity
 * Fields: id, title, date, availableSeats
 * Relationship: MANY-TO-MANY with Student
 */
@Entity
@Table(name = "events")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private int availableSeats;

    // MANY-TO-MANY: One event can have many students
    @ManyToMany(mappedBy = "registeredEvents")
    private Set<Student> registeredStudents = new HashSet<>();
}
