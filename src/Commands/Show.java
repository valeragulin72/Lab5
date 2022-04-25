package Commands;

import Interaction.UserInteraction;
import Movie.Movie;
import java.util.Hashtable;


public class Show implements Command {
    private final UserInteraction interaction;

    public Show(UserInteraction interaction) {
        this.interaction = interaction;
    }

    @Override
    public boolean execute(Hashtable<String, Movie> collection) {
        if (collection.size() == 0) {
            interaction.print(true, "No elements found.");
        } else {
            interaction.print(true, "Total: " + collection.size() +
                    "\nCollection's elements: ");
            for (String key : collection.keySet()) {
                Movie movie = collection.get(key);
                interaction.print(true, key + " = " + movie.toString());
            }
        }
        return true;
    }
}
