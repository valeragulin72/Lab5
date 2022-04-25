package Interaction;

import java.util.Scanner;


public class ScriptInteraction implements UserInteraction {

    private final Scanner scanner;

    public ScriptInteraction(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void print(boolean newLine, String message) {
        new ConsoleInteraction().print(newLine, message);
    }

    @Override
    public String getData() {
        return scanner.nextLine();
    }
}
