import Commands.*;
import Movie.*;
import java.util.*;
import java.time.LocalDateTime;
import java.io.*;
import Interaction.*;
import Xml.Xml;


public class Lab5 {
    private static Hashtable<String, Movie> collection;
    private static LocalDateTime creationDate;
    private static LocalDateTime initDate;
    private static File file;
    private final static UserInteraction interaction = new ConsoleInteraction();

    public static void main(String[] args) throws Exception {
        interaction.print(false, "\nEnter file name with the extension: ");
        String fileName = interaction.getData();

        if (fileName != null) {
            file = new File(fileName);
            if (!prepare()) {
                return;
            }
            runInteraction(HashtableInfo.getCollection());
        } else {
            interaction.print(true, "File not found or incorrect input.");
        }
    }


    public static void uploadInformation() throws FileNotFoundException, NoSuchFieldException, IllegalAccessException {
        HashtableInfo hashtableInfo = Xml.fromXml(file);

        collection = Objects.requireNonNull(hashtableInfo).getCollection();
        interaction.print(true, collection.toString());
        creationDate = hashtableInfo.getCreationDate();
    }

    public static boolean prepare() {
        try {
            uploadInformation();
            initDate = LocalDateTime.now();
        } catch (FileNotFoundException | NoSuchFieldException | IllegalAccessException | NullPointerException e) {
            interaction.print(true, "Problems with the file. Data hasn't read.");
            creationDate = LocalDateTime.now();
            return false;
        }
        BufferedWriter fileWriter;
        try {
            fileWriter = new BufferedWriter(new FileWriter(file, true));
            fileWriter.close();
        } catch (IOException e) {
            interaction.print(true, "Incorrect filename format or not enough permission.");
            interaction.print(true, "Error: " + e.getMessage());
            return false;
        }
        return true;
    }

    private static void runInteraction(Hashtable<String, Movie> collection) throws Exception{

        interaction.print(true, "Enter 'help' to see the list of available commands: ");
        boolean run = true;
        while (run) {
            interaction.print(false, "\nEnter the command: ");
            String potentialCommand = interaction.getData();
            if (potentialCommand == null) {
                continue;
            }
            Command command = CommandExecution.proceedCommand(potentialCommand, false, interaction, creationDate, initDate, file);
            if (command != null) {
                run = command.execute(collection);
            }
        }
    }
}

