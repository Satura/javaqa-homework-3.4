package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Movie;

import static org.junit.jupiter.api.Assertions.*;

class MovieManagerTest {

    private MovieManager manager = new MovieManager();
    private MovieManager manager2= new MovieManager(5);

    private Movie movie1 = new Movie(1,"13-й воин", "боевик", "url_1");
    private Movie movie2 = new Movie(2,"9-ые врата", "триллер", "url_2");
    private Movie movie3 = new Movie(3,"Догма", "комедия", "url_3");
    private Movie movie4 = new Movie(4,"Генетическая опера", "мюзикл", "url_4");
    private Movie movie5 = new Movie(5,"Белоснежка. Страшная сказка", "ужасы", "url_5");
    private Movie movie6 = new Movie(6,"13-й этаж", "боевик", "url_6");
    private Movie movie7 = new Movie(7,"Мертвец", "боевик", "url_7");
    private Movie movie8 = new Movie(8,"Семейка Аддамс", "комедия", "url_8");
    private Movie movie9 = new Movie(9,"Железное небо", "комедия", "url_9");
    private Movie movie10 = new Movie(10,"За бортом", "комедия", "url_10");
    private Movie movie11 = new Movie(11,"Смерть ей к лицу", "комедия", "url_11");



    @BeforeEach
    public void setUp() {
        manager.add(movie1);
        manager.add(movie2);
        manager.add(movie3);
        manager.add(movie4);
        manager.add(movie5);
        manager.add(movie6);
        manager.add(movie7);
        manager.add(movie8);
        manager.add(movie9);
        manager.add(movie10);
        manager.add(movie11);

        manager2.add(movie1);
        manager2.add(movie2);
        manager2.add(movie3);
        manager2.add(movie4);
        manager2.add(movie5);
        manager2.add(movie6);

    }

    @Test
    void shouldRemoveIfExists() {
        int idToRemove = 4;
        manager.removeById(idToRemove);

        Movie[] actual = manager.getAll();
        Movie[] expected = new Movie[]{movie11,movie10,movie9,movie8,movie7,movie6,movie5,movie3,movie2,movie1};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotRemoveIfNotExists() {
        int idToRemove = 17;

        manager.removeById(idToRemove);

        Movie[] actual = manager.getAll();
        Movie[] expected = new Movie[]{movie11,movie10,movie9,movie8,movie7,movie6,movie5,movie4,movie3,movie2,movie1};

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldShowFeedDefaultLengthMoreMovies() {
        Movie[] expected = new Movie[]{movie11,movie10,movie9,movie8,movie7,movie6,movie5,movie4,movie3,movie2};
        Movie[] actual = manager.showFeed();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldShowFeedDefaultLengthExactlyMovies() {
        manager.removeById(11);

        Movie[] expected = new Movie[]{movie10,movie9,movie8,movie7,movie6,movie5,movie4,movie3,movie2,movie1};
        Movie[] actual = manager.showFeed();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldShowFeedDefaultLengthNotEnoughMovies() {
        manager.removeById(1);
        manager.removeById(7);
        manager.removeById(4);
        manager.removeById(9);
        Movie[] expected = new Movie[]{movie11,movie10,movie8,movie6,movie5,movie3,movie2};
        Movie[] actual = manager.showFeed();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldShowFeedCustomLengthMoreMovies() {
        Movie[] expected = new Movie[]{movie6,movie5,movie4,movie3,movie2};
        Movie[] actual = manager2.showFeed();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldShowFeedCustomLengthExactlyMovies() {
        manager2.removeById(6);
        Movie[] expected = new Movie[]{movie5,movie4,movie3,movie2,movie1};
        Movie[] actual = manager2.showFeed();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldShowFeedCustomLengthNotEnoughMovies() {
        manager2.removeById(2);
        manager2.removeById(4);
        manager2.removeById(6);
        Movie[] expected = new Movie[]{movie5,movie3,movie1};
        Movie[] actual = manager2.showFeed();
        assertArrayEquals(expected, actual);
    }
}