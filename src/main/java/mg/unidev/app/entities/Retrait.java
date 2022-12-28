package mg.unidev.app.entities;



import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "retrait")
@NoArgsConstructor
@SuperBuilder @ToString
public class Retrait extends Operation {
}
