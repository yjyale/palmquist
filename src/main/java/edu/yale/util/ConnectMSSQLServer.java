package edu.yale.util;


import edu.yale.domain.Person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

public class ConnectMSSQLServer {

  private final Logger logger = getLogger(this.getClass());


    /**
     * Connects to the database and populates an in-memory database.
     *
     * @param db_connect_string
     * @param db_userid
     * @param db_password
     * @return
     */
    public List<Person> populateIndex(String db_connect_string,
                                      String db_userid,
                                      String db_password) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(db_connect_string,
                    db_userid, db_password);
            logger.debug("Connection to database OK");
            Statement statement = conn.createStatement();
            String queryString = "select * from palmquist";

            boolean debug = false; // change as necessary

            if (debug) {
                queryString = "select * from palmquist where ID < 1000"; // change as necesssary
            }

            ResultSet rs = statement.executeQuery(queryString);
            List<Person> persons = new ArrayList<>();

            while (rs.next()) {
                Person person = new Person();
                person.setPersonId(Long.valueOf(rs.getString("ID")));
                person.setTitle(rs.getString("Title"));
                person.setFullName(rs.getString("Full_Name"));
                person.setAlias(rs.getString("Also known as Description"));
                person.setNations(rs.getString("Nations"));
                person.setStates(rs.getString("State"));
                person.setCities(rs.getString("Cities"));
                person.setLifedates(rs.getString("Life Dates"));
                person.setWorkdates(rs.getString("Work Dates"));
                person.setBiobox(rs.getString("Bio Box"));
                person.setPhotobox(rs.getString("Photo Box"));
                person.setPhotofolders(rs.getString("Photo Folders"));
                person.setPhysicalDescriptionRegular(rs.getString("Physical Description - Regular"));
                person.setAlbumbox(rs.getString("Album Box"));
                person.setPhysicalDescriptionAlbums(rs.getString("Physical Description - Albums"));
                person.setFragilebox(rs.getString("Fragile Photo Box"));
                person.setPhysicalDescriptionsFragilePhoto(rs.getString("Physical Description - Fragile Photo"));
                person.setAudiovisualbox(rs.getString("Audiovisual Box"));
                person.setPhysicalDescriptionsAudioVisual(rs.getString("Physical Description - Audiovisual"));
                person.setOversizebox(rs.getString("Oversize Photo Box"));
                person.setPhysicalDescriptionsOversize(rs.getString("Physical Description - Oversize"));


                String title = person.getTitle();

                if (title == null) {
                    title = " ";
                } else {
                    title = " " + title;
                }

                String fullName = person.getFullName();

                if (fullName != null && fullName.contains("-")) { //e.g. Marie-Jones should be Marie Jones for search
                    fullName = fullName.replace("-", " ");
                }

                String alias = person.getAlias();

                if (alias != null && alias.contains("-")) {
                    alias = alias.replace("-", " ");
                }

                final String index = title + " " + fullName + " " + alias
                        + " " + person.getNations() + " " + person.getStates() + " " + person.getCities();

                person.setIndex(index);
                persons.add(person);
            }
            statement.close();
            rs.close();
            conn.close();
            return persons;
        } catch (Exception e) {

            logger.error("error with db:", e);
        }
        return Collections.emptyList();
    }


}
