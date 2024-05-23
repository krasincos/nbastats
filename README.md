# Project README

## Requirements
- **Java 17+**
- **Docker-compose version 3.9**

## How to Run
Make sure ports **8080**, **8081**, and **8082** are free.

```sh
docker-compose up
```

## Architecture Description
The app consists of three microservices:

1. **Stats-Logger-Service**
   - Provides an endpoint for writing `PlayerStats` to RabbitMQ. Although this could be done directly, this service is included for illustrative purposes.

2. **Stats-Processor-Service**
   - Retrieves `PlayerStats` from RabbitMQ and writes them to the database.

3. **Stats-Aggregator-Service**
   - Provides an endpoint for retrieving average stats per player and season averages per team.

### Technology Stack
- **PostgreSQL**: Stores `PlayerStats` in a single table with independent records.
- **RabbitMQ**: Decouples usage by automated tools and writing to the database for resilience.
- **Redis**: Caches potentially heavy requests from `stats-aggregator-service`.

## Endpoints

### Add Stat Log
**POST** `http://localhost:8081/api/v1/stats/log`
- **Content-Type**: application/json

**Request Body**:
```json
{
  "playerId": "LeBron James",
  "teamId": "1",
  "seasonId": "1",
  "points": 10,
  "rebounds": 40,
  "assists": 30,
  "steals": 23,
  "blocks": 24,
  "fouls": 6,
  "turnovers": 54,
  "minutesPlayed": 32
}
```

### Get Average Score in Season by Team
**GET** `http://localhost:8080/api/v1/stats/avg/teams?seasonId=1`

### Get Average Score in Season by Player
**GET** `http://localhost:8080/api/v1/stats/avg/players?seasonId=1`

## Thoughts

1. **Asynchronous Calls**: Not implemented as the calls are very short. Async might not add notable performance when the system is not loaded and could even decrease performance under heavy load by blocking more threads per request. This assumption should be checked with a performance test.
2. **Performance Estimates**:
   For the test task, let’s speculate
   - **Postgres/RabbitMQ**: On expensive hardware, they should handle 10-20k writes per second.
   - **Stats-Logger-Service**: Should serve up to 10k RPS, given it mainly writes to RabbitMQ.
   - **Stats-Processor-Service**: Around 250+ RPS due to cache invalidation and DB writes.
   - **Stats-Aggregator-Service**: Highly variable, depending on cache efficiency and DB size.
   - **Redis**: won't be an issue for sure🚀
3. **Validation**: Did not add validation for negative points, considering the rare case of a player scoring an own basket.
4. **DTO Design**: Included `teamId`, `playerId`, and `seasonId` in the DTO rather than the URL, which might be correct or not, depending on the broader application context.
5. **Player-Team Relationship**: Kept `playerId` and `teamId` in `PlayerStats` to ensure correct stats if a player changes teams. Avoided creating `Player` and `Team` entities.
6. **Transaction Management**: Did not use transactions, as all records are independent and use UUIDs.

### Potential Improvements

1. **Queue Overflow Alert**: Set up an alert system for queue overflows.
2. **Module Separation**: Separate the common model into its own module.
3. **Dependency Management**: Clean up and organize dependencies and versions.
4. **Performance Testing**: Write and execute performance tests using cloud resources.
5. **Virtual Threads**: Investigate using virtual threads in Spring Boot for better parallelism.
6. **End-to-End Tests**: Develop comprehensive end-to-end tests, as unit tests offer limited value for the minimal service layer logic.
7. **Autoscaling and Load-Balancing**: Implement these features for improved scalability.
8. **Config-Service**: Add an external configuration service.
9. **Exception Handling**: Write robust exception handling mechanisms.
10. **Code Cleanup**: Conduct a global cleanup, as some corners were cut to prioritize showcasing skills for this test task over comprehensive development.
11. **Batch Savings**: Modify Stats-Processor-Service to save stats in small batches every 1-3 seconds for improved performance.
