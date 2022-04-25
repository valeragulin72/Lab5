package Commands;

import Movie.Movie;
import Interaction.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.Hashtable;
import java.util.Scanner;


public class ExecuteScript implements Command {
    private final UserInteraction interaction;
    private final String argument;
    private final LocalDateTime creationDate;
    private final LocalDateTime initDate;
    private final File file;

    public ExecuteScript(UserInteraction interaction, String[] commandArgs, LocalDateTime creationDate, LocalDateTime initDate, File file) {
        this.interaction = interaction;
        this.argument = commandArgs[0];
        this.creationDate = creationDate;
        this.initDate = initDate;
        this.file = file;
    }


    @Override
    public boolean execute(Hashtable<String, Movie> collection) throws Exception {
        File fileScript = new File(argument);
        Scanner fileScanner;

        try {
            fileScanner = new Scanner(fileScript);
        } catch (FileNotFoundException e) {
            interaction.print(true,"File doesn't exist.");
            return true;
        }
        int lineNum = 1;
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            if (line.equals("executeScript script.txt")) {
                interaction.print(true, "Executing script from another is not available.");
                return true;
            }
            try {
                Command command = CommandExecution.proceedCommand(line, true, new ScriptInteraction(fileScanner), creationDate, initDate, file);
                if (command == null) {
                    continue;
                }
                command.execute(collection);
            } catch (Exception e) {
                interaction.print(true,"An error occurred while executing line " + lineNum + ":\n" + line);
                break;
            }
            lineNum++;
        }
        interaction.print(true, "Execution of script '"+ argument + "' finished successful.");
        return true;
    }
}
