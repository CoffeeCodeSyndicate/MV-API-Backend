# 🐾 Snuggle Squad – Pet Adoption API

Snuggle Squad is a **Spring Boot 3.5** backend project that simulates a **pet adoption platform**.  
It demonstrates **authentication, authorization, CRUD APIs, and adoption workflows** with a clean layered architecture.  
Perfect for learning Spring Boot + JPA + Security, or for use as a starter template for pet adoption apps! 🐶🐱🐾

---

## ✨ Features

- 👥 **User Management** – register, login, role-based access (Admin / Registered / Visitor)  
- 🐾 **Pet Management** – create, view, update, delete pets  
- 📄 **Adoption Applications** – apply for pets, track status (`PENDING`, `APPROVED`, `REJECTED`)  
- 🔐 **Spring Security** – BCrypt password encoding, role-based restrictions  
- 🗄️ **Database** – H2 in-memory DB with seed data for quick start  
- 🐳 **Dockerized** – simple Dockerfile for containerized deployments  
- 🧪 **Testing** – JUnit5 test scaffolding included  

---

## 🏗️ Tech Stack

- ☕ Java 21  
- 🚀 Spring Boot 3.5  
- 🛡️ Spring Security (BCrypt)  
- 📦 Spring Data JPA  
- 🐘 H2 Database (in-memory)  
- 🐳 Docker, Maven Wrapper  

---

## 📦 Getting Started

### 1. Clone the Repo
```bash
git clone https://github.com/your-username/snuggle-squad.git
cd snuggle-squad
````

### 2. Run with Maven

```bash
./mvnw spring-boot:run
```

### 3. Run with Docker

```bash
docker build -t snuggle-squad .
docker run -p 8080:8080 snuggle-squad
```

---

## 🔑 API Endpoints

### 👥 Public

* `POST /register` – register new user
* `POST /login` – login (BCrypt validation)
* `GET /pets` – view all pets
* `GET /pets/{id}` – view pet by ID

### 🐾 Registered Users

* `POST /registered/applications` – create adoption application
* `GET /registered/pets` – list pets (same as public)

### 👩‍💼 Admin

* `POST /admin` – create pet
* `PUT /admin/{id}` – update pet
* `DELETE /admin/{id}` – delete pet
* `GET /admin/users` – list users
* `GET /admin/applications` – list applications

## 🐶🐱 About

This project is a **learning demo** for mastering Spring Boot APIs,
but it can also serve as the foundation of a real-world **pet adoption system**.
Because every buddy deserves a forever home! 🐾❤️



Do you want me to also include a **cute ASCII art paw 🐾 banner** at the very top (for extra GitHub flair), or keep it simple and professional?
```
