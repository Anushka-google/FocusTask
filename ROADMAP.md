# FocusTask — Complete Spring Boot Learning + Mini Full-Stack Project Roadmap

**Goal:** Learn Spring Boot deeply enough to explain the concepts, build a real CRUD REST API, connect MySQL, handle errors, test with Postman, and finish with a tiny HTML/CSS/JavaScript frontend.

**Method:** WHY → WHAT → HOW → CODE → TEST → EXPLAIN. Do not copy the project blindly.

---

## Final Architecture
```
Browser (HTML/CSS/JS) → REST API (Spring Boot) → Controller → Service → Repository → JPA/Hibernate → MySQL
```
**Final Scope:** One `Task` entity, five CRUD endpoints, one custom not-found exception, one global exception handler, basic validation, Postman collection, and a tiny frontend.

---

## 1. Master Checklist — Nothing Important Left Out

| Area | Concepts / Words to Know |
| :--- | :--- |
| **Java prerequisites** | Classes, objects, constructors, interfaces, inheritance, abstraction, encapsulation, collections, exceptions, generics, enums, access modifiers, packages, Maven basics |
| **Spring Boot** | Spring vs Spring Boot, starter dependencies, auto-configuration, embedded server, convention over configuration, `@SpringBootApplication`, project structure, application.properties, profiles (basic awareness) |
| **IoC / DI** | IoC, Dependency Injection, Spring container, ApplicationContext, Bean, component scanning, `@Component`, `@Service`, `@Repository`, constructor injection, `@Autowired` (understand, prefer constructor injection) |
| **REST / HTTP** | REST, resource, endpoint, URL, HTTP methods, GET/POST/PUT/DELETE, request/response, headers, status codes, JSON, idempotency (basic), CRUD |
| **Spring MVC** | `@RestController`, `@RequestMapping`, `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`, `@RequestBody`, `@PathVariable`, `@RequestParam` (basic awareness), `ResponseEntity` (basic) |
| **JPA / Hibernate** | JPA, Hibernate, ORM, entity, persistence context (basic), `@Entity`, `@Id`, `@GeneratedValue`, table/column mapping (basic), JpaRepository |
| **Database** | MySQL, schema, table, row, primary key, SQL CRUD awareness, JDBC driver, connection properties |
| **Spring Data** | Repository abstraction, derived query idea, save, findAll, findById, deleteById, Optional, existsById (awareness) |
| **Architecture** | Controller → Service → Repository → DB, separation of concerns, dependency direction, why business logic belongs in Service |
| **Exceptions** | checked vs unchecked (Java review), custom exception, `@ExceptionHandler`, `@RestControllerAdvice`, HTTP 404, clean error JSON |
| **Validation** | Why validation exists, `@Valid`, `@NotBlank`, `@Size` (basic; recommended), validation error awareness |
| **Testing** | Postman, JSON body, headers, happy path, negative path, CRUD verification |
| **Frontend** | HTML structure, CSS basics, JavaScript fetch, GET/POST/PUT/DELETE, JSON parsing, CORS basic concept, DOM rendering |
| **Build / run** | Maven lifecycle, `mvn spring-boot:run`, `mvn clean package`, JAR, port 8080/8081, logs |
| **GitHub** | git init/add/commit/push, .gitignore, README, API documentation, screenshots |
| **Interview understanding** | Request lifecycle, DI, Bean, JPA vs Hibernate, REST vs frontend, Repository role, Service role, exception flow, why layered architecture |

---

## 2. Phase 0 — Java + Web Prerequisites
- Confirm Java fundamentals: class/object, constructor, interface, encapsulation, inheritance, polymorphism.
- Review exceptions: try/catch, throw, throws, RuntimeException.
- Review interfaces and generics because `JpaRepository` is an interface using generics.
- Understand HTTP basics: client, server, request, response, URL, method, status code, JSON.
- Know basic SQL: `CREATE TABLE`, `INSERT`, `SELECT`, `UPDATE`, `DELETE`, primary key.
- **Milestone:** You can explain what happens when a client sends an HTTP request to a server.

---

## 3. Phase 1 — Spring vs Spring Boot
- Learn what Spring Framework is and why manual configuration can become tedious.
- Learn what Spring Boot adds: auto-configuration, starters, embedded server, convention over configuration.
- Learn Spring Boot application startup at a high level.
- Learn Maven: `pom.xml`, dependency, plugin, lifecycle, clean, package.
- Understand the generated project and where Java/resources live.
- Learn `@SpringBootApplication`: configuration + component scanning + auto-configuration.
- **Milestone:** You can start the app and explain why it runs without manually configuring Tomcat.

