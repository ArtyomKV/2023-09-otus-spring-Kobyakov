package ru.otus.questionnaire.dao.dto;

import com.opencsv.bean.AbstractCsvConverter;
import ru.otus.questionnaire.domain.Answer;

public class AnswerCsvConverter extends AbstractCsvConverter {

    @Override
    public Object convertToRead(String value) {
        String[] args = value.split("\\,");
        return new Answer(args[0], Boolean.parseBoolean(args[1]));
    }
}