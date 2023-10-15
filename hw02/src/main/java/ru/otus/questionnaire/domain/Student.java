package ru.otus.questionnaire.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {

    private String name;

    private String surname;

    public String getFullName() {
        return String.format("%s %s", name, surname);
    }

}

