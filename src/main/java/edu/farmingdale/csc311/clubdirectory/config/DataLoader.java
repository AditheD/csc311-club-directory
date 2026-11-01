package edu.farmingdale.csc311.clubdirectory.config;

import edu.farmingdale.csc311.clubdirectory.club.Club;
import edu.farmingdale.csc311.clubdirectory.club.ClubRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Adds starter clubs when the database is empty.
 */
@Configuration
public class DataLoader {
    /**
     * Creates a startup task that loads the three starter clubs.
     *
     * @param repository the club repository
     * @return the startup task
     */
    @Bean
    CommandLineRunner loadStarterClubs(ClubRepository repository) {
        return args -> {
            repository.findAll().stream()
                    .filter(club -> "Robotics Club".equals(club.getName()))
                    .forEach(repository::delete);

            if (repository.count() == 0) {
                repository.save(new Club(
                        "Computer Science Club",
                        "Academic",
                        "Tuesday",
                        "Lupton Hall 120"
                ));

                repository.save(new Club(
                        "Chess Club",
                        "Games",
                        "Wednesday",
                        "Campus Center 205"
                ));

                repository.save(new Club(
                        "Running Club",
                        "Sports",
                        "Friday",
                        "Nold Hall"
                ));
            }
        };
    }
}
