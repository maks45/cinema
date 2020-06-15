package com.durov.maks.cinema.model.dto.cinemahall;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CinemaHallRequestDto {
    @Min(value = 2, message = "cinema hall capacity should be 2 or more")
    private int capacity;
    @NotNull(message = "description can't be null")
    private String description;

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
