package Commands;

import Interaction.UserInteraction;
import Movie.*;
import java.util.Hashtable;
import java.time.*;
import java.util.Objects;

public class Insert implements Command{


    private final UserInteraction interaction;
    private final boolean fromScript;
    private final int update;
    private final Hashtable<String, Movie> collection;
    private final String argument;


    public Insert(UserInteraction interaction, boolean fromScript, int update, Hashtable<String, Movie> collection, String[] commandArgs) {
        this.interaction = interaction;
        this.fromScript = fromScript;
        this.update = update;
        this.collection = collection;
        this.argument = commandArgs[0];
    }


    @Override
    public boolean execute(Hashtable<String, Movie> collection) throws Exception{
        if ((update == 0) && (!fromScript)) {
            interaction.print(true, "Inserting...");
        }
        if (update == 0) {
            if (argument != " ") {
                if (collection.containsKey(argument)) {
                    interaction.print(true, "This key is already used.");
                } else {
                    collection.put(argument, createMovie(interaction, fromScript, update, collection));
                    if ((update == 0) && (!fromScript)) {
                        interaction.print(true, "Element insert successful.");
                    }
                }

            } else {
                interaction.print(true, "Key can't be empty line.");
            }
        } else {
            collection.put(argument, createMovie(interaction, fromScript, update, collection));
        }
        return true;
    }


    public Movie createMovie(UserInteraction interaction, boolean fromScript, int update, Hashtable<String, Movie> collection) {
        Movie movie = new Movie();
        if (update == 0) {
            movie.setId();
        } else {
            movie.setId(update);
        }

        movie.setMovieCreationDate();
        addName(movie, interaction, fromScript);
        addCoordinates(movie, interaction, fromScript);
        addOscarsCount(movie, interaction, fromScript);
        addGoldenPalmCount(movie, interaction, fromScript);
        addGenre(movie, interaction, fromScript);
        addMpaaRating(movie, interaction, fromScript);
        createScreenwriter(movie, interaction, fromScript);
        for (Movie movie1 : collection.values()) {
            if (Objects.equals(movie.getId(), movie1.getId())) {
                interaction.print(true, "This id is already used.");
            }
        }
        return movie;
    }

    public void addName(Movie movie, UserInteraction interaction, boolean fromScript) {
        boolean result = false;
        while (!result) {
            if (!fromScript) {
                interaction.print(false, "Enter the movie name: ");
            }
            try {
                movie.setName(interaction.getData());
            } catch (Exception e) {
                interaction.print(true, "Movie name can't be empty line! Re-enter name.");
                continue;
            }
            result = true;
        }
    }

    public void addCoordinates(Movie movie, UserInteraction interaction, boolean fromScript) {
        boolean result = false;
        while (!result) {
            if(!fromScript) {
                interaction.print(false, "Enter the coordinates separated by a space: ");
            }
            String[] xy = interaction.getData().split(" ");
            Float x;
            double y;
            try {
                x = Float.parseFloat(xy[0]);
                y = Double.parseDouble(xy[1]);
            } catch (Exception e) {
                interaction.print(true, "Coordinates must be numbers! Re-enter coordinates.");
                continue;
            }
            try {
                movie.setCoordinates(x,y);
            } catch (Exception e) {
                interaction.print(true, "Incorrect coordinates (x can't be more than 285)! Re-enter coordinates.");
                continue;
            }
            result = true;
        }
    }

    public void addOscarsCount(Movie movie, UserInteraction interaction, boolean fromScript) {
        boolean result = false;
        while(!result) {
            if(!fromScript) {
                interaction.print(false, "Enter count of Oscars: ");
            }
            try {
                movie.setOscarsCount(Integer.parseInt(interaction.getData()));
            } catch (NumberFormatException e) {
                interaction.print(true, "Count of Oscars must be integer! Re-enter count.");
                continue;
            } catch (Exception e) {
                interaction.print(true, "Incorrect count (it must be more than 0)! Re-enter count.");
                continue;
            }
            result = true;
        }
    }

    public void addGoldenPalmCount(Movie movie, UserInteraction interaction, boolean fromScript) {
        boolean result = false;
        while(!result) {
            if(!fromScript) {
                interaction.print(false, "Enter count of Golden palms: ");
            }
            try {
                movie.setGoldenPalmCount(Integer.parseInt(interaction.getData()));
            } catch (NumberFormatException e) {
                interaction.print(true, "Count of Golden palms must be integer! Re-enter count.");
                continue;
            } catch (Exception e) {
                interaction.print(true, "Incorrect count (it must be more than 0)! Re-enter count.");
                continue;
            }
            result = true;
        }
    }


