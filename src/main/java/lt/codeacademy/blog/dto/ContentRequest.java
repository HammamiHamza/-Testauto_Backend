package lt.codeacademy.blog.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ContentRequest {
    private Long id;
    private String title;
    private String content;

    // Existing constructor
    public ContentRequest(String newCommentContent, long l) {
        this.id = l;
        this.content = newCommentContent;
    }

    // Default constructor for Jackson
    public ContentRequest() {
    }

    // Constructor with annotations to guide Jackson for deserialization
    @JsonCreator
    public ContentRequest(
            @JsonProperty("id") Long id,
            @JsonProperty("title") String title,
            @JsonProperty("content") String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
