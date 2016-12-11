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
            String queryString = "select * from palmquist where ID < 100";
            ResultSet rs = statement.executeQuery(queryString);
            List<Person> persons = new ArrayList<>();

            while (rs.next()) {
                Person person = new Person();
                person.setPersonId(Long.valueOf(rs.getString(1)));
                person.setTitle(rs.getString(2));
                person.setFullName(rs.getString(3));

                person.setIndex(person.getTitle() + " " + person.getFullName());
                persons.add(person);
                System.out.println("Saved:" + person);
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
