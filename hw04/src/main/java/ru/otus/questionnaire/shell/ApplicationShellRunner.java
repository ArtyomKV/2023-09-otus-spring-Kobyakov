package ru.otus.questionnaire.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.questionnaire.service.LocalizationMessageService;
import ru.otus.questionnaire.service.TestRunnerService;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationShellRunner {

    private final TestRunnerService testService;

    private final LocalizationMessageService localizationMessageService;

    private boolean permission;

    @ShellMethod(value = "Personal data processing permission command", key = {"p", "permission"})
    public String personalDataProcessingPermission(@ShellOption(defaultValue = "true") boolean permission) {
        this.permission = permission;
        return localizationMessageService.getLocalizedMessage("ApplicationShellRunner.greetings");
    }

    @ShellMethod(value = "Run test command", key = {"r", "run"})
    @ShellMethodAvailability(value = "wasPermissionGot")
    public void runTest() {
        testService.run();
    }

    private Availability wasPermissionGot() {
        return permission ? Availability.available() : Availability
                .unavailable(localizationMessageService.getLocalizedMessage("ApplicationShellRunner.reason"));
    }
}