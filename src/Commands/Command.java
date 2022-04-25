package Commands;

import Movie.Movie;
import java.util.Hashtable;

public interface Command {
    boolean execute(Hashtable<String, Movie> collection) throws Exception;
}
