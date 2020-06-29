# Cinema
# Table of Contents
* [Project purpose](#purpose)
* [Project structure](#structure)
* [For developers](#for-developers)
* [Authors](#authors)
# <a name="purpose">Project purpose</a>

This is a template for creating an simple version of RESTfull application of cinema ticket shop.

Users with USER role can:
* add tickets to their shopping carts,
* see movies list
* complete orders
* and see all their order
* see all available movie sessions

Users with ADMIN role can :
* add new movies 
* add new movie sessions,
* add new movie halls
* see all users list
 
Not login users have access to:
* registration
* login 
<hr>

# <a name="structure">Project Structure</a>
* Java 11
* Maven 4.0.0
* hibernate 6.1.0
* spring-framework 2.5.7
* spring-security 5.3.2
* log4j 1.2.17
* maven-checkstyle-plugin

<hr>

# <a name="for-developers">For developers</a>
Open the project in your IDE.
Add it as maven project.
Configure Tomcat:
* add artifact
* add sdk 11.0.3
* crate schema "cinema" in your database
* change database properties in db.properties file
* change location of log files in log4j.properties file

When application starts some test data injects into database:
* 1 user with role USER and ADMIN
You can login with email: admin@gmail.com
and password: 1111. You can simply disable it or change in InjectController class;
<hr>

# <a name="authors">Authors</a>
* [Maksym Durov](https://github.com/maks45)