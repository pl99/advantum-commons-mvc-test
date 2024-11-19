# BaseControllerTest Framework

`BaseControllerTest` is an abstract class designed to streamline the testing of Spring Boot controllers using `MockMvc`. This class provides utility methods for testing common HTTP operations (GET, POST, PUT, DELETE) and enables flexible assertions that can be overridden in subclasses.

## Features

- Pre-configured `MockMvc` and `ObjectMapper` for testing REST endpoints.
- Utility methods for common HTTP operations:
    - `GET` requests with or without parameters.
    - `POST`, `PUT`, and `DELETE` requests.
- Customizable assertion methods for validating HTTP responses.
- Automatic configuration for local profiles, including disabling unnecessary features for testing, such as Eureka and database migrations.

## Setup

### Dependencies
Ensure you have the following dependencies in your `pom.xml` or `build.gradle`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
</dependency>
```

### Configuration
The class is annotated with:

- `@SpringBootTest`: Loads the Spring Boot application context.
- `@AutoConfigureMockMvc`: Configures `MockMvc` for integration tests.
- `@ActiveProfiles("local")`: Activates the `local` profile.
- `@TestPropertySource`: Disables specific configurations for testing:
    - `eureka.client.enabled=false`
    - `spring.cloud.config.discovery.enabled=false`
    - `spring.liquibase.enabled=false`
    - `spring.flyway.enabled=false`

## Usage

1. Extend `BaseControllerTest` in your test class.
2. Use the provided utility methods to perform HTTP operations.
3. Override assertion methods to validate the responses as needed.

### Example

```java
package com.example.myapp.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class MyControllerTest extends BaseControllerTest {

    @Test
    public void testGetEndpoint() throws Exception {
        String url = "/api/example";
        mockGetRequest(url)
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.message").value("Success"));
    }

    @Override
    protected void assertionForGet(MvcResult result, String url) {
        // Custom assertions for GET requests.
    }
}
```

## Methods

### Provided Utility Methods

#### GET Requests
- `MvcResult mockGetRequest(String url)`
- `MvcResult mockGetRequest(String url, Long id)`

#### POST Requests
- `MvcResult mockPostRequest(String url, String content)`

#### PUT Requests
- `MvcResult mockPutRequest(String url, String content)`
- `MvcResult mockPutRequest(String url, String id, String content)`
- `MvcResult mockPutRequest(String url, Long id, String content)`

#### DELETE Requests
- `MvcResult mockDeleteRequest(String url, String content)`

### Custom Assertions
Override the following methods in your test class to add custom assertions:

- `protected void assertionForGet(MvcResult result, String url, Long id)`
- `protected void assertionForGet(MvcResult result, String url)`
- `protected void assertionForPost(MvcResult result, String url, String content)`
- `protected void assertionForPut(MvcResult result, String url, String content)`
- `protected void assertionForPut(MvcResult result, String url, Long id, String content)`
- `protected void assertionForDelete(MvcResult result, String url, String content)`

## Best Practices

- Ensure all test endpoints return appropriate HTTP status codes.
- Validate response payloads using `MockMvc` result matchers.
- Keep tests isolated by using profiles and disabling external dependencies.

## License
This framework is licensed under the [MIT License](LICENSE).

## Contributing
Contributions are welcome! Please open an issue or submit a pull request for improvements or bug fixes.

