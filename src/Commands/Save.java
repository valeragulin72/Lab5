package Commands;

import Interaction.UserInteraction;
import Movie.*;
import java.time.LocalDateTime;
import java.util.Hashtable;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import Xml.Xml;


public class Save implements Command{

    private final UserInteraction interaction;
    private final LocalDateTime creationDate;
    private final File file;

    public Save(UserInteraction interaction, LocalDateTime creationDate, File file) {
        this.interaction = interaction;
        this.creationDate = creationDate;
        this.file = file;
    }

    @Override
    public boolean execute(Hashtable<String, Movie> collection) throws Exception {
        try {
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file));
            fileWriter.write(Xml.toXml(new HashtableInfo(collection, creationDate)));
            fileWriter.flush();
        } catch (Exception e) {
            interaction.print(true,"Saving file failed: " + e.getMessage());
            return true;
        }
        interaction.print(true, "File saved successfully.");
        return true;
    }
}
