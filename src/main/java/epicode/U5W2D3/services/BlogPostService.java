package epicode.U5W2D3.services;

import epicode.U5W2D3.DAO.BlogPostDAO;
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
    private BlogPostDAO blogPostsDAO;


    public Page<BlogPost> getBlogPosts(int pageNumber, int size, String orderBy){
        if (size > 20) size = 20;
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        return blogPostsDAO.findAll(pageable);
    }

    public BlogPost saveBlogPost(BlogPost newBlogPost){
        return blogPostsDAO.save(newBlogPost);
    }

    public BlogPost findById(UUID id){
        return blogPostsDAO.findById(id).orElseThrow(NotFoundException::new);
    }



    public BlogPost findByIdAndUpdate(UUID id, BlogPost updatedBlogPost) {
        BlogPost found = this.findById(id);
        found.setTitle(updatedBlogPost.getTitle());
        found.setCategory(updatedBlogPost.getCategory());
        found.setContent(updatedBlogPost.getContent());
        found.setCover(updatedBlogPost.getCover());
        found.setReadTime(updatedBlogPost.getReadTime());
        return blogPostsDAO.save(found);
    }

    public void findByIdAndDelete(UUID id){
        BlogPost found = this.findById(id);
        blogPostsDAO.delete(found);
    }

}