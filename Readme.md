# BaseControllerTest
Это базовый класс для тестирования контроллеров в приложении, использующем Spring Boot.

## Возможности
* Автоматическая настройка MockMvc
* Поддержка тестирования контроллеров с различными методами HTTP (GET, POST, PUT, DELETE)
* Возможность отправки запросов с JSON-телом
* Автоматическая проверка статуса ответа на 200 (OK)
* Вывод результатов запроса в консоль

## Использование
1. Создайте тестовый класс, наследующий от BaseControllerTest.
2. тестовом классе создайте методы, вызывающие методы mockGetRequest, mockPostRequest, mockPutRequest или mockDeleteRequest для отправки запросов к контроллеру.


```java
@ActiveProfiles("local")
public class MyControllerTest extends BaseControllerTest {

    @Test
    public void testGetRequest() {
        mockGetRequest("/my-controller");
    }

    @Test
    public void testPostRequest() {
        String content = "{\"name\":\"John\",\"age\":30}";
        mockPostRequest("/my-controller", content);
    }

    @Test
    public void testPutRequest() {
        String content = "{\"name\":\"Jane\",\"age\":25}";
        mockPutRequest("/my-controller", content);
    }

    @Test
    public void testDeleteRequest()  {
        String content = "{\"id\":1}";
        mockDeleteRequest("/my-controller", content);
    }
}
```

## Методы
* `mockGetRequest(String url)`: отправляет GET-запрос на указанный URL.
* `mockGetRequest(String url, Long id)`: отправляет GET-запрос на указанный URL с указанным идентификатором.
* `mockPostRequest(String url, String content)`: отправляет POST-запрос на указанный URL с указанным содержимым.
* `mockPutRequest(String url, String content)`: отправляет PUT-запрос на указанный URL с указанным содержимым.
* `mockPutRequest(String url, String id, String content)`: отправляет PUT-запрос на указанный URL с указанным идентификатором и содержимым.
* `mockPutRequest(String url, Long id, String content)`: отправляет PUT-запрос на указанный URL с указанным идентификатором и содержимым.
* `mockDeleteRequest(String url, String content)`: отправляет DELETE-запрос на указанный URL с указанным содержимым.