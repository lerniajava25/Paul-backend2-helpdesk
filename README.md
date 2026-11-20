# Helpdesk – Ticket Management System

A web application where users can create and follow support tickets, and support agents can handle them. Built as a lab assignment in Backend Development and System Integration.

## Tech stack

- Java 21
- Spring Boot 4.1.2 (Spring MVC, Spring Data JPA, Spring Security)
- Thymeleaf (server-side rendered pages)
- MySQL
- Maven

## Getting started

### 1. Database

Make sure MySQL is running on `localhost:3306`, then create the database:

```sql
CREATE DATABASE helpdesk;
```

The tables are created automatically by Hibernate when the application starts.

### 2. Database password

The MySQL password is read from the environment variable `DB_PASSWORD` (the username is `root`).

1. Click the dropdown next to **DemoApplication** (top right in IntelliJ) → **Edit Configurations...**
2. In **Environment variables**, enter:
   ```
   DB_PASSWORD=your_mysql_password
   ```
   If the field is not visible: **Modify options → Environment variables**.
3. Click **Apply** and **OK**.

### 3. Run the application

Run `DemoApplication` with the green play button. When the console shows `Started DemoApplication`, open http://localhost:8080 in your browser.

### Troubleshooting

- **`Access denied for user 'root'@'localhost'`**: `DB_PASSWORD` is not set or the password is wrong. Check that the environment variable is saved in the run configuration (remember **Apply**).

## Test accounts

Created automatically on first start (when the users table is empty):

| Username | Password    | Role  |
|----------|-------------|-------|
| user     | userTest    | USER  |
| agent    | agentTest   | AGENT |
| admin    | adminTest   | ADMIN |

## Features

### USER
- Log in and log out
- Create tickets (with validation)
- See only their own tickets
- View details of their own tickets
- Add comments to their own tickets

### AGENT
- See all tickets
- View ticket details
- Assign a ticket to themselves
- Change ticket status (NEW, IN_PROGRESS, WAITING_FOR_USER, RESOLVED, CLOSED)
- Add comments to any ticket

### ADMIN
- All AGENT features

## Security

- Passwords are hashed with BCrypt
- Login against the database via a custom `UserDetailsService`
- Disabled accounts (`enabled = false`) cannot log in
- URL rules: `/agent/**` requires AGENT or ADMIN, `/admin/**` requires ADMIN
- Ownership check in the service layer: a USER gets **403** when trying to open another user's ticket, even by typing the URL directly
- CSRF protection on all forms

## Project structure

```
controller   – handles web requests and chooses which page to show
service      – business logic and access rules
repository   – database access (Spring Data JPA)
model        – entities (User, Ticket, Comment) and enums
dto          – form objects with validation
security     – CustomUserDetailsService
config       – SecurityConfig and test data (DataInitializer)
```

