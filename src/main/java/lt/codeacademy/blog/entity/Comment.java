package lt.codeacademy.blog.entity;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String comment;

    private LocalDate createdOn;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "blog_user_id")
    private BlogUser blogUser;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "post_id")
    private Post post;

    // Default constructor
    public Comment() {
    }

    // Constructor with parameters
    public Comment(String comment, LocalDate createdOn, BlogUser blogUser, Post post) {
        this.comment = comment;
        this.createdOn = createdOn;
        this.blogUser = blogUser;
        this.post = post;
    }

    // Method to update the content of the comment
    public Comment updateContent(String content) {
        setComment(content);
        setCreatedOn(LocalDate.now());
        return this;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public BlogUser getBlogUser() {
        return blogUser;
    }

    public void setBlogUser(BlogUser blogUser) {
        this.blogUser = blogUser;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    // Refactored asJson method with null check
    public JsonNode asJson() {
        ObjectMapper mapper = new ObjectMapper();
        String userName = (blogUser != null) ? blogUser.getUserName() : "Unknown User"; // Null check
        String postTitle = (post != null) ? post.getTitle() : "Unknown Post"; // Null check
        return mapper.createObjectNode()
                .put("id", id)
                .put("comment", comment)
                .put("createdOn", createdOn.toString())
                .put("blogUser", userName)
                .put("post", postTitle);
    }

    // Refactored toString method with null check
    @Override
    public String toString() {
        String userName = (blogUser != null) ? blogUser.getUserName() : "Unknown User"; // Null check
        String postTitle = (post != null) ? post.getTitle() : "Unknown Post"; // Null check
        return "Comment{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", createdOn=" + createdOn +
                ", blogUser=" + userName +
                ", post=" + postTitle +
                '}';
    }
}
