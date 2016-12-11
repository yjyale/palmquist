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

        System.out.println("Populating sample data");


        Person firstPerson = new Person("Ms", "Gibbons");
        firstPerson.setNations(("USA"));
        firstPerson.setStates("CT");
        firstPerson.setCities("New Haven");
        firstPerson.setIndex("Ms Gibbons New Haven CT USA"); //tmp. set this to a different index record

        Person firstPerson2 = new Person("Madame", "Bard");
        Person firstPerson3 = new Person("Madame", "Bard");
        Person firstPerson4 = new Person("Madame", "Bard");
        Person firstPerson5 = new Person("Madame", "Bard");
        Person firstPerson6 = new Person("Madame", "Bard");
        Person firstPerson7 = new Person("Madame", "Bard");
        Person firstPerson8 = new Person("Madame", "Bard");
        Person firstPerson9 = new Person("Madame", "Bard");
        Person firstPerson10 = new Person("Madame", "Bard");
        Person firstPerson11= new Person("Madame", "Bard");
        Person firstPerson12 = new Person("Madame", "Bard");
        Person firstPerson13 = new Person("Madame", "Bard");
        Person firstPerson14 = new Person("Madame", "Alice");
        firstPerson14.setIndex("Madame Alice");
        firstPerson14.setNations("UK");


        personService.save(Arrays.asList(firstPerson, firstPerson2, firstPerson3, firstPerson4, firstPerson5
                , firstPerson6, firstPerson7, firstPerson8, firstPerson9, firstPerson10, firstPerson11, firstPerson12, firstPerson13, firstPerson14
        ));

    }
}
