package mg.unidev.app.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import net.bytebuddy.implementation.bind.annotation.Super;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "operationType", discriminatorType = DiscriminatorType.STRING)

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor @SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class Operation implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long numero;
    Date dateOperation;
    double solde;

    // Une operation appartient à un compte
    @ManyToOne
    @JoinColumn(name = "accountCode")
    Account account;

    @PrePersist
    private void prePersist() {
        this.dateOperation = new Date();
    }
}
