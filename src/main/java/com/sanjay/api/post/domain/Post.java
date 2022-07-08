package com.sanjay.api.post.domain;

import com.sanjay.api.post.comment.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/*
A composite unique key is a unique key made up of a combination of columns.
To define a composite unique key, we can add constraints on the table instead of
 a column. JPA helps us achieve this using the @UniqueConstraint annotation.

For single column unique key
@Column(unique=true)
private Long personNumber;

 */

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "posts" , uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "content", nullable = false)
    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments;
}
