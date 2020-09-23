package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.domain.Movie;
import ru.netology.repository.MovieRepository;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovieManagerTest {

    @Mock
    private MovieRepository repository;
    @InjectMocks
    private MovieManager manager = new MovieManager(repository);
    @InjectMocks
    private MovieManager manager2= new MovieManager(repository,5);

    private Movie movie1 = new Movie(1,"13-й воин", "боевик", "url_1");
    private Movie movie2 = new Movie(2,"9-ые врата", "триллер", "url_2");
    private Movie movie3 = new Movie(3,"Догма", "комедия", "url_3");
    private Movie movie4 = new Movie(4,"Генетическа опера", "мюзикл", "url_4");
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

        Movie[] returned = new Movie[]{movie1,movie2,movie3,movie5,movie6,movie7,movie8, movie9,movie10,movie11};
        doReturn(returned).when(repository).findAll();
        doNothing().when(repository).removeById(idToRemove);

        manager.removeById(idToRemove);
        Movie[] actual = manager.getAll();
        Movie[] expected = new Movie[]{movie11,movie10,movie9,movie8,movie7,movie6,movie5,movie3,movie2,movie1};

        assertArrayEquals(expected, actual);
        verify(repository).removeById(idToRemove);
    }

    @Test
    public void shouldNotRemoveIfNotExists() {
        int idToRemove = 17;

        Movie[] returned = new Movie[]{movie1,movie2,movie3,movie4,movie5,movie6,movie7,movie8, movie9,movie10,movie11};
        doReturn(returned).when(repository).findAll();
        doNothing().when(repository).removeById(idToRemove);
        manager.removeById(idToRemove);

        Movie[] actual = manager.getAll();
        Movie[] expected = new Movie[]{movie11,movie10,movie9,movie8,movie7,movie6,movie5,movie4,movie3,movie2,movie1};

        assertArrayEquals(expected, actual);
        verify(repository).removeById(idToRemove);

    }

    @Test
    void shouldShowFeedDefaultLengthMoreMovies() {
        Movie[] returned = new Movie[]{movie1,movie2,movie3,movie4,movie5,movie6,movie7,movie8, movie9,movie10,movie11};
        doReturn(returned).when(repository).findAll();

        Movie[] expected = new Movie[]{movie11,movie10,movie9,movie8,movie7,movie6,movie5,movie4,movie3,movie2};
        Movie[] actual = manager.showFeed();
        assertArrayEquals(expected, actual);

        verify(repository,atLeastOnce()).findAll();
    }

    @Test
    void shouldShowFeedDefaultLengthExactlyMovies() {
        Movie[] returned = new Movie[]{movie1,movie2,movie3,movie4,movie5,movie6,movie7,movie8, movie9,movie10};
        doReturn(returned).when(repository).findAll();

        Movie[] expected = new Movie[]{movie10,movie9,movie8,movie7,movie6,movie5,movie4,movie3,movie2,movie1};
        Movie[] actual = manager.showFeed();
        assertArrayEquals(expected, actual);

        verify(repository,atLeastOnce()).findAll();
    }

    @Test
    void shouldShowFeedDefaultLengthNotEnoughMovies() {
        Movie[] returned = new Movie[]{movie1,movie2,movie3,movie4,movie5,movie6,movie9,movie10};
        doReturn(returned).when(repository).findAll();

        Movie[] expected = new Movie[]{movie10,movie9,movie6,movie5,movie4,movie3,movie2,movie1};
        Movie[] actual = manager.showFeed();
        assertArrayEquals(expected, actual);

        verify(repository,atLeastOnce()).findAll();
    }

    @Test
    void shouldShowFeedCustomLengthMoreMovies() {
        Movie[] returned = new Movie[]{movie1,movie2,movie3,movie4,movie5,movie6};
        doReturn(returned).when(repository).findAll();

        Movie[] expected = new Movie[]{movie6,movie5,movie4,movie3,movie2};
        Movie[] actual = manager2.showFeed();
        assertArrayEquals(expected, actual);

        verify(repository,atLeastOnce()).findAll();
    }

    @Test
    void shouldShowFeedCustomLengthExactlyMovies() {
        Movie[] returned = new Movie[]{movie1,movie2,movie3,movie4,movie5};
        doReturn(returned).when(repository).findAll();

        Movie[] expected = new Movie[]{movie5,movie4,movie3,movie2,movie1};
        Movie[] actual = manager2.showFeed();
        assertArrayEquals(expected, actual);
        verify(repository,atLeastOnce()).findAll();
    }

    @Test
    void shouldShowFeedCustomLengthNotEnoughMovies() {
        Movie[] returned = new Movie[]{movie1,movie2,movie3};
        doReturn(returned).when(repository).findAll();

        Movie[] expected = new Movie[]{movie3,movie2,movie1};
        Movie[] actual = manager2.showFeed();
        assertArrayEquals(expected, actual);
        verify(repository,atLeastOnce()).findAll();
    }
}