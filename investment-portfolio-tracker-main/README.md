# Investment & Portfolio Tracker

A full-stack application to help users manage and analyze their investments across multiple financial instruments like stocks, mutual funds, and fixed deposits. Built by **Team Zeta Horizon** as part of the Capstone Internship Project.

---

## Project Structure

This repository contains both frontend and backend code:
```
investment-portfolio-tracker/
├── backend/
│   └── investment-portfolio-tracker/
│       ├── src/
│       ├── pom.xml
│       └── ...
├── frontend/
│   └── (Vue.js source code here)
└── README.md
```

---

## Team Members & Modules

| Name                     | Module Number | Module Description                       |
|--------------------------|---------------|------------------------------------------|
| Abhijit Singh            | 1             | User Registration & Authentication       |
| Darshan R M              | 2             | Investment Product Listing & Selection   |
| Gowrisubha Kalimuthu     | 3             | Portfolio Management                     |
| Shashank M Patil         | 4             | Portfolio Analytics & Insights           |
| Nareshkumar G            | 5             | Support & Helpdesk System                |

---

## Technologies Used
Backend: Java, Spring Boot, Spring Data JPA, MySQL, JUnit, Lombok, Docker

Frontend: Vue.js

Database: MySQL

Tools: GitHub, Postman, Jira

---

## Database Setup

Create a database in your system before staring the backend:
``` bash
CREATE DATABASE investment_tracker;
```

### How to Configure Springboot for MySQL?..

Step 1 : Use application.properties with placeholders (already done in main branch)
``` bash
spring.datasource.url=${DATABASE_URL}
spring.datasource.username=${DATABASE_USERNAME}
spring.datasource.password=${DATABASE_PASSWORD}
jwt.secret=${JWT_SECRET_KEY}
```

Step 2 : Use .env file
Create a .env file (never commit this file):
``` bash
DATABASE_URL=jdbc:mysql://localhost:3306/investment_tracker
DATABASE_USERNAME=root
DATABASE_PASSWORD=password(REPLACE THIS)
JWT_SECRET_KEY=key(REPLACE THIS)
```
Note: JWT secret key can be generated using below command and paste the key inside .env file
``` bash
openssl rand -base64 32
```

Step 3 : Add .env to .gitignore

Step 4 : Add .env file to environment variables. Application name in top right corner -> edit configurations -> choose the in environment variables add .env file.

---

## Getting Started

### Prerequisites

- Java 17+
- Node.js 18+
- Maven
- Docker
- Git


### How To Run Backend (Spring Boot)

Step 1 : Clone the repository:
``` bash 
git clone https://github.com/darshan2156/investment-portfolio-tracker.git
```

Step 2 : Navigate to the backend directory:
``` bash 
cd investment-portfolio-tracker/backend/investment-portfolio-tracker
```

Step 3 : Run the application using Maven or your IDE:
``` bash 
./mvnw spring-boot:run
```

API will run on: http://localhost:8080

### How To Run Frontend (Vue.js)



---

## Features
1. User registration/login 

---

## Architecture & Design
Layered Architecture: Controller → Service → Repository

---

## GitHub Collaboration Workflow
All team members will push code to their own branches. Pull Requests (PRs) must be reviewed and merged by another team member, not the person who raised the PR.

### Git Workflow

``` bash
# Clone the repository (only the first time)
git clone https://github.com/darshan2156/investment-portfolio-tracker.git

cd investment-portfolio-tracker


# Create and switch to your feature branch
git checkout -b your-name/feature-name


# Make your changes, then commit and push
git add .

git commit -m "Add: feature description here"

git push origin your-name/feature-name
``` 

### Pull Request (PR) Process

1. Go to GitHub and create a Pull Request from your branch to the main branch.

2. Another team member, not the person who raised the PR will review the code, request changes if needed, and merge approved PRs.

3. Everyone must regularly pull latest changes from the main branch to keep their branch up to date:

``` bash
git checkout main
git pull origin main
```

--- 

## Project Management

1. Kanban Board: Jira

2. Checklist Tracking: Shared Docs

---

## Documentation Index

1. ER Diagram

---