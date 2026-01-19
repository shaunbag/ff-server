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
│   │   │   │   └── SecurityConfig.java      # Security and CORS configuration
│   │   │   ├── controller/
│   │   │   │   └── controller.java          # REST API endpoints
│   │   │   ├── model/
│   │   │   │   ├── Character.java           # Character entity
│   │   │   │   ├── Progress.java            # Progress tracking entity
│   │   │   │   └── dto/
│   │   │   │       ├── CharacterCreateDto.java
│   │   │   │       └── CharacterResponseDto.java
│   │   │   ├── repository/
│   │   │   │   ├── CharacterRepository.java
│   │   │   │   └── ProgressRepository.java
│   │   │   └── services/
│   │   │       ├── CharacterService.java
│   │   │       └── ProgressService.java
│   │   └── resources/
│   │       ├── application.properties        # Application configuration
│   │       └── datasource.properties        # Database configuration
│   └── test/
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

## 🎮 Character Model

Characters have the following attributes:

- **id** (Long) - Auto-generated unique identifier
- **name** (String) - Character name
- **skill** (Integer) - Character skill level
- **luck** (Integer) - Character luck level
- **stamina** (Integer) - Character stamina/health
- **gold** (Integer) - Character's gold coins

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

Run tests using:
```bash
./gradlew test
```

## 📝 Configuration

### Application Properties
- Application name: `ff-server`
- Database configuration imported from `datasource.properties`
- Hibernate DDL auto-update enabled

### CORS Configuration
Currently configured to allow requests from `http://localhost:5173`. Update `SecurityConfig.java` to modify allowed origins.


## 👤 Author

**shaunbag**

---

Built with ❤️ using Spring Boot
