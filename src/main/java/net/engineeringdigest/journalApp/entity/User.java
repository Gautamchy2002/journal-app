package net.engineeringdigest.journalApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
//Ab hum getter setter ko v comment kr rhe hai qki data annotation use kr rhe hai isme getter setter constructor aur bhut kuch ek me hi aa jata hai
//@Getter
//@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    @NonNull
  private String userName;
    private String email;
    private boolean sentimentAnalysis;
    @NonNull
    @JsonIgnore
  private  String password;
@DBRef
    private List<JournalEntry> journalEntries = new ArrayList<>();
private List<String> roles;

//    Ab hum lombok ka getter setter annotation use kr rhe hai isliye hum hume manually getter setter banane ki jrurrat nhi hai isliye comment kr rhe hai
//

//    public LocalDateTime getDate() {
//        return date;
//    }
//
//    public void setDate(LocalDateTime date) {
//        this.date = date;
//    }
//
//    public ObjectId getId() {
//        return id;
//    }
//
//    public void setId(ObjectId id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
}
