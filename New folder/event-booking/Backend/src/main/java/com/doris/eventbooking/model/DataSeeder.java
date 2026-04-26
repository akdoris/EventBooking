package com.auca.events;

import com.auca.events.model.Event;
import com.auca.events.model.Student;
import com.auca.events.repository.EventRepository;
import com.auca.events.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

/**
 * Seeds initial demo data when the app starts
 */
@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedData(EventRepository eventRepo, StudentRepository studentRepo) {
        return args -> {
            // Create sample events
            Event e1 = new Event();
            e1.setTitle("Tech Innovation Summit");
            e1.setDate(LocalDate.of(2025, 5, 15));
            e1.setAvailableSeats(100);
            eventRepo.save(e1);

            Event e2 = new Event();
            e2.setTitle("AUCA Cultural Night");
            e2.setDate(LocalDate.of(2025, 5, 22));
            e2.setAvailableSeats(200);
            eventRepo.save(e2);

            Event e3 = new Event();
            e3.setTitle("Career Fair 2025");
            e3.setDate(LocalDate.of(2025, 6, 1));
            e3.setAvailableSeats(50);
            eventRepo.save(e3);

            // Create sample students
            Student s1 = new Student();
            s1.setName("Alice Uwimana");
            s1.setStdId("AUCA2024001");
            studentRepo.save(s1);

            Student s2 = new Student();
            s2.setName("Bob Nkurunziza");
            s2.setStdId("AUCA2024002");
            studentRepo.save(s2);

            System.out.println("✅ Demo data seeded successfully!");
        };
    }
}
