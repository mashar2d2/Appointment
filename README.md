# Appointments project

This project present an application 
that provides access to information about
(working hours, employees, appointments)
appointments with a specialist in various fields of activity.

## Tech Stack

+ [Hibernate](https://hibernate.org/orm/documentation/5.4/)
+ [Jersey](https://eclipse-ee4j.github.io/jersey.github.io/documentation/latest/index.html)
+ [JUnit](https://junit.org/junit5/)
+ [Maven](https://maven.apache.org/guides/index.html)
+ [SQLite](https://sqlite.org/docs.html)

## Database layer

As DBMS chosen **_SQLite_**.

Data structure includes 3 models:

+ Appointment, 
+ Specialist, 
+ SpecWorkTime

For interaction with the database using _**Hibernate**_.

## Web layer

Web service implemented on _**REST API**_ using **_JAX-RS_**. 
As JAX-RS implementation chosen _**Jersey**_.

## Tests

Code covered with unit tests using JUnit.
For Dependency Injection into test was written InjectionExtension.
