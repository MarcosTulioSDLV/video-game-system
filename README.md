# Video Game System Rest API
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white) ![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=white)  ![H2 Database](https://img.shields.io/badge/H2%20Database-018bff?style=for-the-badge&logoColor=white) ![OpenApi](https://img.shields.io/badge/Docs-OpenAPI-success?style=for-the-badge&logo=swagger)

I developed a Rest API to manage video games that can be grouped into game lists within a video game system, built by using **Spring Boot and Java**, providing CRUD (Create, Read, Update, Delete) operations. This API allows storing game information, such as: title, release year, genre, platforms, and score. Additionally, it supports the storage of game list information with its name. The game list groups games and allows ordering them as desired.

I used some libraries for this Rest API such **Spring Web, Spring Data JPA, Validation, ModelMapper,
H2 Database and SpringDoc OpenAPI Starter WebMVC UI 2.7.0 (for the API documentation)**.

## Requirements

Below are some business rules important to the operation of the system:

• All fields for both game and game list are mandatory and must be filled out.

• A game list allows grouping multiple games together and ordering them based on your preferences.

• A game list can have many games. Similarly, a game can be included in many game lists.

• Each game has a unique position within the game list it belongs to and can be repositioned to the desired position at any time.

• Allow the addition of a game or game list to the system.

• Allow including a game in a specific game list, or associating it with one.

• Allow the update of a game or game list in the system.

• Allow retrieving all games associated with a specific game list. 

• Allow retrieving all games with their positions from a specific game list.

• Allow the removal of a game or game list from the system.

• After a game is removed from the system, all remaining games in the associated game list must be automatically updated to reflect their correct positions.

## Database Config
For test this API, an external Database is not necessary because an embedded Database (H2 Database) was used with the following configuration properties:

- Name: video_game_system_db
- Username: sa
- Password:

## Development Tools
This Rest API was built with:

- Spring Boot version: 3.4.1
- Java version: 17

## System Class Diagram

![VideoGameListClassDiagram](https://github.com/user-attachments/assets/fcd900c8-e702-4e37-ba5d-18688ef62f63)