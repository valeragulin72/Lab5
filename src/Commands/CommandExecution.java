package Commands;

import Movie.Movie;
import Interaction.UserInteraction;
import java.time.LocalDateTime;
import java.io.File;
import java.util.Hashtable;

public abstract class CommandExecution {


    private static Hashtable<String, Movie> collection = new Hashtable<>();



    public static Command proceedCommand(String enteredCommand, boolean fromScript, UserInteraction interaction, LocalDateTime creationDate, LocalDateTime initDate, File file) {
        enteredCommand = enteredCommand.trim();
        String parts[] = enteredCommand.split(" ");
        String command = parts[0];
        String[] commandArgs = new String[parts.length - 1];
        System.arraycopy(parts, 1, commandArgs, 0, commandArgs.length);


        switch (command) {
            case "help":
                return new Help(interaction);
            case "info":
                return new Info(interaction, initDate);
            case "show":
                return new Show(interaction);
            case "insert":
                if (commandArgs.length == 0) {
                    System.out.println("Arguments not found.");
                    return null;
                }
                return new Insert(interaction, fromScript, 0, collection, commandArgs);
            case "update_id":
                if (commandArgs.length == 0) {
                    System.out.println("Arguments not found.");
                    return null;
                }
                return new UpdateId(interaction, commandArgs, collection);
            case "remove_key":
                if (commandArgs.length == 0) {
                    System.out.println("Arguments not found.");
                    return null;
                }
                return new RemoveKey(interaction, commandArgs);
            case "clear":
                return new Clear(interaction);
            case "save":
                return new Save(interaction, creationDate, file);
            case "execute_script":
                if (commandArgs.length == 0) {
                    System.out.println("Arguments not found.");
                    return null;
                }
                return new ExecuteScript(interaction, commandArgs, creationDate, initDate, file);
            case "exit":
                return new Exit();
            case "remove_greater":
                if (commandArgs.length == 0) {
                    System.out.println("Arguments not found.");
                    return null;
                }
                return new RemoveGreater(interaction, commandArgs);
            case "remove_greater_key":
                if (commandArgs.length == 0) {
                    System.out.println("Arguments not found.");
                    return null;
                }
                return new RemoveGreaterKey(interaction, commandArgs);
            case "remove_lower_key":
                if (commandArgs.length == 0) {
                    System.out.println("Arguments not found.");
                    return null;
                }
                return new RemoveLowerKey(interaction, commandArgs);
            case "remove_any_by_golden_palm_count":
                if (commandArgs.length == 0) {
                    System.out.println("Arguments not found.");
                    return null;
                }
                return new RemoveAnyByGoldenPalmCount(interaction, commandArgs);
            case "sum_of_oscars_count":
                return new SumOfOscarsCount(interaction);
            case "count_greater_than_mpaa_rating":
                if (commandArgs.length == 0) {
                    System.out.println("Arguments not found.");
                    return null;
                }
                return new CountGreaterThanMpaaRating(interaction, commandArgs);
            default:
                interaction.print(true,"Command '" + command + "' is not found. Try again. \n" +
                        "To find out the available commands enter 'help'.");
                return null;
        }
    }
}
