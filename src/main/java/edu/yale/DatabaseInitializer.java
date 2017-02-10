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
            final Properties prop = new Properties();

            // NOTE: Change the database path this as necessary:

            // for AWS:

            final String dir =  "/tmp/aws.prop";  //src/main/resources/db.prop

            // for brothers:

            //final String dir =  "src/main/resources/db.prop";

            System.out.println("Looking for db prop file in directory = " + dir);

            InputStream input = new FileInputStream(dir);
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
