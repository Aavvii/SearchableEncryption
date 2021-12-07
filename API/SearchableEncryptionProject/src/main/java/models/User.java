package models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * This class defines the table "Author" from the database.
 * It has a one-to-many relationship with UploadDocuments table.
 */
@Table(name="CHATUSERS")
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name of the user cannot be null")
    @Size(min = 3, max = 100, message = "Size of the name user cannot be lower than 3 or bigger than 100")
    private String username;

    @NotNull(message = "Password of the user cannot be null")
    @Size(min = 3, max = 100, message = "Size of the password user cannot be lower than 3 or bigger than 100")
    private String password;

    @NotNull(message = "Email of the user cannot be null")
    @Size(min = 3, max = 100, message = "Size of the email cannot be lower than 3 or bigger than 100")
    private String email;

    @NotNull(message = "Type of the user can't be null")
    private String type;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "user")
    private List<Message> messagesList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Message> getMessagesList() {
        return messagesList;
    }

    public void setMessagesList(List<Message> messagesList) {
        this.messagesList = messagesList;
    }
}
