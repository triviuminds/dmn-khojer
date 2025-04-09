# dmn-khojer

## Overview
The **dmn-khojer** is a Java-based utility to handle DMN (Decision Model and Notation) files. It provides functionality to convert DMN files between XML and JSON formats, to store in NoSQL database and run search queries. The project is built using Gradle and supports Java 17.

Note: this is a on-going project and is not yet complete.  It will soon be converted to a springboot library.

## Features
- Convert DMN files from XML to JSON and vice versa.
- Wrap the DMN files with additional metadata.

It also includes
- Comprehensive unit tests with JUnit 5.
- Code coverage reporting using JaCoCo.
- Integrated with SonarQube for code quality analysis.

## Requirements
- **Java**: 17 or higher
- **Gradle**: 7.0 or higher
- **Sonar**: Optional, for code quality analysis


## Build and Run
### Build the Project
To build the project, run:
```bash
./gradlew build
```
To execute all tests, run:
```
./gradlew test
```
To generate a JaCoCo code coverage report, run:
```
./gradlew codeCoverageReport
```
To publish the library to local Maven
```
./gradlew publishToMavenLocal
```
To run the Sonar scan, run:
```
./gradlew sonar
```
Note: You need to have SonarQube account and update sonar.login in `sonar-project.properties` file and `sonar.gradle`.
