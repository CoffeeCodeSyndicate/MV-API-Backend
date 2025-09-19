# MV-API

A Spring Boot 3.5.5 application that provides **user management and authentication using JWT**.  
Includes registration, login, and secured endpoints, with support for role-based access.

---

## 🚀 Features
- User registration & login  
- Password hashing with **BCrypt**  
- JWT access & refresh tokens  
- Global exception handling  
- H2 database for dev/test  
- CORS configuration for frontend (React/Vue/Angular)  
- Actuator endpoints for health checks  

---

## 📦 Tech Stack
- **Java 21**  
- **Spring Boot 3.5.5**
  - Web  
  - Data JPA  
  - Security  
  - Validation  
  - Actuator  
- **JJWT 0.11.5**  
- **H2 Database** (dev/test)  

---

## ⚙️ Getting Started

### Prerequisites
- Java 21  
- Maven 3.9+  
- VS Code or IntelliJ IDEA  

### Build & Run
```bash
./mvnw clean install
./mvnw spring-boot:run
App will be available at 👉 http://localhost:8080

🔑 Authentication Flow
1. Register a User
http
Copy code
POST /api/auth/register
Content-Type: application/json

{
  "email": "user@example.com",
  "username": "testuser",
  "password": "password123"
}
2. Login
http
Copy code
POST /api/auth/login
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "password123"
}
✔️ Returns a JWT token.

3. Call Protected Endpoint
http
Copy code
GET /api/users/me
Authorization: Bearer <your_jwt_token>
⚙️ Configuration
Edit src/main/resources/application.properties:

properties
Copy code
spring.application.name=MV-API

# JWT
security.jwt.secret=mysupersecretkeymysupersecretkey123!
security.jwt.expMinutes=60
security.jwt.refreshDays=14

# CORS
app.cors.allowed-origins=http://localhost:5173,http://localhost:3000
🐳 Docker
Build & run in Docker:

bash
Copy code
docker build -t mv-api .
docker run -p 8080:8080 mv-api
📂 Project Structure
graphql
Copy code
src/main/java/com/coffeecodesyndicate/api
 ├── config/            # Security, JWT, exception handling
 ├── controllers/       # REST controllers (Auth, User, Admin, etc.)
 ├── dto/               # DTOs for requests & responses
 ├── models/            # Entities (User, Application, Pet, etc.)
 ├── repositories/      # Spring Data JPA repositories
 ├── services/          # Business logic
 └── MvApiApplication.java  # Main entry point
🧪 Testing
bash
Copy code
./mvnw test
