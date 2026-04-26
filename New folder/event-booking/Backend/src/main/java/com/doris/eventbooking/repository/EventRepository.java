package com.auca.events.repository;

import com.auca.events.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    // JpaRepository gives us: findAll(), findById(), save(), deleteById() for free
}
