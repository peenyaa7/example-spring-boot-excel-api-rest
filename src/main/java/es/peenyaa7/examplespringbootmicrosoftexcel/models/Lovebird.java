package es.peenyaa7.examplespringbootmicrosoftexcel.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Lovebird {
    
    private Long id;

    private String alias;

    public Lovebird(Long id, String alias) {
        this.id = id;
        this.alias = alias;
    }

}
