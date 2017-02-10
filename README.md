# palmquist

This webapp is a rewrite of the Beinecke Library legacy application Palmquist. 
The project can be built using Maven, and the resulting .war file can be dropped into Tomcat (or just launched with `java`).
The stack is Spring Boot/MVC/Data.

The html files are in src/main/resources/templates.

All the backend logic is in package edu.yale.


Installation
--------------

- Install [Maven](https://maven.apache.org/) (and [JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)). Download sqljdbc4 from [Microsoft] (https://www.microsoft.com/en-us/download/details.aspx?id=11774).

* Build and Launch
```sh
mvn install:install-file -Dfile=sqljdbc4.jar -DgroupId=com.microsoft.sqlserver -DartifactId=sqljdbc4 -Dversion=4.0 -Dpackaging=jar
cd palmquist
mvn clean install
cd target
java -jar palmquist-0.0.1-SNAPSHOT.war

# or deploy the resulting .war to Tomcat

```



Configuration
--------------

Specify the path to the properties file in src/main/java/edu/yale/DatabaseInitializer class. Specify
either db.prop for brothers or aws.prop for AWS EC2 instance. Just add the username and password to the SqlServer database or RDS instance.

```
connection=jdbc:sqlserver://brothers.library.yale.edu:1433;databaseName=test_pamoja
# change it for aws
user=
password=
```

Deployment to AWS
------------------

For the remote server, scp to the AWS server to /usr/share/tomcat/webapps
rename it as ROOT.war so it deploys as the default context


Try it
--------------
- Hit: http://localhost:8080/
- For local Tomcat: http://localhost:8080/palmquist-1.0.0-SNAPSHOT
- For AWS TOMCAT: http://server:8080