# Vocab API

A Spring Boot application for managing Japanese-Chinese vocabulary, structured using Domain-Driven Design (DDD) principles.


## Features


### 1. Add Vocabulary
  - Support input of Japanese kana/kanji
  - Chinese meaning
  - Part of speech (verb, noun, adjective, etc.)
  - Extra notes (example sentences, associations, etc.)

### 2. List Saved Vocabulary (Vocabulary Bank)
  - Sort by creation time
  - Show brief info (part of speech, meaning)
  - Click to view details

### 3. Search Function
  - Keyword search (supports Chinese or Japanese)
  - Fuzzy search, prefix/suffix matching
  - Can search content in example sentences or notes

### 4. Edit/Delete Vocabulary

---

### 💡【Extended Features: Advanced Enhancements & Interactivity】
(Consider adding these after MVP is complete)

1. Vocabulary Quiz Mode
    - Chinese-to-Japanese / Japanese-to-Chinese
    - Flashcard-style mode
    - Quiz result records
2. Vocabulary Tagging & Classification
    - Users can define "topic categories" or "proficiency levels"
    - Examples: JLPT N2, business, travel, situational phrases
3. Review Reminder Function (Spaced Repetition Algorithm)
    - Schedule reviews based on user quiz results
4. Import/Export Function
    - Support for CSV files
    - Backup and share vocabulary bank
5. Pronunciation Support
    - Use Text-to-Speech or external APIs
6. Cross-Device Sync (Cloud Backup)
    - Option to log in and automatically sync vocabulary data

---

#### Techniques
- Layered architecture following DDD: domain, application, infrastructure, and web (presentation)
- RESTful API endpoints
- Input validation using Bean Validation (Jakarta Validation)
- In-memory H2 database for development and testing

## Project Structure
```
src/main/java/com/example/vocab_api/
  ├── domain/         # Domain layer (entities, repositories, domain services)
  ├── application/    # Application layer (DTOs, application services)
  ├── infrastructure/ # Infrastructure layer (repository implementations, configs)
  ├── web/            # Web layer (controllers, web DTOs, mappers)
```

## API Endpoints
### Add Vocabulary
- **POST** `/api/vocabulary/add`
- **Request Body (JSON):**
  ```json
  {
    "jp": "こんにちは",
    "zh": "你好"
  }
  ```
- **Response:**
  - `true` if saved successfully
  - `false` if validation or save failed

## Getting Started
### Prerequisites
- Java 17 or later
- Maven

### Run the Application
```sh
./mvnw spring-boot:run
```
Or on Windows:
```sh
mvnw.cmd spring-boot:run
```

The API will be available at [http://localhost:8081/api/vocabulary](http://localhost:8081/api/vocabulary)

### H2 Database Console
- Access at [http://localhost:8081/h2-console](http://localhost:8081/h2-console)
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: *(leave blank)*

## Validation
- Uses `@NotBlank` on request DTO fields to ensure required parameters.
- Invalid requests return HTTP 400 with validation error details.

## License
MIT
