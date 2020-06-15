package com.durov.maks.cinema.model.dto.movie;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MovieRequestDto {
    @NotNull(message = "title can't be null")
    @Size(min = 3, message = "title length should be 3 or more symbols")
    private String title;
    @NotNull(message = "description can't be null")
    @Size(min = 3, message = "description length should be 3 or more symbols")
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
