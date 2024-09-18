package ru.advantum.commons.test.mvc;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("local")
@TestPropertySource(properties = {
        "eureka.client.enabled=false"
        , "spring.cloud.config.discovery.enabled=false"
        , "spring.liquibase.enabled=false"
        , "spring.flyway.enabled=false"
})

public abstract class BaseControllerTest {
    @Autowired
    protected MockMvc mockMvc;


    protected void mockGetRequest(String url, Long id) {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(url, id)
                .contentType(MediaType.APPLICATION_JSON);
        executeIt(requestBuilder);
    }
    protected void mockGetRequest(String url) {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(url)
                .contentType(MediaType.APPLICATION_JSON);
        executeIt(requestBuilder);
    }
    protected void mockPostRequest(String url, String content) {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(url)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON);
        executeIt(requestBuilder);
    }
    protected void mockPutRequest(String url, String content) {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put(url)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON);
        executeIt(requestBuilder);
    }
    protected void mockPutRequest(String url, String id, String content) {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put(url, id)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON);
        executeIt(requestBuilder);
    }
    protected void mockPutRequest(String url,Long id, String content) {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put(url, id)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON);
        executeIt(requestBuilder);
    }
    protected void mockDeleteRequest(String url, String content) throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete(url)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON);
        executeIt(requestBuilder);
    }

    @SneakyThrows({Exception.class})
    private void executeIt(MockHttpServletRequestBuilder requestBuilder) {
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

}
