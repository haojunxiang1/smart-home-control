A Spring Boot RESTful service that models and controls smart home appliances (Light, Fan, Air Conditioner) with an automatic yearly maintenance shutdown.

Requirements
Tool	Version
Java	17 or newer
Maven	3.9.x (the Maven Wrapper mvnw is provided)

Build
# using the Maven Wrapper
./mvnw clean install

# or your system Maven
mvn clean install

Run locally
./mvnw spring-boot:run
The application starts on http://localhost:8080 (default Spring Boot port).
Packaged JAR (alternative)
./mvnw clean package
java -jar target/smart-home-control-0.0.1-SNAPSHOT.jar

REST API quick reference
Action	                    Method & Path	                                                Notes
Turn on / off any device	POST /api/appliances/{id}/on POST /api/appliances/{id}/off	    id = light-1, fan-1, ac-1
Change fan speed (0 1 2)	POST /api/appliances/fan/{id}/speed/{level}	                    level =0 (off), 1, 2
Change AC mode	            POST /api/appliances/ac/{id}/mode/{mode}	                    mode =COOL, HEAT, DRY, OFF
List all devices	        GET /api/appliances	Returns JSON array of current states

Scheduled maintenance
A Spring @Scheduled job executes every January 1st at 01:00 (timezone America/Halifax) and sets every appliance to off.
The schedule and zone can be overridden in application.yml:
maintenance:
cron: "0 0 1 1 1 ?"
zone: "America/Halifax"

Test
./mvnw test

