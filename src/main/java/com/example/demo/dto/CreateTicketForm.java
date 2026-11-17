package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateTicketForm {

    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title can be at most 255 characters")
    private String title;

    @NotBlank(message = "Description is required")
    @Size(max = 2000, message = "Description can be at most 2000 characters")
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
