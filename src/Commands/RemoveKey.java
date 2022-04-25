package Commands;

import Interaction.UserInteraction;
import Movie.Movie;

import java.util.Hashtable;
import java.util.Set;

public class RemoveKey implements Command {
    private final UserInteraction interaction;
    private final String argument;


    public RemoveKey(UserInteraction interaction, String[] commandArgs) {
        this.interaction = interaction;
        this.argument = commandArgs[0];
    }

    @Override
    public boolean execute(Hashtable<String, Movie> collection) throws Exception {
        if (!collection.isEmpty()) {
            if (collection.containsKey(argument)) {
                collection.remove(argument);
                interaction.print(true, "The element removed successfully.");
            } else {
                interaction.print(true, "Key has not found.");
            }
        } else {
            interaction.print(true, "Collection is empty.");
        }
        return true;
    }
}
