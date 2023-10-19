package ru.otus.questionnaire.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.questionnaire.domain.Student;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final IOService ioService;

    @Override
    public Student getStudent() {
        String name = ioService.getLineByQuestion("What is your name?");
        String surname = ioService.getLineByQuestion("What is your surname?");
        return new Student(name, surname);
    }
}
