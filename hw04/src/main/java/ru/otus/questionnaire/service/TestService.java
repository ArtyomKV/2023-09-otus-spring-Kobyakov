package ru.otus.questionnaire.service;

import ru.otus.questionnaire.domain.Result;
import ru.otus.questionnaire.domain.Student;

public interface TestService {

    Result doTest(Student student);
}
