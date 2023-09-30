package ru.otus.questionnaire;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.questionnaire.service.QuestionService;

public class Main {

    public static final String SPRING_CONTEXT_XML = "/spring-context.xml";

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(SPRING_CONTEXT_XML);
        QuestionService questionService = context.getBean(QuestionService.class);
        questionService.printAllQuestions();
        context.close();
    }
}
