package Commands;

import Interaction.UserInteraction;
import Movie.*;
import java.util.Hashtable;


public class CountGreaterThanMpaaRating implements Command{
    private final UserInteraction interaction;
    private final String argument;

    public CountGreaterThanMpaaRating(UserInteraction interaction, String[] commandArgs) {
        this.interaction = interaction;
        this.argument = commandArgs[0];
    }

    @Override
    public boolean execute(Hashtable<String, Movie> collection) throws Exception {
        int count = 0;
        try {
            for (Movie movie : collection.values()) {
                if (movie.getMpaaRating().ordinal() > MpaaRating.getByName(argument).ordinal()) {
                    count++;
                }
            }
            interaction.print(true, "Count of movies with greater MPAA rating is: " + count + ".");
        } catch (NullPointerException e) {
            interaction.print(true, "No such MPAA rating.");
        }
        return true;
    }
}
