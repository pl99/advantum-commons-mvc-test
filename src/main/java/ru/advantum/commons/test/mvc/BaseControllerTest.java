package ru.advantum.commons.test.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("local")
@TestPropertySource(properties = {
        "eureka.client.register-with-eureka=false"
        ,"eureka.client.fetch-registry=true"
        , "spring.cloud.config.discovery.enabled=false"
        , "spring.liquibase.enabled=false"
        , "spring.flyway.enabled=false"
})

public abstract class BaseControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper mapper;

    protected MvcResult mockGetRequest(String url, Long id) {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(url, id)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = executeIt(requestBuilder);
        assertionForGet(result, url, id);
        return result;
    }

    protected MvcResult mockGetRequest(String url) {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(url)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = executeIt(requestBuilder);
        assertionForGet(result, url);
        return result;
    }

    protected MvcResult mockPostRequest(String url, String content) {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(url)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = executeIt(requestBuilder);
        assertionForPost(result, url, content);
        return result;
    }

    protected MvcResult mockPutRequest(String url, String content) {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put(url)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = executeIt(requestBuilder);
        assertionForPut(result, url, content);
        return result;
    }

    protected MvcResult mockPutRequest(String url, String id, String content) {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put(url, id)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = executeIt(requestBuilder);
        assertionForPut(result, id, content);
        return result;
    }

    protected MvcResult mockPutRequest(String url, Long id, String content) {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put(url, id)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = executeIt(requestBuilder);
        assertionForPut(result, url, id, content);
        return result;
    }

    protected MvcResult mockDeleteRequest(String url, String content) throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete(url)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = executeIt(requestBuilder);
        assertionForDelete(result, url, content);
        return result;
    }

    @SneakyThrows({Exception.class})
    private MvcResult executeIt(MockHttpServletRequestBuilder requestBuilder) {
        return mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    /**
     * Можно реализовать в наследнике и проверить результат запроса {@code resultAction}
     *
     * @param result
     * @param url
     * @param id
     */
    protected void assertionForGet(MvcResult result, String url, Long id) {
    }

    protected void assertionForGet(MvcResult result, String url) {
    }

    protected void assertionForPost(MvcResult result, String url, String content) {
    }

    protected void assertionForPut(MvcResult result, String url, String content) {
    }

    protected void assertionForPut(MvcResult result, String url, Long id, String content) {
    }

    protected void assertionForDelete(MvcResult result, String url, String content) {
    }


}
