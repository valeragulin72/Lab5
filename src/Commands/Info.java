package Commands;

import Movie.Movie;
import Interaction.UserInteraction;
import java.time.LocalDateTime;
import java.util.Hashtable;


public class Info implements Command {

    private final UserInteraction interaction;
    private final LocalDateTime initDate;

    public Info(UserInteraction interaction, LocalDateTime initDate) {
        this.interaction = interaction;
        this.initDate = initDate;
    }

    @Override
    public boolean execute(Hashtable<String, Movie> collection) {
        interaction.print(true, "Information about the collection: \n" +
                "Type: " + Movie.class.getName() + "\n" +
                "Initialization date: " + initDate + "\n" +
                "Amount of elements: " + collection.size() + "\n");
        return true;
    }
}
