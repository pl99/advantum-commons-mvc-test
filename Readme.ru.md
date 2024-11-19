# BaseControllerTest Framework

`BaseControllerTest` абстрактный класс, разработанный для упрощения тестирования контроллеров Spring Boot с использованием MockMvc. Этот класс предоставляет служебные методы для тестирования общих операций HTTP (GET, POST, PUT, DELETE) и позволяет использовать гибкие утверждения, которые можно переопределять в подклассах.

## Features
- Предварительно настроенные `MockMvc` and `ObjectMapper` для тестирования конечных точек REST.
- Служебные методы для общих операций HTTP:
    - Запросы `GET` с параметрами или без них.
    - Запросы `POST`, `PUT` и `DELETE`.
- Настраиваемые методы утверждений для проверки ответов HTTP.
- Автоматическая настройка локальных профилей, включая отключение ненужных функций для тестирования, таких как Eureka и миграции баз данных.

## Настройка

### Зависимости
Убедитесь, что в pom.xml:

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

### Конфигурация
Класс аннотирован следующим образом:
- `@SpringBootTest`: Загружает контекст приложения Spring Boot.
- `@AutoConfigureMockMvc`: Настраивает MockMvc для интеграционных тестов.
- `@ActiveProfiles("local")`: Активирует локальный профиль.
- `@TestPropertySource`: Отключает определенные конфигурации для тестирования:
   - `eureka.client.enabled=false`
   - `spring.cloud.config.discovery.enabled=false`
   - `spring.liquibase.enabled=false`
   - `spring.flyway.enabled=false`

## Использование
1. Расширьте `BaseControllerTest` в своем тестовом классе.
2. Используйте предоставленные служебные методы для выполнения операций HTTP.
3. Переопределяйте методы утверждения для проверки ответов по мере необходимости.

### Пример

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

## Методы

### Предоставленные служебные методы

#### Запросы GET
- `MvcResult mockGetRequest(String url)`
- `MvcResult mockGetRequest(String url, Long id)`

#### Запросы POST
- `MvcResult mockPostRequest(String url, String content)`

#### Запросы PUT
- `MvcResult mockPutRequest(String url, String content)`
- `MvcResult mockPutRequest(String url, String id, String content)`
- `MvcResult mockPutRequest(String url, Long id, String content)`

#### Запросы DELETE
- `MvcResult mockDeleteRequest(String url, String content)`

### Пользовательские утверждения
Переопределите следующие методы в своем тестовом классе, чтобы добавить пользовательские утверждения:

- `protected void assertionForGet(MvcResult result, String url, Long id)`
- `protected void assertionForGet(MvcResult result, String url)`
- `protected void assertionForPost(MvcResult result, String url, String content)`
- `protected void assertionForPut(MvcResult result, String url, String content)`
- `protected void assertionForPut(MvcResult result, String url, Long id, String content)`
- `protected void assertionForDelete(MvcResult result, String url, String content)`

## Лучшие практики

- Убедитесь, что все конечные точки теста возвращают соответствующие коды статуса HTTP.
- Проверяйте полезные данные ответа с помощью сопоставителей результатов MockMvc.
- Сохраняйте тесты изолированными, используя профили и отключая внешние зависимости.

## Лицензия
Лицензирован в соответствии с [лицензией MIT](LICENSE).

## Contributing
Участие приветствуется! Пожалуйста, откройте ишью или отправьте пулл реквест для улучшений или исправления ошибок.

 


