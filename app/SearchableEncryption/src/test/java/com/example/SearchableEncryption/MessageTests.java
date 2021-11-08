package com.example.SearchableEncryption;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MessageTests {
    @Autowired
    private MockMvc mvc;

    @org.junit.Test
    public void getAllMessageEmptyDB() throws Exception {
        this.mvc.perform(get("http://127.0.0.1:8082/api/v1/message/")).andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }
}
