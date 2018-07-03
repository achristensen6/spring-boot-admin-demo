
# Spring Boot Admin Demo
In order to show the functionalities available in Spring Boot Admin you will need to follow the instructions below. As an overview, you will:

 - Learn how to create the Admin server;
 - Learn how to create client Apps;
 - Run the demo Apps and try some of the Spring Boot Admin functionalities.

## Create the Admin server
To create the Admin server you just need to build a regular Spring Boot app with the following details:

 - Add the dependencies `spring-boot-admin-starter-server` and `spring-boot-admin-dependencies` to the `pom.xml`;
```
<dependency>
	<groupId>de.codecentric</groupId>
	<artifactId>spring-boot-admin-starter-server</artifactId>
</dependency>
```
```
<dependencyManagement>
	<dependencies>
		<dependency>
			<groupId>de.codecentric</groupId>
			<artifactId>spring-boot-admin-dependencies</artifactId>
			<version>${spring-boot-admin.version}</version>
			<type>pom</type>
			<scope>import</scope>
		</dependency>
	</dependencies>
</dependencyManagement>
```

 - Add the `@EnableAdminServer` annotation to your primary application configuration class.

## Create Client Apps
In order to create Apps that register themselves with the Admin server you need to build regular Spring Boot apps adding the follow details:

 - Add the dependencies `spring-boot-admin-starter-client` and `spring-boot-admin-dependencies` to the `pom.xml`;
```
<dependency>
	<groupId>de.codecentric</groupId>
	<artifactId>spring-boot-admin-starter-client</artifactId>
</dependency>
```
```
<dependencyManagement>
	<dependencies>
		<dependency>
			<groupId>de.codecentric</groupId>
			<artifactId>spring-boot-admin-dependencies</artifactId>
			<version>${spring-boot-admin.version}</version>
			<type>pom</type>
			<scope>import</scope>
		</dependency>
	</dependencies>
</dependencyManagement>
```
 - Configure Admin server endpoint in `application.properties`;
```
spring.boot.admin.client.url = http://localhost:8080
```
 - Define App name to be shown in Spring Boot Admin console;
```
spring.application.name = Table Tennis
```
 - Configure the App to expose all Actuator endpoints and to show full health details;
```
management.endpoints.web.exposure.include = *
management.endpoint.health.show-details = always
```

## Demo
So that you can see the Spring Boot Admin functionalities for yourself you need to run the demo apps:

 - Run the Admin Server (`admin-server` project);
 - Run the AutoDB app (`admin-client-autodb` project) and check for the `Application registered itself as {id}` log during startup;
 - Run the Table Tenis app (`admin-client-reactive` project) and check for the `Application registered itself as {id}` log during startup.

### Spring Boot Admin dashboard
Open http://localhost:8080/ in the browser and check that it shows the 2 different apps on it.
As an additional exercise you can run another instance of the `Table Tenis` app and then check in the Spring Boot Admin dashboard that the 2 instances are displayed together.
Clicking on the *id* with which an app registered itself allows to get details regarding that app (you should be redirected to something similar to http://localhost:8080/#/instances/cd3777b14aa7/).

### App instance Details
In the `Details` tab of a given App instance you have generic information regarding that app:

 - Instance uptime;
 - Threads count evolution with time;
 - Garbage collection information;
 - Memory consumption.

### App instance Metrics
In the `Metrics` tab you can check more detailed information about a given App instance.
As an example, perform the following steps:

 1. Call one of the endpoints exposed by the `Table Tenis` app, e.g. http://localhost:50345/ping;
 2. In the Spring Boot Admin dashboard select the instance of the `Table Tenis` app you just called;
 3. Go to the `Metrics` tab;
 4. In the top dropdown choose `http.server.requests`;
 5. In the `exception` dropdown choose `None`;
 6. In the `method` dropdown choose `GET`;
 7. In the `uri` dropdown choose `/ping`;
 8. In the `status` dropdown choose `200`;
 9. The detailed information (calls count, calls total time, calls max time) regarding the successful calls to the endpoint should be displayed;
 10. Repeat step 1 and check that the detailed information is updated.

### App instance Environment
In the `Environment` tab you can check more detailed information about the environment properties (system and configured via `application.properties`) with which the App instance is running.

### App instance Logs
In the `Logfile` tab you can access the App instance logs. The `Table Tenis` app was configured so that the logs are available:
```
management.endpoint.logfile.external-file = target/log.log
logging.file = target/log.log
```

In the `Loggers` tab you can check the logs level defined for the App instance. You can also change it at runtime. As an example, perform the following steps:

 1. Call the two endpoints exposed by the `Table Tenis` app (http://localhost:50345/ping and http://localhost:50345/pong);
 2. In the Spring Boot Admin dashboard select the instance of the `Table Tenis` app you just called;
 3. Go to the `Logfile` tab;
 4. Check for a `ping` log with `INFO` level;
 5. Check for a `pong` log with `WARN` level;
 6. Go to the `Loggers` tab and change the log level for `io.mercedesbenz.springio.adminclientreactive` to `WARN`;
 7. Repeat steps 1, 2 and 3;
 9. Check there is no new `ping` log;
 8. Check for a new `pong` log with `WARN` level.

### App instance Threads
In the `Threads` tab you can check more detailed information about each App instance thread.

### App instance HTTP Traces
In the `Http Traces` tab you can check detailed information about each HTTP request to the App instance. As an example, perform the following steps:

 1. In the Spring Boot Admin dashboard select the instance of the `Table Tenis` app you are about to call;
 2. Go to the `HttpTraces` tab;
 3. Call one of the endpoints exposed by the `Table Tenis` app, e.g. http://localhost:50345/ping;
 4. Check that a new request is displayed in the `HttpTrances` tab (you can access the request details by clicking on it).

### App instance Scheduled Tasks
TODO

### Actuator endpoints base path
Actuator endpoints base path can be changed via configuration in `application.properties`:
```
management.endpoints.web.base-path = /new-actuator-base-path
```
