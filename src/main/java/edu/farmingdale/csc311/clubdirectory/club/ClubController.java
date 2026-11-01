package edu.farmingdale.csc311.clubdirectory.club;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Handles REST requests for the campus club directory.
 */
@RestController
@RequestMapping("/api/clubs")
public class ClubController  {
    private final ClubRepository repository;

/**
 * Creates the controller using constructor injection.
 *
 * @param repository the club repository
 */
public ClubController(ClubRepository repository) {
    this.repository = repository;
}

/**
 * Returns all clubs or clubs in the requested category.
 *
 * @param category an optional category filter
 * @return the matching clubs
 */
@GetMapping
public List<Club> getClubs(
        @RequestParam(name = "category", required = false)
        String category) {

    if (category != null) {
        return repository.findByCategoryIgnoreCase(category);
    }

    return repository.findAll();
}

/**
 * Returns a club by its ID.
 *
 * @param id the club ID
 * @return the matching club
 * @throws ResponseStatusException if the club does not exist
 */
@GetMapping("/{id}")
public Club getClub(@PathVariable("id") Long id) {
    return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No club with id " + id
            ));
}

/**
 * Validates and saves a new club.
 *
 * @param club the submitted club details
 * @return the saved club with its generated ID
 */
@PostMapping
@ResponseStatus(HttpStatus.CREATED)
public Club addClub(@Valid @RequestBody Club club) {
    Club newClub = new Club(
            club.getName(),
            club.getCategory(),
            club.getMeetingDay(),
            club.getRoom()
    );

    return repository.save(newClub);
}
}
