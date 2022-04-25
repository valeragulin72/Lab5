package Commands;

import Interaction.UserInteraction;
import Movie.Movie;
import java.util.Hashtable;
import java.util.Set;


public class RemoveAnyByGoldenPalmCount implements Command {

    private final UserInteraction interaction;
    private final String argument;


    public RemoveAnyByGoldenPalmCount(UserInteraction interaction, String[] commandArgs) {
        this.interaction = interaction;
        this.argument = commandArgs[0];
    }


    @Override
    public boolean execute(Hashtable<String, Movie> collection) throws Exception {
        boolean count = false;

        Set<String> keys = collection.keySet();
        for (String key : keys) {
            try {
                if (collection.get(key).getGoldenPalmCount() == Integer.parseInt(argument)) {
                    collection.remove(key);
                    count = true;
                    break;
                }
            } catch (NumberFormatException e) {
                interaction.print(true, "Count of Golden palms must be integer!");
                continue;
            }
        }
        if (count) {
            interaction.print(true,"Element with such count of Golden palms was successfully removed.");
        } else {
            interaction.print(true,"Movie with such count of Golden palms is not found.");
        }
        return true;
    }


}
