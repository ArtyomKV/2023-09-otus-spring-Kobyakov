package ru.otus.questionnaire.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.questionnaire.service.TestRunnerService;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationEventsCommands {

    private final TestRunnerService testService;

    private boolean permission;

    @ShellMethod(value = "Personal data processing permission command", key = {"p", "permission"})
    public String personalDataProcessingPermission(@ShellOption(defaultValue = "true") boolean permission) {
        this.permission = permission;
        return "Добро пожаловать!";
    }

    @ShellMethod(value = "Run test command", key = {"r", "run"})
    @ShellMethodAvailability(value = "isPublishEventCommandAvailable")
    public void runTest() {
        testService.run();
    }

    private Availability isPublishEventCommandAvailable() {
        return permission ? Availability.available() : Availability
                .unavailable("Сначала дайте разрешение на обработку персональных данных");
    }
}