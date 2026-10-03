# Employee Management System

A JavaFX desktop application for maintaining employee records, photos, payroll values, and basic statistical reports.

## Features

- Add, search, view, update, and delete employee records.
- Supports hourly, salaried, commission, and commission-based employees.
- Read employee records from CSV/text files and save the current records to CSV.
- Store and display employee photos.
- Show total salary, highest-paid employees by type, and sorted employee reports.

## Technologies & Tools

- **Java 25:** LTS JDK used to compile and run the application.
- **JavaFX 25:** Desktop UI controls, windows, tables, images, and styling.
- **Maven Wrapper:** Reproducible dependency management, builds, tests, and JavaFX launch commands.
- **JUnit 5:** Regression tests for file handling and payment calculations.

## Data Structures

- `ArrayList<Employee>` stores the active in-memory employee records.
- The `Employee` class hierarchy stores shared fields and type-specific payment data.
- `Address` groups each employee's street, city, and country.

## Prerequisites

- JDK 25 or newer available on `PATH`.
- Git and an internet connection for the first build so Maven can download dependencies.

## Getting Started

```powershell
git clone https://github.com/ElyasNajeh/Employee-Management-System.git
cd Employee-Management-System
.\mvnw.cmd clean verify
.\mvnw.cmd javafx:run
```

On macOS or Linux, use `./mvnw clean verify` and `./mvnw javafx:run`.

Use **Read File** and select `data/employees.csv` to load the included sample records. **Save File** writes the current records back to that file.

## Project Structure

- `src/main/java/application/` — existing Java classes and application entry point.
- `src/main/resources/application/` — bundled images and JavaFX CSS.
- `src/test/java/application/` — file and payment regression tests.
- `data/employees.csv` — writable employee data.
- `data/photos/` — sample and user-selected employee photos.
- `pom.xml`, `mvnw`, `.mvn/` — Maven build and wrapper configuration.

## Architecture

The existing application uses programmatic JavaFX screens and event-handler classes. `EmployeeData` holds the shared employee list, the `Employee` subclasses implement payment rules, and the read/save handlers persist that list to CSV without changing the original package or class organization.
