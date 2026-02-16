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
│   │   │   │   ├── AppController.java              # App routing to frontend
│   │   │   │   ├── CharacterController.java        # Character CRUD endpoints
│   │   │   │   ├── EquipmentController.java        # Equipment CRUD endpoints
│   │   │   │   ├── GlobalExceptionHandler.java     # Global exception handling
│   │   │   │   ├── PotionController.java           # Potion CRUD endpoints
│   │   │   │   ├── ProgressController.java         # Progress CRUD endpoints
│   │   │   │   ├── TreasureController.java         # Treasure CRUD endpoints
│   │   │   │   └── UserController.java             # User registration endpoint
│   │   │   ├── model/
│   │   │   │   ├── Character.java                  # Character entity
│   │   │   │   ├── Equipment.java                  # Equipment entity
│   │   │   │   ├── MyUser.java                     # User entity for authentication
│   │   │   │   ├── Potion.java                     # Potion entity
│   │   │   │   ├── Progress.java                   # Progress tracking entity
│   │   │   │   ├── Treasure.java                   # Treasure entity
│   │   │   │   └── dto/
│   │   │   │       ├── CharacterCreateDto.java
│   │   │   │       ├── CharacterResponseDto.java
│   │   │   │       ├── EquipmentDto.java
│   │   │   │       ├── MyUserDto.java
│   │   │   │       ├── PotionDto.java
│   │   │   │       ├── ProgressDto.java
│   │   │   │       └── TreasureDto.java
│   │   │   ├── repository/
│   │   │   │   ├── CharacterRepository.java
│   │   │   │   ├── EquipmentRepository.java
│   │   │   │   ├── PotionRepository.java
│   │   │   │   ├── ProgressRepository.java
│   │   │   │   ├── TreasureRepository.java
│   │   │   │   └── UserRepository.java
│   │   │   └── services/
│   │   │       ├── CharacterService.java           # Character business logic
│   │   │       ├── EquipmentService.java           # Equipment business logic
│   │   │       ├── MyUserDetailsService.java       # User authentication service
│   │   │       ├── MyUserService.java              # User business logic
│   │   │       ├── PotionService.java              # Potion business logic
│   │   │       ├── ProgressService.java            # Progress business logic
│   │   │       └── TreasureService.java            # Treasure business logic
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
│               ├── ProgressServiceTest.java       # Progress service tests
│               └── TreasureServiceTest.java       # Treasure service tests
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

### Treasure

Treasure items are stored per character and track valuable finds.

#### Get Treasure for Character
```http
GET /api/treasure/{characterId}
```

#### Create Treasure
```http
POST /api/treasure
Content-Type: application/json
```

**Request Body:**
```json
{
  "name": "Gold Coin",
  "value": 100,
  "characterId": 1
}
```

#### Update Treasure
```http
POST /api/treasure/{id}
Content-Type: application/json
```

**Request Body:**
```json
{
  "name": "Gold Coin",
  "value": 150,
  "characterId": 1
}
```

#### Delete Treasure
```http
DELETE /api/treasure/{id}
```

### Users

User management endpoints for registration and authentication support.

#### Create User
```http
POST /api/user
Content-Type: application/json
```

**Request Body:**
```json
{
  "username": "player1",
  "password": "secretpassword",
  "role": "USER"
}
```

**Response:**
Returns the created user ID (Long)

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

The application uses Spring Security with form-based authentication and role-based access control:

**Authentication & Authorization:**
- Form-based login at `/login` endpoint
- BCrypt password encoding for secure credential storage
- User details loaded from `MyUserDetailsService` (database-backed)
- Role-based access control: `USER` role required for `/app/**` and `/api/**` endpoints
- Session-based authentication (default Spring Security session management)

**CORS & CSRF Protection:**
- CORS enabled for `http://localhost:5173` (frontend development)
- CSRF disabled for API endpoints
- Allowed methods: GET, POST, PUT, DELETE, OPTIONS
- Credentials allowed in cross-origin requests

**Public & Protected Routes:**
- Public routes: `/app`, `/app/index.html`, `/app/static/**`, `/app/js/**`, `/app/css/**`, `/app/assets/**`, `/api/**` (registration endpoint)
- Protected routes: `/app/**` and `/api/**` endpoints require `USER` role
- Default redirect after successful login: `/app`

## 🗄️ Database

The application uses MySQL with JPA/Hibernate:
- Tables are auto-created/updated via `spring.jpa.hibernate.ddl-auto=update`
- Character data is stored in the `characters` table
- Equipment data is stored in the `equipment` table (linked to characters)
- Potion data is stored in the `potion` table (linked to characters)
- Progress tracking is stored in the `Progress` table (linked to characters)
- Treasure data is stored in the `treasure` table (linked to characters)
- User data is stored in the `users` table for authentication and user management

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

**TreasureServiceTest** covers:
- ✅ Mapping `Treasure` entities to `TreasureDto`
- ✅ Fetching treasure by character ID
- ✅ Saving treasure records
- ✅ Updating treasure records
- ✅ Deleting treasure records

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