---

## 4. Phase 2 — Project Setup
- Create FocusTask with Maven + Java.
- Add Spring Web, Spring Data JPA, and MySQL Driver.
- Understand port configuration and basic application settings.
- Run the application and read startup logs.
- Create packages: `controller`, `service`, `repository`, `entity`, `exception`.
- Keep the main application class at the root package so component scanning finds subpackages.
- **Milestone:** Clean application startup with no errors.

---

## 5. Phase 3 — IoC, Beans and Dependency Injection
- Understand IoC: Spring controls object creation/lifecycle instead of manual wiring everywhere.
- Understand Bean: an object managed by Spring.
- Understand ApplicationContext at a high level as the Spring container/context.
- Learn component scanning.
- Learn `@Component`, `@Service`, `@Repository` and why stereotype annotations communicate roles.
- Learn constructor injection. Understand `@Autowired`, but prefer constructor injection.
- Build a temporary `HelloController` → `HelloService` flow, then remove it.
- **Milestone:** You can explain why `TaskController` does not need `new TaskService()`.

---

## 6. Phase 4 — REST + Spring MVC
- Understand REST as resource-oriented HTTP communication.
- Learn endpoint, resource, request, response, JSON.
- Learn GET, POST, PUT, DELETE and map them to CRUD.
- Learn `@RestController` and `@RequestMapping`.
- Learn `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`.
- Learn `@RequestBody` for JSON → Java object.
- Learn `@PathVariable` for values inside the URL.
- Learn `@RequestParam` as basic awareness for future filtering.
- Learn HTTP status codes: 200, 201, 204, 400, 404, 500.
- Understand `ResponseEntity` at a basic practical level.
- **Milestone:** You can make a simple endpoint return JSON.

---

## 7. Phase 5 — Task Entity + ORM
- Create `Task` with id, title, description, completed.
- Learn ORM: mapping Java objects to relational database tables.
- Learn JPA as a persistence specification/API and Hibernate as a common implementation.
- Use `@Entity`, `@Id`, `@GeneratedValue`.
- Understand primary key and generated ID.
- Understand basic Java field ↔ database column mapping.
- Create constructors/getters/setters as needed.
- Do not add relationships, DTOs, Lombok, or advanced mappings yet.
- **Milestone:** You can explain how a Task Java object becomes a database row.

---

## 8. Phase 6 — MySQL + JPA Configuration
- Install/start MySQL and create a database/schema for FocusTask.
- Understand database URL, username, password, JDBC driver.
- Configure datasource in `application.properties`.
- Learn what JPA/Hibernate properties control at a basic level.
- Understand schema generation/update is convenient for learning but should be used thoughtfully in production.
- Verify that the application can connect to MySQL.
- Read logs and identify database connection errors.
- **Milestone:** Application starts with MySQL connected.

---

## 9. Phase 7 — Repository + Spring Data JPA
- Create `TaskRepository` extending `JpaRepository`.
- Understand repository abstraction: database access without writing every CRUD query.
- Learn `save()`, `findAll()`, `findById()`, `deleteById()`.
- Understand `Optional` from `findById()`.
- Know `existsById()` as useful awareness.
- Understand derived query methods as a future extension, but do not add one yet.
- **Milestone:** Task data can be persisted/retrieved through the repository.

---

## 10. Phase 8 — Service Layer
- Create `TaskService` with constructor-injected `TaskRepository`.
- Implement `createTask`, `getAllTasks`, `getTaskById`, `updateTask`, `deleteTask`.
- Keep business/application logic here rather than in the controller.
- For get/update/delete, decide what happens when an ID does not exist.
- Understand why Controller → Service → Repository is useful: separation of concerns and maintainability.
- **Milestone:** Service contains the application flow and controller stays thin.

---

## 11. Phase 9 — Controller + Complete CRUD
- Create `TaskController` with `@RestController` and `@RequestMapping("/api/tasks")`.
- `POST /api/tasks` → create.
- `GET /api/tasks` → list.
- `GET /api/tasks/{id}` → single task.
- `PUT /api/tasks/{id}` → update.
- `DELETE /api/tasks/{id}` → delete.
- Use `@RequestBody` for POST/PUT and `@PathVariable` for IDs.
- Return sensible HTTP statuses: 201 for creation, 404 for missing task, and 204 for successful deletion are good defaults.
- Test each endpoint before moving on.
- **Milestone:** Five endpoints work end-to-end.

