package mg.unidev.app.entities;




import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "versement")
@NoArgsConstructor
@SuperBuilder @ToString
public class Versement extends Operation {
}
