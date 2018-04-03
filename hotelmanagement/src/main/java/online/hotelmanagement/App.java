package online.hotelmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Startup Class
 *
 * @author: Dilip
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories("online.hotelmanagement.repository")
@EntityScan("online.hotelmanagement.entity")
public class App 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);
    }
}
