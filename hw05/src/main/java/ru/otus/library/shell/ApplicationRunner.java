package ru.otus.library.shell;

import org.h2.tools.Console;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.sql.SQLException;

@ShellComponent
public class ApplicationRunner {

    @ShellMethod(value = "Run test command", key = {"r", "run"})
//    @ShellMethodAvailability(value = "wasPermissionGot")
    public void runApplication() throws Exception {
        Console.main();
    }
}
