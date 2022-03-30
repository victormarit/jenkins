import fr.epsi.Movie;
import fr.epsi.MovieFactory;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.List;

public class MovieFactoryTest extends TestCase {

    private List<String> movieCsv;
    private static List<Movie> movies;

    @BeforeAll
    protected void setUp() {
        movieCsv = new ArrayList<>() {{ add("s1|Movie|Dick Johnson Is Dead|Kirsten Johnson||United States|September 25 2021|2020|PG-13|90 min|Documentaries|As her father nears the end of his life filmmaker Kirsten Johnson stages his death in inventive and comical ways to help them both face the inevitable."); }};
        MovieFactory movieFactory = MovieFactory.getInstance();
        movies = movieFactory.createMovies(movieCsv);
    }

    @Test
    public void testMovies(){
        assertEquals(1, movies.size());
        assertEquals("Dick Johnson Is Dead",  movies.get(0).getTitle());
    }


}
