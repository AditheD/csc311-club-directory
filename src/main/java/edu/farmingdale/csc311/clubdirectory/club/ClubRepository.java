package edu.farmingdale.csc311.clubdirectory.club;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Provides database operations for campus clubs.
 */
public interface ClubRepository extends JpaRepository<Club, Long> {

    /**
     * Finds clubs by category without considering letter case.
     *
     * @param category the category to find
     * @return clubs belonging to the category
     */
    List<Club> findByCategoryIgnoreCase(String category);
}