---

## 12. Phase 10 — Exception Handling
- Create `TaskNotFoundException` extending `RuntimeException`.
- Throw it when a requested task does not exist.
- Learn `@ExceptionHandler` for mapping an exception to an HTTP response.
- Learn `@RestControllerAdvice` for centralized exception handling.
- Return a clean JSON error message and 404 status.
- Know the difference between local controller handling and global handling.
- **Milestone:** Missing IDs produce a controlled 404 response.

---

## 13. Phase 11 — Validation
- Understand why input validation protects API quality.
- Add `@Valid` to request body handling.
- Use `@NotBlank` on title.
- Optionally use `@Size` for title/description limits.
- Understand invalid input should produce 400 Bad Request.
- Keep validation minimal; do not build a complicated validation framework.
- **Milestone:** Empty/invalid title is rejected cleanly.

---

## 14. Phase 12 — Postman Testing
- Create a FocusTask Postman collection.
- Test POST with valid JSON.
- Test GET all.
- Test GET by ID.
- Test PUT.
- Test DELETE.
- Test nonexistent ID.
- Test invalid input.
- Check status code, response body, and database state after each request.
- Keep example request/response JSON for the README.
- **Milestone:** You can demo the complete API without opening the source code.

---

## 15. Phase 13 — Tiny Frontend
- Use plain HTML/CSS/JavaScript to keep scope minimal.
- Create one page with task list, add-task form, completion control, and delete button.
- Use `fetch()` for GET/POST/PUT/DELETE.
- Parse JSON responses.
- Render tasks dynamically into the DOM.
- Handle loading/error states simply.
- Learn CORS at a basic level because frontend and backend may use different origins/ports.
- Do not add React unless you specifically want React practice; it is not required to prove Spring Boot.
- **Milestone:** Browser UI can create, read, update, and delete tasks through the Spring Boot API.

---

## 16. Phase 14 — Build, Debug and Runtime
- Learn `mvn clean`, `mvn package` and running the generated JAR.
- Understand embedded server and application port.
- Read Spring startup logs.
- Know where to look for dependency, port, database, SQL, JSON and 404 errors.
- Practice debugging one request from browser/Postman → controller → service → repository → database → response.
- **Milestone:** You can run the project from a clean machine after following your README.

---

## 17. Phase 15 — GitHub + README
- Create `.gitignore` for Java/Maven/IDE files.
- Commit meaningful changes.
- Push the project to GitHub.
- README: Overview, Features, Tech Stack, Architecture, Setup, Database Configuration, API Endpoints, Example JSON, Screenshots, Future Improvements.
- Add a simple architecture diagram.
- Add Postman screenshots and frontend screenshot.
- Never commit database passwords/API keys.
- **Milestone:** Recruiter can understand and run the project from GitHub.

---

## 18. Architecture You Must Be Able to Explain
- **Controller** — receives HTTP requests, extracts request data, calls the service, and returns HTTP responses.
- **Service** — contains application/business logic and coordinates operations.
- **Repository** — persistence/data-access layer using Spring Data JPA.
- **Entity** — Java object mapped to a database table.
- **Database** — durable storage.

### Request Flows
- **POST flow:**
  `POST /api/tasks` → `TaskController` → `TaskService` → `TaskRepository` → JPA/Hibernate → MySQL → JSON response
- **GET flow:**
  `GET /api/tasks/1` → `Controller` → `Service` → `Repository` → MySQL → Entity → JSON response
- **Exception flow:**
  Missing task → `TaskNotFoundException` → `@RestControllerAdvice` / `@ExceptionHandler` → HTTP 404 + JSON error

---

## 19. Final API Contract

| Method | Endpoint | Purpose | Typical Success |
| :--- | :--- | :--- | :--- |
| **POST** | `/api/tasks` | Create task | `201 Created` |
| **GET** | `/api/tasks` | Get all tasks | `200 OK` |
| **GET** | `/api/tasks/{id}` | Get one task | `200 OK` / `404` |
| **PUT** | `/api/tasks/{id}` | Update task | `200 OK` / `404` |
| **DELETE** | `/api/tasks/{id}` | Delete task | `204 No Content` / `404` |

