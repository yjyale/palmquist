# palmquist

This webapp is a rewrite of the Beinecke Library legacy application Palmquist. 
The project can be built using Maven, and the resulting .war file can be dropped into Tomcat (or just launched with `java`).
The stack is Spring Boot/MVC/Data.

The html files are in src/main/resources/templates.

All the backend logic is in package edu.yale.


Installation
--------------


- Install [Maven](https://maven.apache.org/) (and [JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)). Download sqljdbc4 from [Microsoft] (https://www.microsoft.com/en-us/download/details.aspx?id=11774).

- Specify the path to the database connection properties file in
src/main/java/edu/yale/DatabaseInitializer class.

- Specify either db.prop for "brothers server" or aws.prop for AWS RDS database called palmquist.

- Copy the file to /tmp directory and make sure it matches DatabaseInitializer (e.g.,
/tmp/aws.prop in both places)

* Build and Launch

```sh
# only the 1st time
mvn install:install-file -Dfile=sqljdbc4.jar -DgroupId=com.microsoft.sqlserver -DartifactId=sqljdbc4 -Dversion=4.0 -Dpackaging=jar

# go into the folder:
cd palmquist

# run the build and package it
mvn clean install

# to test it:

java -jar target/palmquist-0.0.1-SNAPSHOT.war

```




Deployment to AWS
------------------

For the remote server, scp to the AWS server the .war in target/
 to /usr/share/tomcat/webapps, and
rename it as ROOT.war so it deploys as the default context, as follows:

```
scp -i yul-palmquist-prod-app.pem palmquist/target/palmquist-0.0.1-SNAPSHOT.war ec2-user@10.5.68.174:/tmp/ROOT.war
scp -i yul-palmquist-prod-app.pem palmquist/src/main/resources/aws.prop ec2-user@10.5.68.174:/tmp

```

The folder where you're running this command should have the .pem file. Check paths to make
sure you can reach the .war file.

We're copying the aws.prop file to /tmp/aws.prop, so that it can pick up db properties
on load. You can delete it afterwards once the app is up and running.

Once you've copied (using scp) these files, log into AWS, and copy the .war file to /usr/share/tomcat/webapps.
It will automatically expand the .war file to full directory. The app should now be running at 8080.


Try it
--------------
- Hit (for local testing): http://localhost:8080/
- For local Tomcat: http://localhost:8080/palmquist-1.0.0-SNAPSHOT
- For AWS Tomcat Instance: http://server:8080