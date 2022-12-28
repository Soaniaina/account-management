package mg.unidev.app.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@DiscriminatorValue(value = "Saving Account")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor @SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SavingsAccount extends Account {

    double taux;

}
