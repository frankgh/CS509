# CS509 - Server WordSweeper

## wordsweeper-rest Project

The wordsweeper-rest project is a RESTful service that provides the
functionality for the WordSweeper game. The project produces a war file
that can be deployed into any container (i.e [Tomcat](http://tomcat.apache.org/)).
The project uses maven for dependency management and for building and 
deploying the war file.

### Requirements
- [Tomcat](http://tomcat.apache.org/)
- [Maven](https://maven.apache.org/)

### Third Party Libraries
- Jersey Containers
- Jackson (for JSON)
- Hibernate (ORM for the persistence layer)
- Apache Commons (commons-lang3 for String operations)
- [H2 Database](http://www.h2database.com/html/main.html) (In-memory database)
- JUnit 4.9 (For Unit Testing)

### Deploying
To deploy the application in tomcat (Running in localhost port 8080),
run the following command:
```
mvn clean install tomcat7:deploy -Denv=local
```

### API
#### Create Game Request
To create a game request we invoke the following URL and provide a user:
```
http://localhost:8080/wordsweeper/rest/game/create/{user}
```

To create a game request using a password for the game we use the
following URL:
```
http://localhost:8080/wordsweeper/rest/game/create/{user}/password/{password}
```
#### Join Game Request
To join a game, we invoke the following URL, provided that we know a 
game ID:
```
http://localhost:8080/wordsweeper/rest/game/join/{game_id}/{user}
```

If the game is password protected, we need to provide the password in
the URL:
```
http://localhost:8080/wordsweeper/rest/game/join/{game_id}/{user}/password/{password}
```

## wordsweeper-server Project

The wordsweeper-server project is a thin server that will interface
between WordSweeper Clients and the the WordSweeper REST Server. The
wordsweeper-server project implements version **1.2** of the WordSweeper
protocol. The wordsweeper-server project uses maven to build a jar file.

### Requirements
- [Maven](https://maven.apache.org/)

### Third Party Libraries
- Retrofit 2
- Gson
- OkHttp

### Building
```
mvn clean install
```

### Running
```
java -cp wordsweeper-server-1.0-SNAPSHOT.jar com.wordsweeper.server.ServerLauncher
```