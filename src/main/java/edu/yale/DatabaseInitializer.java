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

import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

@Component
public class DatabaseInitializer {

    private final Logger logger = getLogger(this.getClass());


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

        logger.debug("Populating data to cache...");

        final ConnectMSSQLServer util = new ConnectMSSQLServer();

        List<Person> db = Collections.emptyList();

        try {
            final Properties prop = new Properties();

            // NOTE: Change the database path this as necessary:

            // for AWS:

            final String dir =  "/opt/palmquist/aws.prop";  //src/main/resources/db.prop

            // for brothers:

            //final String dir =  "src/main/resources/db.prop";

            logger.debug("Looking for db prop file in directory = " + dir);

            InputStream input = new FileInputStream(dir);
            prop.load(input);
            String dbString = prop.getProperty("connection");
            String user = prop.getProperty("user");
            String pwd = prop.getProperty("pwd");
            db = util.populateIndex(dbString, user, pwd);
        } catch (Exception e) {
            logger.error("Error connecting to the database", e);
            // throw e;
        }

        // save to cache

        try {
            logger.debug("Saving to local cache");
            personService.save(db);
        } catch (Exception e) {
          logger.error("Error setting up cache", e);
          // throw e;
        }

    }
}
