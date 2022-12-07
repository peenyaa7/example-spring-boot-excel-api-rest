package es.peenyaa7.examplespringbootmicrosoftexcel.models;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Owner {
    
    private Long id;

    private String name;

    private String surname;

    private String dni;

    private List<Lovebird> lovebirds;

    

}
