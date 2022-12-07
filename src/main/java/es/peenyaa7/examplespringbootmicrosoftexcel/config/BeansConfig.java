package es.peenyaa7.examplespringbootmicrosoftexcel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import es.peenyaa7.examplespringbootmicrosoftexcel.classes.Database;

/**
 * Este archivo de configuración es el encargado de registrar los beans que se
 * utilizarán en la aplicación.
 */
@Configuration
public class BeansConfig {
    
    @Bean
    public Database database() {
        return new Database();
    }

}
