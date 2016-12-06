# palmquist

This webapp is a rewrite of the Beinecke Library legacy application Palmquist. 
The project can be built using Maven, and the resulting .war file can be dropped into Tomcat (or just launched with `java`).
The stack is Spring Boot/MVC/Data.

Installation
--------------

Install [Maven](https://maven.apache.org/) (and JDK). Download sqljdbc4 from [Microsoft] (https://www.microsoft.com/en-us/download/details.aspx?id=11774).

* Build and Launch
```sh
mvn install:install-file -Dfile=sqljdbc4.jar -DgroupId=com.microsoft.sqlserver -DartifactId=sqljdbc4 -Dversion=4.0 -Dpackaging=jar
cd palmquist
mvn clean install
cd target
java -jar palmquist-0.0.1-SNAPSHOT.war
```

Try it
--------------
- Hit: http://localhost:8080/
