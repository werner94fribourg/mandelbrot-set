# Mandelbrot set

## Introduction
The project consists of developping and display a Mandelbrot set using Java concurrent available libraries.

## How To Run the Project

The program requires Java 21 JDK and Maven to build and execute the project.

### Compile the project

```
mvn compile
```

### Execute the compiled project

```
mvn exec:java -Dexec.mainClass="org.mandelbrot.Main"
```

## Alternative: create and execute a package for the project

### Create a jar file for the project
```
mvn package
```
A new jar file will become available inside the target subfolder.

### Execute the jar file
Navigate into the folder containing the jar file and execute the following command (file named mandelbrot-set-1.0.0.jar in this example):
```
java -Dexec.mainClass=org.mandelbrot.Main -jar mandelbrot-set-1.0.0.jar
```
