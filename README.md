# FF Server

A Spring Boot REST API server for managing Fighting Fantasy RPG characters and game progress.

## 📋 Description

FF Server is a backend application built with Spring Boot that provides RESTful APIs for creating and managing characters in Fighting Fantasy role-playing games. The server handles character creation, retrieval, and tracks game progress through various books and sections.

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
│   │   │   │   ├── controller.java             # REST API endpoints
│   │   │   │   └── GlobalExceptionHandler.java # Global exception handling
│   │   │   ├── model/
│   │   │   │   ├── Character.java              # Character entity
│   │   │   │   ├── Progress.java               # Progress tracking entity
│   │   │   │   └── dto/
│   │   │   │       ├── CharacterCreateDto.java
│   │   │   │       └── CharacterResponseDto.java
│   │   │   ├── repository/
│   │   │   │   ├── CharacterRepository.java
│   │   │   │   └── ProgressRepository.java
│   │   │   └── services/
│   │   │       ├── CharacterService.java       # Character business logic
│   │   │       └── ProgressService.java        # Progress business logic
│   │   └── resources/
│   │       ├── application.properties           # Application configuration
│   │       └── datasource.properties           # Database configuration
│   └── test/
│       └── java/com/shaunbag/ff_server/
│           ├── FfServerApplicationTests.java      # Spring Boot context & smoke tests
│           └── services/
│               ├── CharacterServiceTest.java      # Character service tests
│               ├── EquipmentServiceTest.java      # Equipment service tests
│               ├── PotionServiceTest.java         # Potion service tests
│               └── ProgressServiceTest.java       # Progress service tests
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
```
http://localhost:8080/api
```

### Get All Characters
```http
GET /api/all
```

**Response:**
```json
[
  {
    "id": 1,
    "name": "Hero",
    "skill": 10,
    "luck": 8,
    "stamina": 20,
    "gold": 15
  }
]
```

### Create Character
```http
POST /api/createcharacter
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

**Response:**
```json
{
  "id": 1,
  "name": "Hero",
  "skill": 10,
  "luck": 8,
  "stamina": 20,
  "gold": 15
}
```

### Get Character by ID
```http
GET /api/character/{id}
```

**Response:**
```json
{
  "id": 1,
  "name": "Hero",
  "skill": 10,
  "luck": 8,
  "stamina": 20,
  "gold": 15
}
```

**Error Response (404):**
If character is not found, returns:
```json
"Character Not Found With Id: {id}"
```

### Update Character
```http
POST /api/character/{id}
Content-Type: application/json
```

**Request Body:**
```json
{
  "name": "UpdatedHero",
  "skill": 12,
  "luck": 9,
  "stamina": 22,
  "gold": 25
}
```

**Response:**
```json
{
  "id": 1,
  "name": "UpdatedHero",
  "skill": 12,
  "luck": 9,
  "stamina": 22,
  "gold": 25
}
```

**Error Response (404):**
If character is not found, returns:
```json
"No Character Found With Id {id}"
```

### Delete Character
```http
DELETE /api/character/{id}
```

**Response:**
- **204 No Content** - Character successfully deleted

**Error Response (404):**
If character is not found, returns:
```json
"Character Not Found With Id: {id}"
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

The API supports full CRUD operations:
- **Create** - `POST /api/createcharacter`
- **Read** - `GET /api/all` and `GET /api/character/{id}`
- **Update** - `POST /api/character/{id}`
- **Delete** - `DELETE /api/character/{id}`

All operations return appropriate HTTP status codes and error messages when resources are not found.

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
- Progress tracking is stored in the `Progress` table (linked to characters)

## 🧪 Testing

The project includes comprehensive unit tests for service layers using JUnit 5 and Mockito.

### Running Tests

Run all tests:
```bash
./gradlew test
```

Run specific test classes (examples):
```bash
./gradlew test --tests "CharacterServiceTest"
./gradlew test --tests "EquipmentServiceTest"
./gradlew test --tests "PotionServiceTest"
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

**EquipmentServiceTest** covers:
- ✅ Mapping `Equipment` entities to `EquipmentDto`
- ✅ Fetching equipment lists by character ID
- ✅ Persisting and deleting equipment records

**PotionServiceTest** covers:
- ✅ Mapping `Potion` entities to `PotionDto`
- ✅ Fetching potions by character ID
- ✅ Persisting and deleting potions

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
