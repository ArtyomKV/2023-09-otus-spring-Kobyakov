package ru.otus.questionnaire.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.questionnaire.domain.Student;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final LocalizedIOService ioService;

    @Override
    public Student getStudent() {
        String name = ioService.getLineByLocalizedQuestion("StudentService.input.first.name");
        String surname = ioService.getLineByLocalizedQuestion("StudentService.input.surname.name");
        return new Student(name, surname);
    }
}
