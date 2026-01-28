# FF Server

A Spring Boot REST API server for managing Fighting Fantasy RPG characters, equipment, potions, and game progress.

## 📋 Description

FF Server is a backend application built with Spring Boot that provides RESTful APIs for creating and managing characters in Fighting Fantasy role-playing games. The server handles character creation and retrieval, manages per-character equipment and potions, and tracks game progress through various books and sections, exposing a clean DTO-based API that’s consumed by the React frontend.

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot 4.0.0**
- **Spring Data JPA** - Database persistence
- **Spring Security** - Security configuration
- **MySQL** - Database
- **Gradle** - Build tool
- **JSON** - Data serialization

## 📁 Project Structure

```
ff-server/
├── src/
│   ├── main/
│   │   ├── java/com/shaunbag/ff_server/
│   │   │   ├── configs/
│   │   │   │   └── SecurityConfig.java         # Security and CORS configuration
│   │   │   ├── controller/
│   │   │   │   ├── CharacterController.java        # Character CRUD endpoints
│   │   │   │   ├── EquipmentController.java        # Equipment CRUD endpoints
│   │   │   │   ├── PotionController.java           # Potion CRUD endpoints
│   │   │   │   ├── ProgressController.java         # Progress CRUD endpoints
│   │   │   │   └── GlobalExceptionHandler.java     # Global exception handling
│   │   │   ├── model/
│   │   │   │   ├── Character.java                  # Character entity
│   │   │   │   ├── Equipment.java                  # Equipment entity
│   │   │   │   ├── Potion.java                     # Potion entity
│   │   │   │   ├── Progress.java                   # Progress tracking entity
│   │   │   │   └── dto/
│   │   │   │       ├── CharacterCreateDto.java
│   │   │   │       ├── CharacterResponseDto.java
│   │   │   │       ├── EquipmentDto.java
│   │   │   │       ├── PotionDto.java
│   │   │   │       └── ProgressDto.java
│   │   │   ├── repository/
│   │   │   │   ├── CharacterRepository.java
│   │   │   │   ├── EquipmentRepository.java
│   │   │   │   ├── PotionRepository.java
│   │   │   │   └── ProgressRepository.java
│   │   │   └── services/
│   │   │       ├── CharacterService.java           # Character business logic
│   │   │       ├── EquipmentService.java           # Equipment business logic
│   │   │       ├── PotionService.java              # Potion business logic
│   │   │       └── ProgressService.java            # Progress business logic
│   │   └── resources/
│   │       ├── application.properties           # Application configuration
│   │       └── datasource.properties           # Database configuration
│   └── test/
│       └── java/com/shaunbag/ff_server/
│           └── services/
│               ├── CharacterServiceTest.java    # Character service tests
│               └── ProgressServiceTest.java     # Progress service tests
└── build.gradle
```

## 🚀 Getting Started

### Prerequisites

- Java 17 or higher
- MySQL 8.0 or higher
- Gradle (or use the included Gradle wrapper)

### Installation

1. **Clone the repository**
   ```bash
   git clone <your-repo-url>
   cd ff-server
   ```

2. **Set up MySQL database**
   - Create a MySQL database named `ffdb`
   - Update database credentials in `src/main/resources/datasource.properties`:
     ```properties
     datasource.user=your_username
     datasource.password=your_password
     datasource.host=localhost
     datasource.name=ffdb
     ```

3. **Build the project**
   ```bash
   ./gradlew build
   ```
   On Windows:
   ```bash
   gradlew.bat build
   ```

4. **Run the application**
   ```bash
   ./gradlew bootRun
   ```
   On Windows:
   ```bash
   gradlew.bat bootRun
   ```

The server will start on `http://localhost:8080` by default.

## 📡 API Endpoints

### Base URL
All endpoints are prefixed with:

```
http://localhost:8080/api
```

### Characters

#### Get All Characters
```http
GET /api/character
```

#### Create Character
```http
POST /api/character
Content-Type: application/json
```

**Request Body:**
```json
{
  "name": "Hero",
  "skill": 10,
  "luck": 8,
  "stamina": 20,
  "gold": 15
}
```

#### Get Character by ID
```http
GET /api/character/{id}
```

