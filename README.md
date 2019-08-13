# Postings

Develop an application where texts could be posted freely, and these can have upvotes.
The application must allow:

- List current posts and their received upvotes.
- add a new post.
- add upvote to a post.

The backend project should be written in Spring Boot (https://start.spring.io). It should expose an API to be consumed by a frontend application written in a language of your choice (preferably ReactJS), persisting the data. In a database of your choice.

Feel free about styles and organization; the primary goal of this challenge is to validate your skills in the requested languages ​​and frameworks, as well as code quality.

To complete your test, you must:
Include unit and integration tests;
  - No environment setup should be required to run the tests. If environment setup is required to run the tests or the application, it must be automated with a single command;

## Implementation: 


Implementation:

It was developed the following solution:
Java API [postings.1.0.0.jar] using Spring Boot 2.0.6, that artefact was documented below Swagger framework, also was created an interface on React JavaScript library to manager post, it necessary to follow the next steps to putting solution run:

1. git clone https://github.com/jlopez34/postings

2. cd postings

3. mvn package

5. cd posting/target/

6. java -jar posting.1.0.0.jar

7. http://localhost:8080 on the browser that you prefer.

8. http://localhost:8080/swagger-ui.html/ api documentation
