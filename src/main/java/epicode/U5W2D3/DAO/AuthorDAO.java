package epicode.U5W2D3.DAO;

import epicode.U5W2D3.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorDAO extends JpaRepository<Author, UUID> {
    boolean existsByEmail(String email);
}
