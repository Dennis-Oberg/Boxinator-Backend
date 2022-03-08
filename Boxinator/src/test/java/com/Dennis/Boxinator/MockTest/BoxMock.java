package com.Dennis.Boxinator.MockTest;

import com.Dennis.Boxinator.BoxinatorApplication;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {
        BoxinatorApplication.class
})
@WebAppConfiguration
public abstract class BoxMock {

    protected MockMvc mock;
    @Autowired
    WebApplicationContext webApplicationContext;

    protected void setup() {
        mock = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    protected String mapToJson(Object ob) throws JsonProcessingException {
        setup();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(ob);
    }

    protected <T> T mapFromJson(String json, Class<T> tClass) throws IOException {
        setup();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, tClass);
    }
}
