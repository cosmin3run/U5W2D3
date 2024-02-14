package epicode.U5W2D3.services;

import epicode.U5W2D3.DAO.AuthorDAO;
import epicode.U5W2D3.entities.Author;
import epicode.U5W2D3.exceptions.BadRequestException;
import epicode.U5W2D3.exceptions.NotFoundException;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthorService {
    @Autowired
    private AuthorDAO authorsDAO;



    public List<Author> getAuthors() {return this.authorsDAO.findAll();}

    public Author saveAuthor(Author newAuthor) {

        if(this.authorsDAO.existsByEmail(newAuthor.getEmail())){
            throw new BadRequestException("Author with email " + newAuthor.getEmail() + " already exists");
        } else {
            newAuthor.setAvatar("https://ui-avatars.com/api/?name=" + newAuthor.getName().charAt(0) + "+" + newAuthor.getSurname().charAt(0));
            authorsDAO.save(newAuthor);
            return newAuthor;
        }
    }

    public Author findById(UUID id) {
        return authorsDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Author findByIdAndUpdate(UUID id, Author updatedAuthor){
            Author found = this.findById(id);

                found.setName(updatedAuthor.getName());
                found.setSurname(updatedAuthor.getSurname());
                found.setEmail(updatedAuthor.getEmail());
                found.setDateBirth(updatedAuthor.getDateBirth());
                found.setAvatar(updatedAuthor.getAvatar());
            return authorsDAO.save(found);
    }

    public void findByIdAndDelete(UUID id){
        Author found = this.findById(id);
        authorsDAO.delete(found);
    }

}



