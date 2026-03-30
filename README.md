# QuantityMeasurementSpringBoot

The Quantity Measurement Application is a Spring Boot–based RESTful service designed to perform operations on physical quantities such as Length, Volume, Weight, and Temperature. The application supports arithmetic operations, unit conversions, comparisons, and maintains a history of all operations using a relational database.

This project demonstrates clean architecture, layered design, validation, exception handling, and API documentation using OpenAPI (Swagger).

---

## Features

* Perform arithmetic operations on quantities:

  * Addition
  * Subtraction
  * Division
* Compare two quantities
* Convert quantities between units
* Support for multiple measurement types:

  * Length
  * Volume
  * Weight
  * Temperature
* Store and retrieve operation history
* Filter history by:

  * Operation type
  * Measurement type
  * Error status
* Count successful operations
* Input validation using Jakarta Validation
* Centralized exception handling
* Interactive API documentation with Swagger UI

---

## Technology Stack

* Java 17+
* Spring Boot
* Spring Web
* Spring Data JPA
* Hibernate
* H2 / MySQL (configurable)
* Jakarta Validation
* OpenAPI (springdoc)
* Maven

---

## Project Structure

```
com.app.quantitymeasurement
│
├── config
│   └── OpenAPIConfig.java
│
├── controller
│   └── QuantityMeasurementController.java
│
├── service
│   ├── QuantityMeasurementService.java
│   └── QuantityMeasurementServiceImpl.java
│
├── repository
│   └── QuantityMeasurementRepository.java
│
├── model
│   ├── QuantityDTO.java
│   ├── QuantityInputDTO.java
│   ├── QuantityMeasurementDTO.java
│   └── QuantityMeasurementEntity.java
│
├── unit
│   ├── Unit.java
│   ├── Quantity.java
│   ├── LengthUnit.java
│   ├── VolumeUnit.java
│   ├── WeightUnit.java
│   └── TemperatureUnit.java
│
├── exception
│   └── GlobalExceptionHandler.java
│   └── QuantityMeasurementException.java
│  
test
│ 
├── controller
│   ├── QuantityMeasurementControllerTest.java
│
├── service
│   ├── QuantityMeasurementServiceImplTest.java
│
├── repository
│   ├── QuantityMeasurementRepositoryTest.java

```

---

## API Endpoints

Base URL:

```
/api/v1/quantities
```

### 1. Compare Quantities

**POST** `/compare`

Request:

```json
{
  "thisQuantityDTO": {
    "value": 1,
    "unit": "METER",
    "measurementType": "LengthUnit"
  },
  "thatQuantityDTO": {
    "value": 100,
    "unit": "CENTIMETER",
    "measurementType": "LengthUnit"
  }
}
```

---

### 2. Convert Quantity

**POST** `/convert`

Request:

```json
{
  "thisQuantityDTO": {
    "value": 1,
    "unit": "METER",
    "measurementType": "LengthUnit"
  },
  "targetQuantityDTO": {
    "unit": "CENTIMETER",
    "measurementType": "LengthUnit"
  }
}
```

---

### 3. Add Quantities

**POST** `/add`

---

### 4. Add with Target Unit

**POST** `/add-with-target-unit`

---

### 5. Subtract Quantities

**POST** `/subtract`

---

### 6. Subtract with Target Unit

**POST** `/subtract-with-target-unit`

---

### 7. Divide Quantities

**POST** `/divide`

---

### 8. Get Operation History

**GET** `/history/operation/{operation}`

---

### 9. Get History by Measurement Type

**GET** `/history/type/{type}`

---

### 10. Get Operation Count

**GET** `/count/{operation}`

---

### 11. Get Error History

**GET** `/history/errored`

---

## Validation

The application uses Jakarta Validation to ensure:

* Required fields are not null
* Measurement types are valid
* Units correspond to measurement types

Invalid inputs return structured error responses.

---

## Exception Handling

A centralized exception handler manages:

* Validation errors
* Runtime exceptions

All errors are returned with appropriate HTTP status codes and messages.

---

## Swagger Documentation

Swagger UI is available at:

```
http://localhost:8080/swagger-ui/index.html
```

It provides:

* Interactive API testing
* Request/response schemas
* Example payloads

---

## How to Run

1. Clone the repository
2. Navigate to the project directory
3. Build the project:

```
mvn clean install
```

4. Run the application:

```
mvn spring-boot:run
```

---

## Future Enhancements

* Add multiplication operation
* Improve unit validation logic
* Add authentication and authorization
* Deploy to cloud platform (AWS/GCP/Azure)
* Add frontend interface (React/Next.js)
* Extend support for additional measurement systems

---

## Conclusion

This project demonstrates a robust implementation of a quantity measurement system using Spring Boot. It highlights best practices in REST API design, layered architecture, validation, and persistence, making it suitable for academic, learning, and production-ready extensions.
