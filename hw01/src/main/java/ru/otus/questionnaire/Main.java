package ru.otus.questionnaire;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.questionnaire.domain.Question;
import ru.otus.questionnaire.service.QuestionService;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuestionService questionService = context.getBean(QuestionService.class);
        List<Question> questions = questionService.getAllQuestions();
        questionService.printAllQuestions(questions);
        context.close();
    }
}