#### Update Character
```http
POST /api/character/{id}
Content-Type: application/json
```

#### Delete Character
```http
DELETE /api/character/{id}
```

On missing resources, the server returns a `404` with a descriptive message via the global exception handler.

### Equipment

Equipment items are stored per character and exposed via DTOs.

#### Get Equipment for Character
```http
GET /api/equipment/{characterId}
```

#### Create Equipment
```http
POST /api/equipment
Content-Type: application/json
```

**Request Body:**
```json
{
  "name": "Sword",
  "effect": "+1 Skill",
  "characterId": 1
}
```

#### Delete Equipment
```http
DELETE /api/equipment/{id}
```

### Potions

Potions are also stored per character and exposed via DTOs.

#### Get Potions for Character
```http
GET /api/potions/{characterId}
```

#### Create Potion
```http
POST /api/potions
Content-Type: application/json
```

**Request Body:**
```json
{
  "name": "Stamina Potion",
  "effect": "Restore 4 Stamina",
  "characterId": 1
}
```

#### Delete Potion
```http
DELETE /api/potions/{id}
```

### Progress

Progress records track which book and section a character is currently on. Multiple records per character are supported.

#### Get Progress for Character
```http
GET /api/progress/{characterId}
```

#### Create Progress Record
```http
POST /api/progress
Content-Type: application/json
```

**Request Body:**
```json
{
  "book": "City of Thieves",
  "section": 123,
  "characterId": 1
}
```

#### Update Progress Record
```http
POST /api/progress/{id}
Content-Type: application/json
```

#### Delete Progress Record
```http
DELETE /api/progress/{id}
```

## 🎮 Character Model

Characters have the following attributes:

- **id** (Long) - Auto-generated unique identifier
- **name** (String) - Character name
- **skill** (Integer) - Character skill level
- **luck** (Integer) - Character luck level
- **stamina** (Integer) - Character stamina/health
- **gold** (Integer) - Character's gold coins

### Character Operations

The API supports full CRUD operations via `CharacterController` and returns DTOs (`CharacterResponseDto`) to the client. All operations return appropriate HTTP status codes and error messages when resources are not found.

## 🔒 Security

The application uses Spring Security with:
- CORS enabled for `http://localhost:5173` (frontend development)
- CSRF disabled for API endpoints
- Stateless session management
- Currently configured to permit all requests (can be customized for production)

## 🗄️ Database

The application uses MySQL with JPA/Hibernate:
- Tables are auto-created/updated via `spring.jpa.hibernate.ddl-auto=update`
- Character data is stored in the `characters` table
- Equipment data is stored in the `equipment` table (linked to characters)
- Potion data is stored in the `potion` table (linked to characters)
- Progress tracking is stored in the `Progress` table (linked to characters)

## 🧪 Testing

The project includes comprehensive unit tests for service layers using JUnit 5 and Mockito.

### Running Tests

Run all tests:
```bash
./gradlew test
```

Run specific test classes:
```bash
./gradlew test --tests "CharacterServiceTest"
./gradlew test --tests "ProgressServiceTest"
```

### Test Coverage

**CharacterServiceTest** covers:
- ✅ Getting all characters as DTOs
- ✅ Getting character by ID
- ✅ Creating new characters
- ✅ Updating existing characters
- ✅ Deleting characters
- ✅ DTO conversion with various data scenarios

**ProgressServiceTest** covers:
- ✅ Getting all progress records
- ✅ Getting progress by player ID
- ✅ Handling empty results
- ✅ Multiple progress records scenarios

## 📝 Configuration

### Application Properties
- Application name: `ff-server`
- Database configuration imported from `datasource.properties`
- Hibernate DDL auto-update enabled

### CORS Configuration
Currently configured to allow requests from `http://localhost:5173`. Update `SecurityConfig.java` to modify allowed origins.

### Exception Handling
The application includes a `GlobalExceptionHandler` that:
- Handles `EntityNotFoundException` and returns HTTP 404 status
- Provides meaningful error messages for missing resources
- Ensures consistent error responses across all endpoints


## 👤 Author

**shaunbag**

---

Built with ❤️ using Spring Boot
