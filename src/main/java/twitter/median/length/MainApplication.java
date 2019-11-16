package twitter.median.length;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main method - tells Spring to instantiate this class as the main application class (useful later for setting custom configurations)
 */
@SpringBootApplication()
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}