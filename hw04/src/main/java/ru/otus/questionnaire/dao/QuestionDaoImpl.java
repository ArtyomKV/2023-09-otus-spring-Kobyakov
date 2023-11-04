package ru.otus.questionnaire.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.questionnaire.config.FileNameProvider;
import ru.otus.questionnaire.dao.dto.QuestionDto;
import ru.otus.questionnaire.domain.Question;
import ru.otus.questionnaire.exception.QuestionsAccessException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class QuestionDaoImpl implements QuestionDao {
    public static final char CSV_SEPARATOR = ';';

    private final FileNameProvider fileNameProvider;

    @Override
    public List<Question> findAll() {
        return getQuestionsAsResources();
    }

    private List<Question> getQuestionsAsResources() {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream questionsInputStream = classLoader.getResourceAsStream(fileNameProvider.getTestFileName());
        return getQuestionsFromInputStream(questionsInputStream);
    }

    private List<Question> getQuestionsFromInputStream(InputStream questionsInputStream) {
        List<Question> questions;
        try (InputStreamReader streamReader = new InputStreamReader(questionsInputStream, StandardCharsets.UTF_8)) {
            List<QuestionDto> questionDtoList = getDtoList(streamReader);
            questions = convertDtoToObjects(questionDtoList);
        } catch (IOException e) {
            throw new QuestionsAccessException("Can not to get Questions!", e);
        }
        return questions;
    }

    private List<QuestionDto> getDtoList(InputStreamReader streamReader) {
        return new CsvToBeanBuilder<QuestionDto>(streamReader)
                .withType(QuestionDto.class)
                .withSeparator(CSV_SEPARATOR)
                .build()
                .parse();
    }

    private List<Question> convertDtoToObjects(List<QuestionDto> questionDtoList) {
        return questionDtoList.stream()
                .map(QuestionDto::toDomainObject)
                .collect(Collectors.toList());
    }

}