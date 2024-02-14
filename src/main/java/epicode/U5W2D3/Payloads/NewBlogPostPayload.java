package epicode.U5W2D3.Payloads;

import lombok.Getter;

import java.util.UUID;

 @Getter
public class NewBlogPostPayload {
    private UUID authorId;
    private String category;
    private String content;
    private int readTime;
    private String cover;
    private String title;
}
