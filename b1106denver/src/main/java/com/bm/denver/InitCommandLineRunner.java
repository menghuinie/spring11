package com.bm.denver;

import com.bm.denver.model.Event;
import com.bm.denver.model.Group;
import com.bm.denver.model.GroupRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.Instant;
import java.util.Collections;
import java.util.stream.Stream;

@Component
class InitCommandLineRunner implements CommandLineRunner {

    private final GroupRepository repository;

    public InitCommandLineRunner(GroupRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) {        // method signature utilizes varargs. strings never used here.

        Stream.of("Seattle Group", "Denver Group", "Dublin Group", "Boston Group", "London Group").forEach(
                    name -> repository.save(new Group(name))
        );

        Group djug = repository.findByName("Seattle Group");
        //Lombok’s Builder is a helpful mechanism for using the Builder pattern without writing boilerplate code. 
        //We can apply this annotation to a Class or a method.
        Event e = Event.builder().title("eventTitle SpringBoot for Seattle")
                .description("eventDesc support in Seattle!")
                .date(Instant.parse("2022-09-13T17:00:00.000Z"))  // parses a string in ISO-8601 format, then converts the Instant to a Date.
                .build();
        djug.setEvents(Collections.singleton(e));  // returns an immutable set containing only a single element.
        repository.save(djug);

        Group djug2 = repository.findByName("Denver Group");
        e = Event.builder().title("eventTitle React for Denver")
                .description("eventDesc support in Denver!")
                .date(Instant.parse("2024-12-13T17:00:00.000Z"))
                .build();
        djug2.setEvents(Collections.singleton(e));
        repository.save(djug2);

        Group djug4 = repository.findByName("Boston Group");
        e = Event.builder().title("eventTitle NG for Boston")
                .description("eventDesc support in Boston!")
                .date(Instant.parse("2023-05-13T17:00:00.000Z"))  
                .build();
        djug4.setEvents(Collections.singleton(e));  
        repository.save(djug4);

        repository.findAll().forEach(System.out::println);      // list.forEach(System.out::println); 
        // :: operator, also known as the 'method reference' operator, was introduced in Java 8. 
        // It provides a way to reference methods or constructors in a concise and readable manner.
    }
}