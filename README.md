# IceTrack

IceTrack is a Spring Boot application for tracking figure skating sessions, exercises, combinations, spins, and system status. It provides RESTful APIs for managing these entities and checking system health.

## Features
- Track skating sessions, exercises, jump combinations, and spins.
- Health and database connectivity checks.
- Extensible for additional entities.

## API Endpoints

### InitController (`/iceTrack/init`)
- `GET /iceTrack/init/is-active`  
  Returns "active" if the service is running.
- `GET /iceTrack/init/db-status`  
  Checks database connectivity and returns exercise count.

### SessionController (`/iceTrack/sessions`)
- `POST /iceTrack/sessions`  
  Create a new session.  
  Request body: `Session` object.
- `GET /iceTrack/sessions/{id}`  
  Get a session by ID.

### ExerciseController (`/iceTrack/exercises`)
- `POST /iceTrack/exercises`  
  Create a new exercise.  
  Request body: `Exercise` object.
- `GET /iceTrack/exercises/{id}`  
  Get an exercise by ID.

### CombinationController (`/iceTrack/combinations`)
- `POST /iceTrack/combinations`  
  Create a new jump combination.  
  Request body: `Combination` object.
- `GET /iceTrack/combinations/{id}`  
  Get a combination by ID.

### SpinController (`/iceTrack/spins`)
- `POST /iceTrack/spins`  
  Create a new spin.  
  Request body: `Spin` object.
- `GET /iceTrack/spins/{id}`  
  Get a spin by ID.

---

## Entity Field Descriptions

### Session
- `sessionId` (Long): Unique identifier for the session.
- `description` (String): Description of the session.
- `date` (LocalDate): Date of the session.
- `location` (String): Location of the session.
- `attempts` (List<Attempt>): List of attempts associated with the session.

### Exercise
- `elementId` (Long): Unique identifier (inherited from Element).
- `name` (String): Name of the exercise (e.g., "Rocker–Counter Drill").
- `skills` (List<BaseSkill>): List of base skills involved in the exercise.

### Combination
- `elementId` (Long): Unique identifier (inherited from Element).
- `combinationName` (String): Name of the combination.
- `entrance` (String): Description of the entrance to the combination.
- `jumps` (List<Jump>): List of jumps in the combination.
- `spins` (List<Spin>): List of spins in the combination.

### Jump
- `jumpType` (Enum: JumpType): Type of jump (e.g., Axel, Toe Loop).
- `rotations` (Integer): Number of rotations.
- `prerotated` (Boolean): Whether the jump was prerotated.
- `underRotated` (Boolean): Whether the jump was underrotated.

### Spin
- `rotations` (Integer): Number of rotations.
- `position` (Enum: SpinPosition): Position of the spin (e.g., Sit, Camel).
- `variation` (String): Variation of the spin.

### BaseSkill
- `skillId` (Long): Unique identifier for the skill.
- `direction` (Enum: Direction): Direction of the skill.

---

## Example Requests & Responses

### Session
**POST /iceTrack/sessions**
```json
{
  "description": "Morning practice",
  "date": "2025-09-05",
  "location": "Local Rink"
}
```
**Response**
```json
{
  "sessionId": 1,
  "description": "Morning practice",
  "date": "2025-09-05",
  "location": "Local Rink",
  "attempts": []
}
```

### Exercise
**POST /iceTrack/exercises**
```json
{
  "name": "Forward Outside Edges",
  "skills": [
    { "skillId": 1, "direction": "FORWARD" },
    { "skillId": 2, "direction": "BACKWARD" }
  ]
}
```
**Response**
```json
{
  "elementId": 1,
  "name": "Forward Outside Edges",
  "skills": [
    { "skillId": 1, "direction": "FORWARD" },
    { "skillId": 2, "direction": "BACKWARD" }
  ]
}
```

### Combination
**POST /iceTrack/combinations**
```json
{
  "combinationName": "Double Axel + Triple Toe",
  "entrance": "Back outside edge",
  "jumps": [
    {
      "jumpType": "AXEL",
      "rotations": 2,
      "prerotated": false,
      "underRotated": false
    },
    {
      "jumpType": "TOE_LOOP",
      "rotations": 3,
      "prerotated": false,
      "underRotated": true
    }
  ],
  "spins": []
}
```
**Response**
```json
{
  "elementId": 1,
  "combinationName": "Double Axel + Triple Toe",
  "entrance": "Back outside edge",
  "jumps": [
    {
      "jumpType": "AXEL",
      "rotations": 2,
      "prerotated": false,
      "underRotated": false
    },
    {
      "jumpType": "TOE_LOOP",
      "rotations": 3,
      "prerotated": false,
      "underRotated": true
    }
  ],
  "spins": []
}
```

### Spin
**POST /iceTrack/spins**
```json
{
  "rotations": 5,
  "position": "SIT",
  "variation": "Cannonball"
}
```
**Response**
```json
{
  "elementId": 1,
  "rotations": 5,
  "position": "SIT",
  "variation": "Cannonball"
}
```

---

## Getting Started

1. Clone the repository.
2. Configure your database in `src/main/resources/application.properties`.
3. Build and run the application:
   ```
   ./mvnw spring-boot:run
   ```
4. Access the API at `http://localhost:8080/iceTrack/`

## Testing
- Unit and integration tests are in `src/test/java/com/wemi/IceTrack/`.
- Run tests with:
  ```
  ./mvnw test
  ```

## Postman Collection
A Postman collection for all endpoints is available in the `postman/` folder:  
`postman/IceTrack-API-Collection.postman_collection.json`

## Contributing
Feel free to open issues or submit pull requests for improvements or new features.

