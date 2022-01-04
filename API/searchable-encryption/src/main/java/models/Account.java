package models;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Table(name = "ACCOUNT")
@Entity
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name of the user cannot be null")
    @Size(min = 3, max = 100, message = "Size of the name user cannot be lower than 3 or bigger than 100")
    @Column(name = "username")
    private String username;

    @NotNull(message = "Password of the user cannot be null")
    @Size(min = 3, max = 100, message = "Size of the password user cannot be lower than 3 or bigger than 100")
    @Column(name = "password")
    private String password;

    @NotNull(message = "Email of the user cannot be null")
    @Size(min = 3, max = 100, message = "Size of the email cannot be lower than 3 or bigger than 100")
    @Column(name = "email")
    private String email;

    @NotNull(message = "Type of the user can't be null")
    @Column(name = "type")
    private String type;

    @Column(name = "status")
    private Status status;

    public Account(String username, String password, String email, String type) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.type = type;
        this.status = Status.ACTIVE;
    }

    public Account(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public Account() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}