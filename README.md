# Campus Club Directory

A Spring Boot application that lets visitors view campus clubs and add new clubs. Club information is stored in an embedded Apache Derby database and accessed through a REST API.

## Technologies

- Java 25
- Spring Boot 4.1.1
- Maven
- Spring Web MVC
- Spring Data JPA
- Jakarta Validation
- Apache Derby
- HTML, CSS, and JavaScript

## Features

- View all campus clubs.
- Filter clubs by category through the API.
- Find a club by its ID.
- Add a club through the webpage.
- Validate required fields and maximum field lengths.
- Keep saved clubs after restarting the application.
- Load three starter clubs when the database is empty.

## Requirements

- JDK 25
- Internet access for the first Maven dependency download

The project includes the Maven Wrapper, so a separate Maven installation is not required.

## Running the Application

Open a terminal in the project folder.

### macOS or Linux

```bash
chmod +x mvnw
./mvnw spring-boot:run
```

On macOS, if Maven uses an older Java version:

```bash
JAVA_HOME=$(/usr/libexec/java_home -v 25) ./mvnw spring-boot:run
```

### Windows

```powershell
.\mvnw.cmd spring-boot:run
```

Open:

http://localhost:8085

Keep the application running while using the webpage. Press Control+C in the terminal to stop it.

## Starter Clubs

| Name | Category | Meeting Day | Room |
|---|---|---|---|
| Computer Science Club | Academic | Tuesday | Lupton Hall 120 |
| Chess Club | Games | Wednesday | Campus Center 205 |
| Running Club | Sports | Friday | Nold Hall |

The starter clubs are inserted only when the club table is empty.

## REST API

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/clubs` | Return all clubs |
| GET | `/api/clubs?category=Sports` | Return clubs in a category, ignoring case |
| GET | `/api/clubs/{id}` | Return one club, or HTTP 404 if it does not exist |
| POST | `/api/clubs` | Validate and create a club |

A successful POST returns HTTP 201. Invalid club details return HTTP 400.

### Example POST Body

```json
{
  "name": "Robotics Club",
  "category": "Academic",
  "meetingDay": "Thursday",
  "room": "Lupton Hall 210"
}
```

## Validation Rules

| Field | Required | Maximum Length |
|---|---|---|
| Name | Yes | 60 characters |
| Category | Yes | 30 characters |
| Meeting day | Yes | 20 characters |
| Room | No | 40 characters |

The database generates each club's ID automatically.

## Project Structure

- `Club.java`: Club entity and validation rules.
- `ClubRepository.java`: Database access and category queries.
- `ClubController.java`: REST API endpoints.
- `config/DataLoader.java`: Adds starter clubs to an empty database.
- `src/main/resources/application.yaml`: Database and server configuration.
- `src/main/resources/static/index.html`: Webpage, form, and API requests.

## Database

The application uses:

```text
jdbc:derby:./data/clubs-db;create=true
```

Database files are stored in the project's `data` folder. The `data` folder and `derby.log` are excluded from Git.

## Build and Test

With JDK 25 configured, run:

```bash
./mvnw clean verify
```

On Windows:

```powershell
.\mvnw.cmd clean verify
```

## Manual Checks

1. Open the homepage and confirm the club list appears.
2. Submit an empty form and check for the red error message.
3. Add Robotics Club and confirm it appears in the list.
4. Refresh the page and confirm the new club remains.
5. Restart the application and confirm the data is still saved.
6. Open `/api/clubs?category=Sports` and check the category filter.
7. Request an ID that does not exist and check for HTTP 404.
