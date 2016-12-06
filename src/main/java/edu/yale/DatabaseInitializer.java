package edu.yale;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import edu.yale.domain.Person;
import edu.yale.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer {

    private PersonService personService;

    @Autowired
    public DatabaseInitializer(PersonService studentService) {
        this.personService = studentService;
    }

    /**
     * Populates the database
     */
    @PostConstruct
    public void populateDatabase() {
        // FIXME remove the sample/random data
        Person firstPerson = new Person("Michelle", "Obama", 40);
        Person firstPerson1 = new Person("Michelle", "Obama", 41);
        Person firstPerson2 = new Person("Michelle", "Obama", 42);
        Person firstPerson3 = new Person("Michelle", "Obama", 43);
        Person firstPerson4 = new Person("Michelle", "Obama", 44);
        Person firstPerson5 = new Person("Michelle", "Obama", 45);
        Person firstPerson6 = new Person("Michelle", "Obama", 46);
        Person secondPerson = new Person("nancy", "Ajram", 35);
        Person fourthPerson = new Person("Angela", "Merkel", 60);
        Person nancy = new Person(11, "nancy", "dbuai", "Nancy", "Ajram", "UAE");
        nancy.setLifedates("1984");

        personService.save(Arrays.asList(firstPerson, firstPerson1, firstPerson2,
                firstPerson3, firstPerson4, firstPerson5, firstPerson6, secondPerson, fourthPerson, nancy));
    }
}
