package com.example.SearchableEncryption;

import com.example.SearchableEncryption.models.Message;
import org.json.JSONObject;
import org.junit.Assert;
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

    @org.junit.Test
    public void getMessageById() throws Exception {
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

        JSONObject jo1 = new JSONObject();
        jo1.put("id", 2);
        jo1.put("sender_username", "other_sender");
        jo1.put("receiver_username", "other_receiver");
        jo1.put("message_title", "other_title");
        jo1.put("message_body", "other_body");
        jo1.put("sent_date", "12.11.1111");
        jo1.put("read_date", "12.11.1111");
        jo1.put("read", "false");

        this.mvc.perform(post("http://127.0.0.1:8082/api/v1/message/").contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(jo1)))
                .andExpect(status().isOk());

        String result1 = "{\"id\":" + jo.get("id").toString() + ",\"sender_username\":\"" + jo.get("sender_username")
                + "\",\"receiver_username\":\"" + jo.get("receiver_username") + "\",\"message_title\":\"" + jo.get("message_title")
                + "\",\"message_body\":\"" + jo.get("message_body") + "\",\"sent_date\":\"" + jo.get("sent_date")
                + "\",\"read_date\":\"" + jo.get("read_date") + "\",\"read\":\"" + jo.get("read") + "\"}";
        
        this.mvc.perform(get("http://127.0.0.1:8082/api/v1/message/id=1")).andExpect(status().isOk())
                .andExpect(content().json(result1));

        String result2 = "{\"id\":" + jo1.get("id").toString() + "\",\"sender_username\":\"" + jo1.get("sender_username")
                + "\",\"receiver_username\":\"" + jo1.get("receiver_username") + "\",\"message_title\":\"" + jo1.get("message_title")
                + "\",\"message_body\":\"" + jo1.get("message_body") + "\",\"sent_date\":\"" + jo1.get("sent_date")
                + "\",\"read_date\":\"" + jo1.get("read_date") + "\",\"read\":\"" + jo1.get("read") + "\"}";

        this.mvc.perform(get("http://127.0.0.1:8082/api/v1/messages/id=2")).andExpect(status().isOk())
                .andExpect(content().string(result2));

        this.mvc.perform(get("http://127.0.0.1:8082/api/v1/messages/id=3")).andExpect(status().isOk())
                .andExpect(content().string(""));

        this.mvc.perform(delete("http://127.0.0.1:8082/api/v1/message/id=1")).andExpect(status().isOk())
                .andExpect(status().isOk());

        this.mvc.perform(delete("http://127.0.0.1:8082/api/v1/message/id=2")).andExpect(status().isOk())
                .andExpect(status().isOk());
    }

    @org.junit.Test
    public void setGetIdTest() throws Exception {
        Message message = new Message();
        message.setId(1);

        Assert.assertEquals(message.getId(), 1);
    }

    @org.junit.Test
    public void setGetSenderUsernameTest() throws Exception {
        Message message = new Message();
        message.setSender_username("sender_username");

        Assert.assertEquals(message.getSender_username(), "sender_username");
    }

    @org.junit.Test
    public void setGetReceiverUsernameTest() throws Exception {
        Message message = new Message();
        message.setReceiver_username("receiver_username");

        Assert.assertEquals(message.getReceiver_username(), "receiver_username");
    }

    @org.junit.Test
    public void setGetMessageTitleTest() throws Exception {
        Message message = new Message();
        message.setMessage_title("example_of_title");

        Assert.assertEquals(message.getMessage_title(), "example_of_title");
    }

    @org.junit.Test
    public void setGetMessageBodyTest() throws Exception {
        Message message = new Message();
        message.setMessage_body("example_of_body");

        Assert.assertEquals(message.getMessage_body(), "example_of_body");
    }

    @org.junit.Test
    public void setGetReadTest() throws Exception {
        Message message = new Message();
        message.setRead(false);

        Assert.assertFalse(message.getRead());

        message.setRead(true);

        Assert.assertTrue(message.getRead());
    }

    @org.junit.Test
    public void setGetSentDateTest() throws Exception {
        Message message = new Message();
        message.setSent_date("11.11.1111");

        Assert.assertEquals(message.getSent_date(), "11.11.1111");
    }

    @org.junit.Test
    public void setGetReadDateTest() throws Exception {
        Message message = new Message();
        message.setRead_date("11.11.1111");

        Assert.assertEquals(message.getRead_date(), "11.11.1111");
    }

}
