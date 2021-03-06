package Commands;

import Interaction.UserInteraction;
import Movie.Movie;
import java.util.Hashtable;
import java.util.Set;


public class UpdateId implements Command, IdUsing {


    private final UserInteraction interaction;
    private final Hashtable<String, Movie> collection;
    private final String argument;


    public UpdateId(UserInteraction interaction, String[] commandArgs, Hashtable<String, Movie> collection) {
        this.interaction = interaction;
        this.argument = commandArgs[0];
        this.collection = collection;
    }


    @Override
    public boolean execute(Hashtable<String, Movie> collection) throws Exception {
        search(interaction, collection, argument);
        return true;
    }


    @Override
    public void remove(UserInteraction interaction, Hashtable<String, Movie> collection, String argument) throws Exception {
        Set<String> keys = collection.keySet();
        for (String key : keys) {
            if (collection.get(key).getId() == Integer.parseInt(argument)) {
                String[] commandArgs = new String[1];
                commandArgs[0] = key;
                Insert insert = new Insert(interaction, false, Integer.parseInt(argument), collection, commandArgs);
                insert.execute(collection);
                break;
            }
        }
    }
}
