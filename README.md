# Loan Payment System

## Overview

This project implements a simple **Loan Payment System** using **Spring Boot**.
The application allows users to create loans and record payments against those loans.

The system contains two main domains:

* **Loan Domain** – manages loan creation and retrieval.
* **Payment Domain** – processes payments toward loans.

The application uses an **H2 in-memory database** for persistence.

---

# Technologies Used

* Java
* Spring Boot
* Spring Data JPA
* H2 Database
* Lombok
* Maven
* JUnit

## Lombok

This project uses Lombok to reduce boilerplate code (getters, setters, constructors, etc.).

If using an IDE such as IntelliJ IDEA, ensure the Lombok plugin is installed and annotation processing is enabled.

---

# How to Run the Application

Clone the repository and run:

```
mvn spring-boot:run
```

The application will start at:

```
http://localhost:8080
```

---

# H2 Database

The application uses an **H2 in-memory database**.

You can access the H2 console at:

```
http://localhost:8080/h2-console
```

Use the following connection settings:

```
JDBC URL: jdbc:h2:mem:loan_payment_db
Username: sa
Password:
```

---

# API Endpoints

## Create Loan

```
POST /loans
```

Example request:

```
{
  "loanAmount": 1000,
  "term": 12
}
```

Example response:

```
{
  "loanId": 1,
  "loanAmount": 1000,
  "balance": 1000,
  "term": 12,
  "status": "ACTIVE"
}
```

---

## Get Loan Details

```
GET /loans/{loanId}
```

Example:

```
GET /loans/1
```

---

## Record Payment

```
POST /payments
```

Example request:

```
{
  "loanId": 1,
  "paymentAmount": 200
}
```

---

# Business Rules

* A loan must have an **amount** and a **term**.
* Payments reduce the **remaining loan balance**.
* **Overpayment is not allowed**.
* When the loan balance reaches **0**, the loan status becomes **SETTLED**.

---

# Running Tests

Run unit tests with:

```
mvn test
```

Tests verify:

* A loan is successfully created.
* Payments reduce the loan balance.
* Overpayment raises an error.
* A loan becomes **SETTLED** when fully paid.

---

# Project Structure

```
src
 ├── controller
 ├── service
 ├── repository
 ├── entity
 └── exception
```

---

# Notes

* The application uses an **H2 in-memory database**, so all data is reset when the application restarts.
* The project focuses on **clean architecture, business logic, and unit testing**.
