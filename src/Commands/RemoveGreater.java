package Commands;

import Interaction.UserInteraction;
import Movie.Movie;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;


public class RemoveGreater implements Command, IdUsing {
    private final UserInteraction interaction;
    private final String argument;


    public RemoveGreater(UserInteraction interaction, String[] commandArgs) {
        this.interaction = interaction;
        this.argument = commandArgs[0];
    }

    @Override
    public boolean execute(Hashtable<String, Movie> collection) throws Exception {
        if (!collection.isEmpty()) {
            if (collection.containsKey(argument)) {
                Set<String> keys = collection.keySet();
                ArrayList<String> removable = new ArrayList<>();
                for (String key : keys) {
                    if (collection.get(key).getId() > collection.get(argument).getId()) {
                        removable.add(key);
                    }
                }
                for (String key : removable) {
                    collection.remove(key);
                }
                interaction.print(true, "All necessary elements removed successfully.");
            } else {
                interaction.print(true, "No key found.");
            }
        } else {
            interaction.print(true, "Collection is empty.");
        }
        return true;
    }
}
