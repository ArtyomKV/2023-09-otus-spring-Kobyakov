package ru.otus.questionnaire;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.questionnaire.service.TestRunnerService;

@ComponentScan
@Configuration
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        TestRunnerService testRunner = context.getBean(TestRunnerService.class);
        testRunner.run();
        context.close();
    }
}