    public void addGenre(Movie movie, UserInteraction interaction, boolean fromScript) {
        boolean result = false;
        while(!result) {
            if(!fromScript) {
                interaction.print(false, "Enter one of movie genres 'western', 'comedy', 'musical': ");
            }
            try {
                movie.setGenre(MovieGenre.getByName(interaction.getData()));
            } catch (Exception e) {
                interaction.print(true, "Incorrect genre! Re-enter genre.");
                continue;
            }
            result = true;
        }
    }

    public void addMpaaRating(Movie movie, UserInteraction interaction, boolean fromScript) {
        boolean result = false;
        while(!result) {
            if(!fromScript) {
                interaction.print(false, "Enter one of MPAA ratings 'G', 'PG', 'PG-13', 'NC-17': ");
            }
            try {
                movie.setMpaaRating(MpaaRating.getByName(interaction.getData()));
            } catch (Exception e) {
                interaction.print(true, "Incorrect MPAA rating! Re-enter rating.");
                continue;
            }
            result = true;
        }
    }

    public void createScreenwriter(Movie movie, UserInteraction interaction, boolean fromScript) {
        Person screenwriter = new Person();
        addPerName(screenwriter, interaction, fromScript);
        addBirthday(screenwriter, interaction, fromScript);
        addEyeColor(screenwriter, interaction, fromScript);
        addHairColor(screenwriter, interaction, fromScript);
        addNationality(screenwriter, interaction, fromScript);
        movie.setScreenwriter(screenwriter);
    }

    public void addPerName(Person screenwriter, UserInteraction interaction, boolean fromScript) {
        boolean result = false;
        while(!result) {
            if(!fromScript) {
                interaction.print(false, "Enter screenwriter name: ");
            }
            try {
                screenwriter.setPerName(interaction.getData());
            } catch (Exception e) {
                interaction.print(true, "Screenwriter name can't be empty line! Re-enter name.");
                continue;
            }
            result = true;
        }
    }

    public void addBirthday(Person screenwriter, UserInteraction interaction, boolean fromScript) {
        boolean result = false;
        while(!result) {
            if(!fromScript) {
                interaction.print(false, "Enter screenwriter's date of birth in the format YYYY-MM-DD: ");
            }
            try {
                screenwriter.setBirthday(LocalDate.parse(interaction.getData()));
            } catch (Exception e) {
                interaction.print(true, "Incorrect date! Re-enter date.");
                continue;
            }
            result = true;
        }
    }

    public void addEyeColor(Person screenwriter, UserInteraction interaction, boolean fromScript) {
        boolean result = false;
        while(!result) {
            if(!fromScript) {
                interaction.print(false, "Enter one of colors 'red', 'black', 'orange', 'white', 'brown' for eyes: ");
            }
            try {
                screenwriter.setEyeColor(Color.getByName(interaction.getData()));
            } catch (Exception e) {
                interaction.print(true, "Incorrect color! Re-enter color.");
                continue;
            }
            result = true;
        }
    }

    public void addHairColor(Person screenwriter, UserInteraction interaction, boolean fromScript) {
        boolean result = false;
        while(!result) {
            if(!fromScript) {
                interaction.print(false, "Enter one of colors 'red', 'black', 'orange', 'white', 'brown' for hair: ");
            }
            try {
                screenwriter.setHairColor(Color.getByName(interaction.getData()));
            } catch (Exception e) {
                interaction.print(true, "Incorrect color! Re-enter color.");
                continue;
            }
            result = true;
        }
    }

    public void addNationality(Person screenwriter, UserInteraction interaction, boolean fromScript) {
        boolean result = false;
        while(!result) {
            if(!fromScript) {
                interaction.print(false, "Enter one of countries 'United Kingdom', 'Germany', 'Vatican', 'Italy', 'North Korea': ");
            }
            try {
                screenwriter.setNationality(Country.getByName(interaction.getData()));
            } catch (Exception e) {
                interaction.print(true, "Incorrect country! Re-enter country.");
                continue;
            }
            result = true;
        }
    }
}
