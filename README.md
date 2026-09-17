# ⚡ FocusTask — Mini Full-Stack Spring Boot & MySQL Application

[![Java](https://img.shields.io/badge/Java-21%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.x%20%2F%203.x-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)
[![Hibernate](https://img.shields.io/badge/Hibernate-ORM-59666C?style=for-the-badge&logo=hibernate&logoColor=white)](https://hibernate.org/)
[![Postman](https://img.shields.io/badge/Postman-Tested-FF6C37?style=for-the-badge&logo=postman&logoColor=white)](https://www.postman.com/)

> **A complete, production-patterned full-stack task management application** built to master enterprise Spring Boot development: layered architecture, Inversion of Control (IoC), Spring Data JPA, Hibernate ORM, Bean Validation, centralized exception handling (`@RestControllerAdvice`), Postman automated test suites, and a responsive Vanilla JavaScript frontend.

---

## 📑 Table of Contents
1. [Overview & Highlights](#-overview--highlights)
2. [Layered Architecture](#-layered-architecture)
3. [Tech Stack](#-tech-stack)
4. [Getting Started & Local Setup](#-getting-started--local-setup)
5. [Database Configuration](#-database-configuration)
6. [API Endpoints & Contracts](#-api-endpoints--contracts)
7. [Validation & Error Handling](#-validation--error-handling)
8. [Postman Testing Suite](#-postman-testing-suite)
9. [Tiny Frontend UI](#-tiny-frontend-ui)
10. [Roadmap Completion Checklist](#-roadmap-completion-checklist)
11. [Future Enhancements](#-future-enhancements)

---

## 🌟 Overview & Highlights

- **Layered Architecture**: Strict separation of concerns across `Controller` $\rightarrow$ `Service` $\rightarrow$ `Repository` $\rightarrow$ `Entity`.
- **Database Persistence**: Automatic schema management using Hibernate with MySQL 8.0 and HikariCP connection pooling.
- **Robust Bean Validation**: Jakarta Bean Validation (`@Valid`, `@NotBlank`, `@Size`) ensuring high data integrity.
- **Centralized Error Handling**: Global `@RestControllerAdvice` converting exceptions into standardized, client-friendly JSON error payloads (`400 Bad Request`, `404 Not Found`).
- **Complete Test Coverage**: Bundled Postman collection covering all positive and negative test cases with automated JavaScript assertions.
- **Single-Page Vanilla JS Frontend**: Responsive, lightweight DOM UI served directly by Spring Boot on `http://localhost:8081/` with full CORS support.

---

## 🏗️ Layered Architecture

```text
Browser / Postman Client
       │
       ▼ [HTTP Request - JSON Body]
┌─────────────────────────────────────────────────────────┐
│ Embedded Apache Tomcat Server (Port 8081)               │
└────────────────────────────┬────────────────────────────┘
                             │
                             ▼
┌─────────────────────────────────────────────────────────┐
│ Controller Layer (TaskController)                       │
│ - Exposes REST endpoints (@GetMapping, @PostMapping...) │
│ - Enforces input validation (@Valid)                    │
│ - Keeps methods thin; delegates to Service layer        │
└────────────────────────────┬────────────────────────────┘
                             │
                             ▼
┌─────────────────────────────────────────────────────────┐
│ Service Layer (TaskService)                             │
│ - Owns business logic and validation decisions          │
│ - Coordinates between Controller and Repository         │
│ - Throws domain-specific TaskNotFoundException          │
└────────────────────────────┬────────────────────────────┘
                             │
                             ▼
┌─────────────────────────────────────────────────────────┐
│ Repository Layer (TaskRepository - Spring Data JPA)    │
│ - Extends JpaRepository<Task, Long>                     │
│ - Generates optimized SQL at runtime (Zero boilerplate) │
└────────────────────────────┬────────────────────────────┘
                             │
                             ▼
┌─────────────────────────────────────────────────────────┐
│ Persistence Engine (Hibernate ORM + HikariCP)           │
│ - Maps Task entity fields to columns in table 'tasks'   │
└────────────────────────────┬────────────────────────────┘
                             │
                             ▼
┌─────────────────────────────────────────────────────────┐
│ MySQL Server 8.0 (Database: focustask_db)               │
└─────────────────────────────────────────────────────────┘
```

---

## 💻 Tech Stack

| Layer | Technologies Used |
| :--- | :--- |
| **Backend** | Java 21 / 23, Spring Boot 4.x, Spring MVC, Spring Data JPA, Hibernate, Jakarta Bean Validation |
| **Database** | MySQL Server 8.0, HikariCP Connection Pool |
| **Frontend** | HTML5, Modern CSS3 (CSS Variables, Flexbox), Vanilla JavaScript (ES6+ `fetch()` API) |
| **Build & Tooling** | Maven Wrapper (`mvnw`), Postman API Client, Git / GitHub |

---

## 🚀 Getting Started & Local Setup

### Prerequisites
- **Java JDK**: Version 21 or higher installed (`java -version`)
- **MySQL Server**: Version 8.0 running locally on port `3306`
- **Git**: Installed on your machine

### 1. Clone the Repository
```bash
git clone https://github.com/Anushka-google/FocusTask.git
cd FocusTask
```

### 2. Configure Database
Log into your MySQL terminal or Workbench and create the database:
```sql
CREATE DATABASE IF NOT EXISTS focustask_db;
```

Update your database credentials in `src/main/resources/application.properties` (or set environment variables `DB_USERNAME` and `DB_PASSWORD`):
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/focustask_db?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD
```

### 3. Run the Application
You can run the application directly with the included Maven Wrapper:

**Windows (PowerShell):**
```powershell
.\mvnw.cmd spring-boot:run
```

**Linux / macOS:**
```bash
./mvnw spring-boot:run
```

The application will start on **`http://localhost:8081`**!

### 4. Build and Run Standalone JAR
To test running the application without Maven:
```powershell
.\mvnw.cmd clean package -DskipTests
java -jar target/focustask-0.0.1-SNAPSHOT.jar
```

---

## 🔌 API Endpoints & Contracts

Base URL: `http://localhost:8081/api/tasks`

| Method | Endpoint | Description | Success Status | Error Status |
| :--- | :--- | :--- | :--- | :--- |
| **POST** | `/api/tasks` | Create a new task | `201 Created` | `400 Bad Request` |
| **GET** | `/api/tasks` | Retrieve all tasks | `200 OK` | `500 Server Error` |
| **GET** | `/api/tasks/{id}` | Retrieve task by ID | `200 OK` | `404 Not Found` |
| **PUT** | `/api/tasks/{id}` | Update existing task | `200 OK` | `400 Bad Request` / `404 Not Found` |
| **DELETE** | `/api/tasks/{id}` | Delete task by ID | `204 No Content`| `404 Not Found` |

---

## 📝 Example JSON Payloads

### 1. Create Task (`POST /api/tasks`)
**Request Body:**
```json
{
  "title": "Master Spring Boot",
  "description": "Build complete CRUD REST API and connect MySQL",
  "completed": false
}
```
**Response (`201 Created`):**
```json
{
  "id": 1,
  "title": "Master Spring Boot",
  "description": "Build complete CRUD REST API and connect MySQL",
  "completed": false
}
```

### 2. Validation Failure (`POST /api/tasks` with empty title)
**Response (`400 Bad Request`):**
```json
{
  "status": 400,
  "error": "Validation Failed",
  "errors": {
    "title": "Title is mandatory"
  },
  "timestamp": "2026-09-17T18:56:49.765222900"
}
```

### 3. Missing Task (`GET /api/tasks/999`)
**Response (`404 Not Found`):**
```json
{
  "message": "Task not found with id: 999",
  "status": 404,
  "timestamp": "2026-09-17T18:59:23.580640700"
}
```

---

## 🧪 Postman Testing Suite

A complete Postman Collection is included in the root directory:
👉 **`FocusTask.postman_collection.json`**

### Steps to Import & Run:
1. Open **Postman**.
2. Click **Import** $\rightarrow$ select `FocusTask.postman_collection.json`.
3. Click **Run Collection**.
4. All 8 tests (create, validate, list, get by id, update, delete, 404 checks) will run and pass automatically with green assertions!

---

## 🎨 Tiny Frontend UI

The application includes a built-in Vanilla JS web interface served directly at the root URL:
👉 **`http://localhost:8081/`**

### Features:
- **Interactive Add Task Form**: Real-time validation and instant DOM insertion.
- **Dynamic Task Cards**: Responsive design with task counts, completion checkboxes, and delete buttons.
- **Full State Handling**: Loading indicators, empty list state, and auto-dismissing feedback banners.
- **Zero Framework Bloat**: Pure HTML5, CSS3, and JavaScript ES6+ fetch API.

---

## 📋 Roadmap Completion Checklist

- [x] **Phase 0 & 1**: Java Prerequisites & Spring vs Spring Boot fundamentals
- [x] **Phase 2**: Project setup with Maven, Web, Data JPA, and MySQL driver
- [x] **Phase 3**: IoC, Spring Beans, and Constructor Dependency Injection
- [x] **Phase 4**: REST principles, JSON serialization, and `@RestController`
- [x] **Phase 5**: `Task` Entity modeling with JPA & Hibernate annotations
- [x] **Phase 6**: MySQL database connectivity & automatic DDL schema creation
- [x] **Phase 7**: Repository abstraction with `JpaRepository<Task, Long>`
- [x] **Phase 8**: Service layer architecture and business logic isolation
- [x] **Phase 9**: Full CRUD implementation across 5 REST endpoints
- [x] **Phase 10**: Custom `TaskNotFoundException` and `@RestControllerAdvice`
- [x] **Phase 11**: Jakarta Bean Validation (`@Valid`, `@NotBlank`, `@Size`)
- [x] **Phase 12**: Comprehensive Postman Collection with automated test scripts
- [x] **Phase 13**: Responsive Vanilla JS frontend with dynamic DOM rendering & CORS
- [x] **Phase 14**: Executable standalone fat-JAR build and runtime lifecycle verification
- [x] **Phase 15**: Professional GitHub repository setup and comprehensive documentation

---

## 🔮 Future Enhancements
- [ ] Add pagination and sorting via `Pageable` and `Page<Task>`.
- [ ] Implement user authentication with Spring Security and JWT.
- [ ] Add task categories, due dates, and priority filters.
- [ ] Dockerize the application with `Dockerfile` and `docker-compose.yml` for MySQL.

---

## 👤 Author
**Anushka Kaushal** — [GitHub Profile](https://github.com/Anushka-google)
