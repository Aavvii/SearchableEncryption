package com.example.SearchableEncryption;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountTest {
    @Autowired
    private MockMvc mvc;

    @org.junit.Test
    public void getAllAccountsEmptyDB() throws Exception {
        this.mvc.perform(get("http://127.0.0.1:8082/api/v1/account/")).andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }

    @org.junit.Test
    public void addAccountEmptyDB() throws Exception {
        JSONObject jo = new JSONObject();
        jo.put("id", 1);
        jo.put("firstname", "FirstName");
        jo.put("lastname", "lastName");
        jo.put("username", "firstName_lastName");
        jo.put("password", "password");

        this.mvc.perform(post("http://127.0.0.1:8082/api/v1/account/").contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(jo)))
                .andExpect(status().isOk());

        this.mvc.perform(delete("http://127.0.0.1:8082/api/v1/account/id=1")).andExpect(status().isOk())
                .andExpect(status().isOk());
    }

    @org.junit.Test
    public void getAccountById() throws Exception {
        JSONObject jo1 = new JSONObject();
        jo1.put("id", 1);
        jo1.put("firstname", "otherFirstName");
        jo1.put("lastname", "otherLastName");
        jo1.put("username", "otherFirstName_lastName");
        jo1.put("password", "otherPassword");

        this.mvc.perform(post("http://127.0.0.1:8082/api/v1/account/").contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(jo1)))
                .andExpect(status().isOk());

        JSONObject jo2 = new JSONObject();
        jo2.put("id", 2);
        jo2.put("firstname", "FirstName");
        jo2.put("lastname", "lastName");
        jo2.put("username", "firstName_lastName");
        jo2.put("password", "password");


        this.mvc.perform(post("http://127.0.0.1:8082/api/v1/account/").contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(jo2)))
                .andExpect(status().isOk());

        String result1 = "{\"id\":" + jo1.get("id").toString() + ",\"firstname\":\"" + jo1.get("firstname") + "\",\"lastname\":\"" + jo1.get("lastname") + "\",\"username\":\"" + jo1.get("username") + "\",\"password\":\"" + jo1.get("password") + "\"}";

        this.mvc.perform(get("http://127.0.0.1:8082/api/v1/account/id=1")).andExpect(status().isOk())
                .andExpect(content().json(result1));

        String result2 = "{\"id\":" + jo2.get("id").toString() + ",\"firstname\":\"" + jo2.get("firstname") + "\",\"lastname\":\"" + jo2.get("lastname") + "\",\"username\":\"" + jo2.get("username") + "\",\"password\":\"" + jo2.get("password") + "\"}";

        this.mvc.perform(get("http://127.0.0.1:8082/api/v1/account/id=2")).andExpect(status().isOk())
                .andExpect(content().json(result2));

        this.mvc.perform(get("http://127.0.0.1:8082/api/v1/account/id=3")).andExpect(status().isOk())
                .andExpect(content().string(""));

        this.mvc.perform(delete("http://127.0.0.1:8082/api/v1/account/id=1")).andExpect(status().isOk())
                .andExpect(status().isOk());

        this.mvc.perform(delete("http://127.0.0.1:8082/api/v1/account/id=2")).andExpect(status().isOk())
                .andExpect(status().isOk());
    }

    @org.junit.Test
    public void getAllAccounts() throws Exception {

        JSONObject jo1 = new JSONObject();
        jo1.put("id", 1);
        jo1.put("firstname", "otherFirstName");
        jo1.put("lastname", "otherLastName");
        jo1.put("username", "otherFirstName_lastName");
        jo1.put("password", "otherPassword");

        this.mvc.perform(post("http://127.0.0.1:8082/api/v1/account/").contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(jo1)))
                .andExpect(status().isOk());

        JSONObject jo2 = new JSONObject();
        jo2.put("id", 2);
        jo2.put("firstname", "FirstName");
        jo2.put("lastname", "lastName");
        jo2.put("username", "firstName_lastName");
        jo2.put("password", "password");
        jo2.get("id");


        this.mvc.perform(post("http://127.0.0.1:8082/api/v1/account/").contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(jo2)))
                .andExpect(status().isOk());

        String expectedResponse = "[{\"id\":" + jo1.get("id").toString() + ",\"firstname\":\"" + jo1.get("firstname") + "\",\"lastname\":\"" + jo1.get("lastname") + "\",\"username\":\"" + jo1.get("username") + "\",\"password\":\"" + jo1.get("password") + "\"}," +
                "{\"id\":" + jo2.get("id").toString() + ",\"firstname\":\"" + jo2.get("firstname") + "\",\"lastname\":\"" + jo2.get("lastname") + "\",\"username\":\"" + jo2.get("username") + "\",\"password\":\"" + jo2.get("password") + "\"}]";

        String responseString = this.mvc.perform(get("http://127.0.0.1:8082/api/v1/account/")).andExpect(status().isOk())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assert.assertEquals("Response does not match", expectedResponse, responseString);

        this.mvc.perform(delete("http://127.0.0.1:8082/api/v1/account/id=1")).andExpect(status().isOk())
                .andExpect(status().isOk());

        this.mvc.perform(delete("http://127.0.0.1:8082/api/v1/account/id=2")).andExpect(status().isOk())
                .andExpect(status().isOk());
    }

    @org.junit.Test
    public void updateAccount() throws Exception {
        JSONObject jo = new JSONObject();
        jo.put("id", 1);
        jo.put("firstname", "newFirstName");
        jo.put("lastname", "newLastName");
        jo.put("username", "newFirstName_lastName");
        jo.put("password", "newPassword");

        JSONObject jo1 = new JSONObject();
        jo1.put("id", 1);
        jo1.put("firstname", "FirstName");
        jo1.put("lastname", "lastName");
        jo1.put("username", "firstName_lastName");
        jo1.put("password", "password");

        this.mvc.perform(post("http://127.0.0.1:8082/api/v1/account/").contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(jo1)))
                .andExpect(status().isOk());

        this.mvc.perform(put("http://127.0.0.1:8082/api/v1/account/id=1").contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(jo)))
                .andExpect(status().isOk());

        String result = "{\"id\":" + jo.get("id").toString() + ",\"firstname\":\"" + jo.get("firstname") + "\",\"lastname\":\"" + jo.get("lastname") + "\",\"username\":\"" + jo.get("username") + "\",\"password\":\"" + jo.get("password") + "\"}";

        this.mvc.perform(get("http://127.0.0.1:8082/api/v1/account/id=1")).andExpect(status().isOk())
                .andExpect(content().json(result));

        this.mvc.perform(delete("http://127.0.0.1:8082/api/v1/account/id=1")).andExpect(status().isOk())
                .andExpect(status().isOk());
    }

    @org.junit.Test
    public void deleteAccountById() throws Exception {

        JSONObject jo1 = new JSONObject();
        jo1.put("id", 1);
        jo1.put("firstname", "otherFirstName");
        jo1.put("lastname", "otherLastName");
        jo1.put("username", "otherFirstName_lastName");
        jo1.put("password", "otherPassword");

        this.mvc.perform(post("http://127.0.0.1:8082/api/v1/account/").contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(jo1)))
                .andExpect(status().isOk());

        JSONObject jo2 = new JSONObject();
        jo2.put("id", 2);
        jo2.put("firstname", "FirstName");
        jo2.put("lastname", "lastName");
        jo2.put("username", "firstName_lastName");
        jo2.put("password", "password");
        jo2.get("id");


        this.mvc.perform(post("http://127.0.0.1:8082/api/v1/account/").contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(jo2)))
                .andExpect(status().isOk());

        this.mvc.perform(delete("http://127.0.0.1:8082/api/v1/account/id=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        this.mvc.perform(delete("http://127.0.0.1:8082/api/v1/account/id=2")).andExpect(status().isOk())
                .andExpect(status().isOk());

        this.mvc.perform(get("http://127.0.0.1:8082/api/v1/account/")).andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }
}
