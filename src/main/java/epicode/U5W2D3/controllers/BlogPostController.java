package epicode.U5W2D3.controllers;

import epicode.U5W2D3.entities.BlogPost;
import epicode.U5W2D3.services.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/blogPost")

public class BlogPostController {
    @Autowired
    private BlogPostService blogPostService;

    //Get All BlogPosts
    @GetMapping
    public Page<BlogPost> getAllBlogposts(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size,
                                          @RequestParam(defaultValue = "id") String orderBy
                                          )
    {
        return this.blogPostService.getBlogPosts(page, size, orderBy);
    }

    //Get BlogPost By ID
    @GetMapping("/{id}")
    public BlogPost findById(@PathVariable UUID id) {
        return this.blogPostService.findById(id);
    }

    //Save BlogPost
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BlogPost saveBlogPost(@RequestBody BlogPost newBlogPost) {return this.blogPostService.saveBlogPost(newBlogPost);}

    @PutMapping("/{id}")
    public BlogPost findByAndUpdate(@PathVariable UUID id, @RequestBody BlogPost updatedBlogPost){
        return this.blogPostService.findByIdAndUpdate(id, updatedBlogPost);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID id) {this.blogPostService.findByIdAndDelete(id);}
}
