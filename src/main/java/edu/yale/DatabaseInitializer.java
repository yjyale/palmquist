package edu.yale;

import edu.yale.domain.Person;
import edu.yale.service.PersonService;
import edu.yale.util.ConnectMSSQLServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

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
        System.out.println("Populating data to cache...");

        final ConnectMSSQLServer util = new ConnectMSSQLServer();

        List<Person> db = Collections.emptyList();

        try {
            Properties prop = new Properties();
            InputStream input = null;
            input = new FileInputStream("src/main/resources/db.prop");
            prop.load(input);
            String dbString = prop.getProperty("connection");
            String user = prop.getProperty("user");
            String pwd = prop.getProperty("pwd");
            db = util.populateIndex(dbString, user, pwd);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // save to cache

        try {
            System.out.println("Saving to local database");
            personService.save(db);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
