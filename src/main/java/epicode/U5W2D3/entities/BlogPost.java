package epicode.U5W2D3.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@ToString

@Entity
@Table(name = "blog_post")
public class BlogPost {
    @Id
    @GeneratedValue
    private UUID id;
    private String category;
    private String title;
    private String cover;
    private String content;

    @Column(name = "read_time")
    private int readTime;

    @ManyToOne
    @JoinColumn(name = "author")
    private Author author;

    




    public BlogPost(String category, String title, String cover, String content, int readTime, Author author) {
        this.category = category;
        this.title = title;
        this.cover = cover;
        this.content = content;
        this.readTime = readTime;
        this.author = author;
    }
}
