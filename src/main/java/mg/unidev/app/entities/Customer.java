package mg.unidev.app.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor @Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String email;

    /**
     * A client has many account
     * Bidirectional relation with Account Object
     */
    @OneToMany(mappedBy = "customer")
    @ToString.Exclude
    Collection<Account> accounts;

}
