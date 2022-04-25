package Commands;

import Interaction.UserInteraction;
import Movie.Movie;
import java.util.Hashtable;


public class SumOfOscarsCount implements Command {
    private final UserInteraction interaction;

    public SumOfOscarsCount(UserInteraction interaction) {
        this.interaction = interaction;
    }

    @Override
    public boolean execute(Hashtable<String, Movie> collection) throws Exception {
        if (!collection.isEmpty()) {
            int count = 0;
            for (Movie movie : collection.values()) {
                count += movie.getOscarsCount();
            }
            interaction.print(true, "Total Oscar's count: " + count + ".");
        } else {
            interaction.print(true, "Collection is empty.");
        }
        return true;
    }

}