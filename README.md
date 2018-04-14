# seca-project-3
## NYC User Database Management System
[GitHub Dashboard](https://fast-bastion-29101.herokuapp.com/)

### What is it?
The city of New York needs a way to manage the users of its Database system. This is a monolithic Spring Boot application with a React front end that allows them to do just that. It allows for users to register accounts along with admin users to delete unnecessary accounts. Users can update their profiles at any time or delete them as well.

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
