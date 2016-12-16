package edu.yale.util;


import edu.yale.domain.Person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConnectMSSQLServer {
    public List<Person> dbConnect(String db_connect_string,
                                  String db_userid,
                                  String db_password) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(db_connect_string,
                    db_userid, db_password);
            System.out.println("connected");
            Statement statement = conn.createStatement();
            String queryString = "select * from palmquist";
            //String queryString = "select * from palmquist where ID < 1000";
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

                person.setIndex(person.getTitle() + " " + person.getFullName() + " " + person.getAlias()
                + person.getNations() + " " + person.getStates() + " " + person.getCities());

                persons.add(person);
                //System.out.println("Saved:" + person);
            }
            statement.close();
            rs.close();
            conn.close();
            return persons;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return Collections.emptyList();

    }



}
