package movie;

import java.util.List;
import java.util.stream.Collectors;

public class MovieLister {

    MovieFinder finder;

    public MovieLister() {
        finder = new MovieFinder("MovieList.txt");
    }

    public List<String> findMovieByTitle(String title) {
        return finder.findAll().stream().filter(t -> t.contains(title)).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        MovieLister lister = new MovieLister();
        System.out.println(lister.findMovieByTitle("柯南"));
    }

}
