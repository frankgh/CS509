# CS509 - Server WordSweeper

## wordsweeper-core Project

The wordsweeper-core project contains shared functionality between the wordsweeper-server project and the wordsweeper-admin-client project. Make sure to build this project before building the projects that depend on it.

### Compilation

```
mvn clean install
```

## wordsweeper-rest Project

The wordsweeper-rest project is a RESTful service that provides the
functionality for the WordSweeper game. The project produces a war file
that can be deployed into any container (i.e [Tomcat](http://tomcat.apache.org/)).
The project uses maven for dependency management and for building and 
deploying the war file.

### Requirements
- [Tomcat](http://tomcat.apache.org/)
- [Maven](https://maven.apache.org/)

### Tomcat Configuration

This is a one-time only configuration of Tomcat. 

#### Create a Tomcat User

Tomcat users are defined in the file – 
`$TOMCAT_HOME/conf/tomcat-users.xml`, by default, there is NO user,
it means no one can access the Tomcat manager page.

To enable users to access the Tomcat manager page, add a user as the 
role `manager-gui`.

##### `$TOMCAT_HOME/conf/tomcat-users.xml` 

```
<?xml version="1.0" encoding="UTF-8"?>
<!--
  <role rolename="tomcat"/>
  <role rolename="role1"/>
  <user username="tomcat" password="tomcat" roles="tomcat"/>
  <user username="both" password="tomcat" roles="tomcat,role1"/>
  <user username="role1" password="tomcat" roles="role1"/>
-->
  <role rolename="manager-script"/>
  <user password="tomcat-cs509" roles="manager-script" username="tomcat-script"/>
</tomcat-users>
```

Save it and restart Tomcat, now you should able to deploy using the maven command below

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
mvn clean install tomcat7:deploy -Denv=local -DskipTests -Dmaven.test.skip=true
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
The default build command will point the server to the localhost REST server
```
mvn clean install
```

### Building for custom REST server
To build with a custom server URL, use the following command, where yoururl.com is your own ip address or the domain name of your server; and yourportnumber is your tomcat port number
```
mvn clean install -Dwordsweeper.rest.server.url=http://yoururl.com:yourportnumber
```

### Running
```
java -cp wordsweeper-server-1.0-SNAPSHOT.jar com.wordsweeper.server.ServerLauncher
```

### Running With Options

```
java -cp wordsweeper-server-1.0-SNAPSHOT.jar com.wordsweeper.server.ServerLauncher --port 12345
```

## wordsweeper-admin-client Project

The Administrative client connects to the wordsweeper-server and allows us to view the status of the server.

### Building

```
mvn clean install
```

### Running
```
java -cp wordsweeper-admin-client-1.0-SNAPSHOT.jar com.wordsweeper.adminclient.AdminClientLauncher
```

### Running With Options
```
java -cp wordsweeper-admin-client-1.0-SNAPSHOT.jar com.wordsweeper.adminclient.AdminClientLauncher --host yourhost --port 12345
```