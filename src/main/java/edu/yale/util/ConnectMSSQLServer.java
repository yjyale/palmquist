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
            //String queryString = "select * from palmquist where ID < 10000";
            String queryString = "select * from palmquist where ID < 1000";
            ResultSet rs = statement.executeQuery(queryString);
            List<Person> persons = new ArrayList<>();

            while (rs.next()) {
                Person person = new Person();
                person.setPersonId(Long.valueOf(rs.getString(1)));
                person.setTitle(rs.getString(2));
                person.setFullName(rs.getString(3));
                person.setAlias(rs.getString(4));
                person.setNations(rs.getString(5));
                person.setCities(rs.getString(6));
                person.setLifedates(rs.getString(7));
                person.setWorkdates(rs.getString(8));
                person.setBiobox(rs.getString(9));
                person.setPhotobox(rs.getString(10));
                person.setPhotofolders(rs.getString(11));
                person.setPhysicalDescriptionRegular(rs.getString(12));
                person.setAlbumbox(rs.getString(13));
                person.setPhysicalDescriptionAlbums(rs.getString(14));
                person.setFragilebox(rs.getString(15));
                person.setPhysicalDescriptionsFragilePhoto(rs.getString(16));
                person.setAudiovisualbox(rs.getString(17));
                person.setPhysicalDescriptionsAudioVisual(rs.getString(18));
                person.setOversizebox(rs.getString(19));
                person.setPhysicalDescriptionsOversize(rs.getString(20));

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
