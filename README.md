# seca-project-3
## NYC User Database Management System

### What is it?
The city of New York requested a new feature to add to their user management system that had been created earlier. For this project I have created a new posting service that allows posts from any user. Once the post has been approved by an admin user it will be shown to any visitor of the site. 

### Technologies
* Java
* Spring Boot
* React


### Before Running Or Testing
Before you run the application for the first time, you may want to enter the 'ui' directory and run 'npm install' in your terminal, since the node_modules folder is excluded form upload.

```bash
$ cd ui
$ npm install
```

### Running Application
To launch the application all you should need is to run docker-compose up in the project directory in terminal

```bash
$ docker-compose up
```

### Running Tests
All application tests are runnable with a single command from the project root directory. You only have to run the command below in your terminal

```bash
$ ./gradlew test
```
