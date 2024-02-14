package epicode.U5W2D3.DAO;

import epicode.U5W2D3.entities.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BlogPostDAO extends JpaRepository<BlogPost, UUID> {
}
