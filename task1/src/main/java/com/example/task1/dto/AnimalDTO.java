package com.example.task1.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
public class AnimalDTO {
    private LocalDate birthday;
    private String gender;
    private String name;
    private Integer type;
}
