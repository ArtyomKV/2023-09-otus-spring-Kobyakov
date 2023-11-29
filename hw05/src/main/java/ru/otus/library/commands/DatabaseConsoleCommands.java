package ru.otus.library.commands;

import org.h2.tools.Console;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class DatabaseConsoleCommands {

    @ShellMethod(value = "Show database console", key = {"show", "s"})
    public void runApplication() throws Exception {
        Console.main();
    }
}
