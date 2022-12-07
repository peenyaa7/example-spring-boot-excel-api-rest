package es.peenyaa7.examplespringbootmicrosoftexcel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import es.peenyaa7.examplespringbootmicrosoftexcel.classes.Database;
import es.peenyaa7.examplespringbootmicrosoftexcel.models.Lovebird;
import es.peenyaa7.examplespringbootmicrosoftexcel.models.Owner;

@Component
public class DataInitializr implements CommandLineRunner {
    
    @Autowired
    private Database database;

    @Override
    public void run(String... args) throws Exception {
        
        // Creamos los datos

        Lovebird lovebird1 = new Lovebird(1L, "Pepito");
        Lovebird lovebird2 = new Lovebird(2L, "Juanito");
        Owner owner1 = new Owner(1L, "Pepe", "Pérez", "12345678A", List.of(lovebird1, lovebird2));

        
        Lovebird lovebird3 = new Lovebird(3L, "Luisito");
        Owner owner2 = new Owner(2L, "Juan", "García", "87654321B", List.of(lovebird3));
        
        Lovebird lovebird4 = new Lovebird(4L, "Pepito");
        Lovebird lovebird5 = new Lovebird(5L, "Foxito");
        Owner owner3 = new Owner(3L, "Luis", "González", "12345678C", List.of(lovebird4, lovebird5));

        // "Persistimos" los datos

        database.save(owner1);
        database.save(owner2);
        database.save(owner3);
        database.save(lovebird1);
        database.save(lovebird2);
        database.save(lovebird3);
        database.save(lovebird4);
        database.save(lovebird5);

    }

}
