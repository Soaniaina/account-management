package mg.unidev.app.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@DiscriminatorValue(value = "Current Account")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter @Getter @SuperBuilder
public class CurrentAccount extends Account {

    double decouvert;

}
