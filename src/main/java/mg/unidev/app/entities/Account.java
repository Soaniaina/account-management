package mg.unidev.app.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "accountType", discriminatorType = DiscriminatorType.STRING)

@Getter @Setter @SuperBuilder
@NoArgsConstructor @AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class Account implements Serializable {

    @Id
    String code;
    LocalDateTime createdAt;
    double solde;

    // Many account belongs to a client
    @ManyToOne
    @JoinColumn(name = "customerId")
    Customer customer;

    // Un compte peut subir plusieurs operations
    @OneToMany(mappedBy = "account")
    Collection<Operation> operations;

    @PrePersist
    private void prePersist() {
        //this.code = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
    }
}
