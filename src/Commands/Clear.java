package Commands;

import Movie.Movie;
import Interaction.UserInteraction;
import java.util.Hashtable;


public class Clear implements Command{

    private final UserInteraction interaction;
    public Clear(UserInteraction interaction) {
        this.interaction = interaction;
    }

    @Override
    public boolean execute(Hashtable<String, Movie> collection) throws Exception {
        collection.clear();
        interaction.print(true, "Collection cleared. \n");
        return true;
    }
}
