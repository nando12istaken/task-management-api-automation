# task-management-api-automation
REST Assured + TestNG API automation framework

# Task Management API Automation Framework

This repository contains an API automation framework built to test a **Task Management REST API**.
The framework follows industry-standard practices and is suitable for **SDET-1 level interviews**.

---

## 🚀 Tech Stack

- **Java 21**
- **REST Assured** – API automation
- **TestNG** – Test framework
- **Maven** – Build & dependency management
- **Log4j2** – Logging

---

## 🧱 Framework Structure

```text
src
├── main
│   └── java
│       ├── client        # API client classes (AuthClient, TaskClient)
│       ├── config        # Configuration reader
│       ├── model         # Request & Response POJOs
│       └── utils         # Common utilities
│
└── test
    └── java
        ├── base          # Base test setup
        ├── client        # Test clients
        └── tests
            ├── auth     # Register & Login tests
            └── tasks    # Task CRUD tests

```
---

## 🔑 APIs Covered

### Authentication
- Register User
- Login User

### Task Management
- Create Task
- Get Tasks (user-scoped)
- Update Task
- Delete Task

---

## 🧪 Test Design Approach

- Tests are **independent** (no execution order dependency)
- Login uses a **pre-created test user**
- Register test generates a **unique email dynamically**
- Task APIs are tested using **JWT auth token**
- Clear separation between:
    - Test logic
    - API clients
    - Data models
- Request and response logging enabled for debugging

---

## ▶️ How to Run Tests

Run all tests using the TestNG suite:

```bash
   mvn clean test -DsuiteXmlFile=testng.xml
   📊 Test Reports

Test execution reports are generated automatically by Maven Surefire

Reports are available at:

target/surefire-reports/index.html
