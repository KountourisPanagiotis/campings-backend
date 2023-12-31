# campings-backend
## Athens University of Economics and Business ([AUEB](https://aueb.gr/)) 🏫 👨‍🎓
A [Coding Factory](https://codingfactory.aueb.gr/) @ [AUEB](https://aueb.gr/) Java Final Project.

## Description
This project is based on the database requirements that can be found [here](https://drive.google.com/file/d/1_8gIdewyfhv6x67EeV9i8NTQllBPQgMl/view?usp=sharing). They were deeply analysed and implemented in [aueb-sql-final-project-database](https://github.com/KountourisPanagiotis/aueb-sql-final-project-database) as the final SQL *Coding Factory* Project. It provides a comprehensive backend for managing campsite data, with a local MySQL server serving as the data persistence layer. The Angular frontend for this project can be found at [campings-frontend](https://github.com/KountourisPanagiotis/campings-frontend) repository.

## Key Features
- A RESTful API constructed with JAX-RS, enabling CRUD operations on campsite data.
- Utilizes the Jakarta EE framework for building a scalable and robust backend.
- Local MySQL server integration for data persistence and manipulation.
- Deployment-ready for Apache Tomcat 9.0.72.

<div align="center">
  <h2>Database Diagram</h2>
  <img src="src/main/resources/images/camping_eer_diagram.png" alt="Database Diagram" width="700" height="500">
</div>

# Getting Started
## Prerequisites
- JDK 11 or higher.
- Local MySQL Server.
- Apache Tomcat 9.0.72.
- IntelliJ IDEA or any suitable Java IDE supporting Apache Tomcat integration
- Basic knowledge of Java, JAX-RS, Jakarta EE, and MySQL.

## Installation
1. Clone the repository:
```bash
git clone https://github.com/KountourisPanagiotis/cf-campings-jax.git
```
2. Open the project in your chosen Java IDE.
3. Modify MySQL server details.
4. In DBUtil Class adjust windows environmental variables for `CAMPDB_USERNAME` and `CAMPDB_PASSWORD` for database connection. Also adjust the database url in same Class.
5. Build the project and start the server. 

## Usage
Once the server is operational, you can interact with the REST API endpoints through any HTTP client such as Postman or use the corresponding Angular framework [campings-frontend](https://github.com/KountourisPanagiotis/campings-frontend) that was build specifically for this backend.
