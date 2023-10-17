package ru.otus.questionnaire.dao.dto;

import com.opencsv.bean.CsvBindAndSplitByPosition;
import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;
import ru.otus.questionnaire.domain.Answer;
import ru.otus.questionnaire.domain.Question;

import java.util.ArrayList;
import java.util.List;

@Data
public class QuestionDto {

    @CsvBindByPosition(position = 0)
    private String question;

    @CsvBindAndSplitByPosition(position = 1, splitOn = "\\|", converter = AnswerCsvConverter.class,
            collectionType = ArrayList.class, elementType = Answer.class)
    private List<Answer> answers;

    public Question toDomainObject() {
        return new Question(question, answers);
    }
}
