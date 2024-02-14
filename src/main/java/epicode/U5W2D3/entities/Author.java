package epicode.U5W2D3.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CollectionIdJdbcTypeCode;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString

@Entity
@Table(name = "author")

public class Author {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String name;
    private String surname;
    private String email;
    @Column(name = "date_birth")
    private String dateBirth;
    private String avatar;



    public Author(String name, String surname, String email, String dateBirth, String avatar) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.dateBirth = dateBirth;
        this.avatar = avatar;
    }

}
