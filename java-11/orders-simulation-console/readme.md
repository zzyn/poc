# Engineering Challenge Homework

## Technology

- Java 11
- RxJava 3
- Guice
- Lombok
- Jackson Json
- Logback
- JUnit5
- Mockito

## Development

### Prerequisites

- Java 11
- Maven 3.6.0 (or above)

### Code Standard

- [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) ([example](https://github.com/mattia-battiston/clean-architecture-example))

### Test Strategy

| type              | package                                   | test objective                                    |
| :---------------: | :---------------------------------------- | :------------------------------------------------ |
| unit test         | src/test/{basepackage}/cases/unit         | `core` components (entities, usecases)            |
| component test    | src/test/{basepackage}/cases/component    | test from endpoint to dataprovider with real IO   |

## Question
- the original homework is Orders_Simulation_Homework_for_China.pdf
- you can read the alternative version: question.md at the same folder

## Design
- you can read the design.md at the same folder
