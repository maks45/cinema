package com.durov.maks.cinema.model.dto.cinemahall;

import javax.validation.constraints.NotNull;

public class CinemaHallRequestDto {
    @NotNull(message = "capacity cant be null")
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
