package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Movie;

import static org.junit.jupiter.api.Assertions.*;

class MovieRepositoryTest {

    MovieRepository repository = new MovieRepository();
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
    void setUp() {
        repository.save(movie1);
        repository.save(movie2);
        repository.save(movie3);
        repository.save(movie4);
        repository.save(movie5);
        repository.save(movie6);
        repository.save(movie7);
        repository.save(movie8);
        repository.save(movie9);
        repository.save(movie10);
        repository.save(movie11);
    }

    @Test
    void shouldFindAll() {
        Movie[] expected = new Movie[]{movie1,movie2,movie3,movie4,movie5,movie6,movie7,movie8,movie9,movie10, movie11};
        Movie[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSave() {
        repository.save(movie1);
        Movie[] expected = new Movie[]{movie1,movie2,movie3,movie4,movie5,movie6,movie7,movie8,movie9,movie10, movie11,movie1};
        Movie[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindById() {
        Movie expected = movie5;
        Movie actual = repository.findById(5);
        assertEquals(expected, actual);
    }

    @Test
    void shouldRemoveById() {
        repository.removeById(4);
        Movie[] expected = new Movie[]{movie1,movie2,movie3,movie5,movie6,movie7,movie8,movie9,movie10, movie11};
        Movie[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldRemoveByIdNotExist() {
        repository.removeById(14);
        Movie[] expected = new Movie[]{movie1,movie2,movie3,movie4,movie5,movie6,movie7,movie8,movie9,movie10, movie11};
        Movie[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldRemoveAll() {
        repository.removeAll();
        Movie[] expected = new Movie[0];
        Movie[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }
}