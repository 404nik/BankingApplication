# iNB - Net Bank Online Banking Application

## Overview

iNB (Net Bank) is an online banking application for a fictitious bank called Net Bank (NB). It is a Spring Boot MVC application with JSP views and Oracle-backed repositories for common banking operations such as customer registration, account approval, cheque processing, deposits, withdrawals, and money transfers.

This repository is a training project, not a production banking system. It does not include database provisioning, schema migrations, seed data, or a secrets-management layer.

## What is in this repository

- Java Spring Boot 3.3.3 application code under `src/main/java/com/mindgate/main`.
- JSP views under `src/main/webapp/WEB-INF/views`.
- Static media under `src/main/resources/static`.
- Spring configuration in `src/main/resources/application.properties`.
- Maven Wrapper scripts (`mvnw` and `mvnw.cmd`) so contributors do not need to install Maven separately.
- A Spring context smoke test in `src/test/java/com/mindgate/main/BankingApplicationTests.java`.

## Prerequisites

Install or prepare these before running the application:

1. **Java 21 JDK**
   - The Maven build is configured with `<java.version>21</java.version>`.
   - Confirm with:

     ```sh
     java -version
     ```

2. **Network access for the first Maven Wrapper run**
   - `.mvn/wrapper/maven-wrapper.properties` downloads Apache Maven 3.9.9 from Maven Central when it is not already cached.
   - Maven will also resolve Spring Boot, Oracle JDBC, JSP/JSTL, Lombok, and test dependencies.

3. **Oracle Database / Oracle XE**
   - The checked-in Spring configuration points to Oracle XE at `jdbc:oracle:thin:@localhost:1521/xe`.
   - The configured database user is `Banking_Application`.
   - The repository does **not** create the Oracle user, schema, tables, stored data, or grants. Prepare a compatible schema before running the application or the Spring context test.

## Configuration and secrets

The default runtime settings live in `src/main/resources/application.properties`:

| Property | Default behavior |
| --- | --- |
| `server.port` | Runs the web application on port `8081`. |
| `spring.datasource.url` | Connects to local Oracle XE at `jdbc:oracle:thin:@localhost:1521/xe`. |
| `spring.datasource.username` | Uses the `Banking_Application` Oracle user. |
| `spring.datasource.password` | Must be treated as a secret. Do not copy committed passwords into docs, issues, or logs. |
| `logging.file.name` | Writes application logs to `BankingApplication.log`. |

Prefer environment variable overrides instead of editing tracked configuration. Spring Boot maps these variables to the datasource properties:

### POSIX shells

```sh
export SPRING_DATASOURCE_URL='jdbc:oracle:thin:@localhost:1521/xe'
export SPRING_DATASOURCE_USERNAME='Banking_Application'
export SPRING_DATASOURCE_PASSWORD='<your-local-password>'
```

### Windows PowerShell

```powershell
$env:SPRING_DATASOURCE_URL = 'jdbc:oracle:thin:@localhost:1521/xe'
$env:SPRING_DATASOURCE_USERNAME = 'Banking_Application'
$env:SPRING_DATASOURCE_PASSWORD = '<your-local-password>'
```

If your Oracle listener, service name, username, or password differ, change the override values to match your local database. Keep real credentials out of commits.

## Run from a clean checkout

Clone the repository, enter its root directory, configure the datasource overrides above, and run through the Maven Wrapper.

On POSIX systems, repair the executable bit if your checkout lost it:

```sh
chmod +x mvnw
```

Run the application:

### POSIX shells

```sh
./mvnw spring-boot:run
```

### Windows Command Prompt or PowerShell

```powershell
mvnw.cmd spring-boot:run
```

A successful startup prints `Banking Application Running`, writes `BankingApplication.log`, and serves the application at:

```text
http://localhost:8081
```

The application will fail during startup if Oracle is unavailable, the datasource credentials are wrong, or the expected schema objects are missing.

## Package and run the JAR

Build the executable Spring Boot JAR without running tests:

### POSIX shells

```sh
./mvnw -q -DskipTests package
```

### Windows Command Prompt or PowerShell

```powershell
mvnw.cmd -q -DskipTests package
```

Run the packaged application with the same datasource environment variables set:

### POSIX shells

```sh
java -jar target/Banking_Application-0.0.1-SNAPSHOT.jar
```

### Windows Command Prompt or PowerShell

```powershell
java -jar target\Banking_Application-0.0.1-SNAPSHOT.jar
```

## Run tests

The current test suite contains a `@SpringBootTest` context smoke test. It loads the real Spring configuration, including the Oracle datasource, so it is not self-contained.

Before running tests, make sure Oracle XE is reachable and the configured user has a compatible schema.

### POSIX shells

```sh
./mvnw test
```

### Windows Command Prompt or PowerShell

```powershell
mvnw.cmd test
```

If Oracle is not running or the schema is not prepared, fix the database prerequisite or datasource overrides rather than changing application code to bypass the connection.

## Developer notes

- Use `./mvnw` on POSIX systems and `mvnw.cmd` on Windows so everyone uses the repository-pinned Maven wrapper behavior.
- Generated build output belongs under `target/` and is ignored by Git.
- `BankingApplication.log` is generated at runtime; do not commit logs or local credentials.
- No CI workflow is currently committed in `.github/workflows`, `.gitlab-ci.yml`, `Jenkinsfile`, or `.circleci/config.yml`; use the package and test commands above as the local verification path.
