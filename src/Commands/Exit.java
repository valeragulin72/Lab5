package Commands;

import Movie.Movie;
import java.util.Hashtable;


public class Exit implements Command{

    public Exit() {}

    @Override
    public boolean execute(Hashtable<String, Movie> collection) throws Exception {
        return false;
    }
}