**Example JSON:**
```json
{"title":"Learn Spring Boot","description":"Build FocusTask API","completed":false}
```

**Example error:**
```json
{"message":"Task not found with id: 99"}
```

---

## 20. Core Definitions
- **Spring Boot:** Framework built on Spring that simplifies application setup using auto-configuration, starters, embedded servers, and conventions.
- **IoC:** Inversion of Control: object creation and management are handled by the framework/container instead of being manually wired everywhere.
- **Dependency Injection:** A dependency is supplied to a class rather than the class constructing that dependency itself.
- **Bean:** An object managed by the Spring container.
- **ApplicationContext:** A Spring container/context that creates, configures, stores, and provides beans.
- **REST API:** An HTTP-based interface exposing resources and operations through endpoints and standard HTTP methods.
- **Controller:** Web/API layer that handles incoming HTTP requests and produces responses.
- **Service:** Application layer that contains business/application logic.
- **Repository:** Persistence layer responsible for data access.
- **JPA:** Java Persistence API/specification for mapping and persisting Java objects to relational databases.
- **Hibernate:** A widely used ORM implementation of JPA.
- **ORM:** Object-Relational Mapping: mapping objects/classes to relational tables/rows.
- **JpaRepository:** Spring Data interface providing common CRUD persistence operations.
- **Entity:** A persistent Java object mapped to a database table.
- **@RequestBody:** Binds request body data, commonly JSON, to a Java object.
- **@PathVariable:** Binds a value embedded in the URL path to a method parameter.
- **@RestControllerAdvice:** Centralized exception-handling mechanism for REST controllers.
- **CORS:** Browser security mechanism controlling cross-origin HTTP requests; relevant when frontend/backend origins differ.

---

## 21. What NOT to Add Yet
- Spring Security, JWT, OAuth2
- Microservices, Spring Cloud
- Kafka, Redis
- WebFlux / reactive programming
- Kubernetes
- Docker specifically for this learning project
- Advanced Hibernate relationships and performance tuning
- Complex DTO/mapping frameworks
- Caching, event-driven architecture, distributed tracing
- Complex frontend frameworks unless separately required

*Reason:* These can hide the core Spring Boot concepts you are trying to learn. Add them only when a job/project requirement justifies them.

---

## 22. Definition of Done
- Application starts successfully.
- MySQL connection works.
- Task entity is mapped correctly.
- Repository performs CRUD.
- Service owns application logic.
- Controller exposes five REST endpoints.
- JSON request/response works.
- Missing task returns controlled 404.
- Validation rejects invalid task input.
- Postman collection proves every endpoint.
- Tiny browser frontend calls the API successfully.
- GitHub repository contains clean source code and README.
- You can explain the complete request lifecycle without reading notes.

---

## 23. Final Interview Drill Questions
1. What is Spring Boot and why use it?
2. What does `@SpringBootApplication` do?
3. What is IoC?
4. What is Dependency Injection?
5. Why is constructor injection preferred?
6. What is a Spring Bean?
7. Difference between `@Component`, `@Service` and `@Repository`?
8. What is REST?
9. Difference between GET, POST, PUT and DELETE?
10. What does `@RequestBody` do?
11. What does `@PathVariable` do?
12. What is JPA?
13. What is Hibernate?
14. What is ORM?
15. Why does `JpaRepository` provide CRUD methods?
16. What is `Optional` and why does `findById` return it?
17. Why should business logic not live in the controller?
18. Why use Controller → Service → Repository?
19. What happens when a task ID does not exist?
20. What does `@RestControllerAdvice` do?
21. What are HTTP 200, 201, 204, 400, 404 and 500?
22. What is CORS and why might the frontend trigger it?
23. How does JSON travel from the browser to MySQL and back?
24. How do you run the project with Maven?
25. How would you extend this project if asked to add authentication or pagination?

---

## 24. Recommended Working Method
For every concept:
- **WHY** — What problem does this concept solve?
- **WHAT** — What exactly is it?
- **HOW** — How does Spring use it?
- **CODE** — Implement the smallest possible example.
- **TEST** — Prove it works.
- **EXPLAIN** — Explain it in your own words without notes.

**Golden rule:** Do not move to the next phase until the current phase's milestone works. If an error appears, understand the error before copying a fix.

*Final project size:* roughly 6–10 backend Java files plus a very small frontend. The goal is depth of understanding, not feature count.
