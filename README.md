# palmquist

This webapp is a rewrite of the Beinecke Library legacy application Palmquist. 
The project can be built using Maven, and the resulting .war file can be dropped into Tomcat (or just launched with `java`).
The stack is Spring Boot/MVC/Data.

Installation
--------------

- Install [Maven](https://maven.apache.org/) (and [JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)). Download sqljdbc4 from [Microsoft] (https://www.microsoft.com/en-us/download/details.aspx?id=11774).

- Copy file db.prop to src/main/resources (as documented below).

* Build and Launch
```sh
mvn install:install-file -Dfile=sqljdbc4.jar -DgroupId=com.microsoft.sqlserver -DartifactId=sqljdbc4 -Dversion=4.0 -Dpackaging=jar
cd palmquist
mvn clean install
cd target
java -jar palmquist-0.0.1-SNAPSHOT.war

# or deploy the resulting .war to Tomcat

```

Development
--------------
The html files are in src/main/resources/templates.

All the backend logic is in package edu.yale.


Configuration
--------------

Specify the path to the properties file in DatabaseInitializer class. Specify
either db.prop for brothers or aws.prop for AWS EC2 instance.

Just add the username and password to the SqlServer database or RDS instance.

```
connection=jdbc:sqlserver://brothers.library.yale.edu:1433;databaseName=test_pamoja
user=
password=
```

Try it
--------------
- Hit: http://localhost:8080/
- For Tomcat: http://localhost:8080/palmquist-1.0.0-SNAPSHOT