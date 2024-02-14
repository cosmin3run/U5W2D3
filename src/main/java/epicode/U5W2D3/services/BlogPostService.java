package epicode.U5W2D3.services;

import epicode.U5W2D3.DAO.BlogPostDAO;
import epicode.U5W2D3.Payloads.NewBlogPostPayload;
import epicode.U5W2D3.entities.Author;
import epicode.U5W2D3.entities.BlogPost;
import epicode.U5W2D3.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BlogPostService {

    @Autowired
    BlogPostDAO blogPostsDAO;

    @Autowired
    AuthorService authorService;


    public Page<BlogPost> getBlogPosts(int pageNumber, int size, String orderBy){
        if (size > 20) size = 20;
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        return blogPostsDAO.findAll(pageable);
    }

    public BlogPost saveBlogPost(NewBlogPostPayload newBlogPost){
        Author author = authorService.findById(newBlogPost.getAuthorId());
        BlogPost newPost = new BlogPost();
        newPost.setReadTime(newBlogPost.getReadTime());
        newPost.setTitle(newBlogPost.getTitle());
        newPost.setContent(newBlogPost.getContent());
        newPost.setCover(newBlogPost.getCover());
        newPost.setCategory(newBlogPost.getCategory());
        newPost.setAuthor(author);
        return blogPostsDAO.save(newPost);
    }

    public BlogPost findById(UUID id){
        return blogPostsDAO.findById(id).orElseThrow(NotFoundException::new);
    }



    public BlogPost findByIdAndUpdate(UUID id, NewBlogPostPayload updatedBlogPost) {
        BlogPost found = this.findById(id);
        found.setTitle(updatedBlogPost.getTitle());
        found.setCategory(updatedBlogPost.getCategory());
        found.setContent(updatedBlogPost.getContent());
        found.setCover(updatedBlogPost.getCover());
        found.setReadTime(updatedBlogPost.getReadTime());

        if(found.getAuthor().getId() != updatedBlogPost.getAuthorId()){
            Author newAuthor = authorService.findById(updatedBlogPost.getAuthorId());
            found.setAuthor(newAuthor);
        }

        return blogPostsDAO.save(found);
    }

    public void findByIdAndDelete(UUID id){
        BlogPost found = this.findById(id);
        blogPostsDAO.delete(found);
    }

    public List<BlogPost> findByAuthor(UUID id) {
        Author author = authorService.findById(id);
        return blogPostsDAO.findByAuthor(author);
    }

}