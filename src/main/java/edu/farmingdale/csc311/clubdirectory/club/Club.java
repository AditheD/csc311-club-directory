package edu.farmingdale.csc311.clubdirectory.club;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Represents a campus club stored in the Derby database.
 */
@Entity
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "name is required")
    @Size(max = 60)
    private String name;

    @NotBlank(message = "category is required")
    @Size(max = 30)
    private String category;

    @NotBlank(message = "meetingDay is required")
    @Size(max = 20)
    private String meetingDay;

    @Size(max = 40)
    private String room;

    /**
     * Creates an empty club for JPA.
     */
    protected Club() {
    }

    /**
     * Creates a club with its details.
     *
     * @param name the club name
     * @param category the club category
     * @param meetingDay the day the club meets
     * @param room the optional meeting room
     */
    public Club(String name, String category,
                String meetingDay, String room) {
        this.name = name;
        this.category = category;
        this.meetingDay = meetingDay;
        this.room = room;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMeetingDay() {
        return meetingDay;
    }

    public void setMeetingDay(String meetingDay) {
        this.meetingDay = meetingDay;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}