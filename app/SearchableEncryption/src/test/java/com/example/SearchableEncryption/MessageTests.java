package com.example.SearchableEncryption;

import org.json.JSONObject;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @org.junit.Test
    public void addMessageEmptyDB() throws Exception {
        JSONObject jo = new JSONObject();
        jo.put("id", 1);
        jo.put("sender_username", "sender");
        jo.put("receiver_username", "receiver");
        jo.put("message_title", "title");
        jo.put("message_body", "body");
        jo.put("sent_date", "11.11.1111");
        jo.put("read_date", "11.11.1111");
        jo.put("read", "true");

        this.mvc.perform(post("http://127.0.0.1:8082/api/v1/message/").contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(jo)))
                .andExpect(status().isOk());

        this.mvc.perform(delete("http://127.0.0.1:8082/api/v1/message/id=1")).andExpect(status().isOk())
                .andExpect(status().isOk());
    }

}
