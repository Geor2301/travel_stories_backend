package com.gt9.travelstories.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Document(collection = "postmessages")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostMessage {

    @Id
    private String _id;
    private String title;
    private String message;
    private String name;
    private String creator;
    private List<String> tags;
    private String selectedFile;
    private List<String> likes;
    private List<String> comments;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date createdAt;
}
