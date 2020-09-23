package ru.netology.manager;

import ru.netology.domain.Movie;
import ru.netology.repository.MovieRepository;

public class MovieManager {

    private MovieRepository repository;
    int feedLength = 10;

    public MovieManager(MovieRepository repository){this.repository = repository;}
    public MovieManager(MovieRepository repository, int feedLength){
        this.repository = repository;
        this.feedLength = feedLength;
    }

    public void add (Movie movie){
        repository.save(movie);
    }

    public Movie[] getAll() {
        Movie[] result = new Movie[repository.findAll().length];
        for (int i = 0; i < result.length; i++) {
            int index = repository.findAll().length - i - 1;
            result[i] = repository.findAll()[index];
        }
        return result;
    }

    public void removeById(int id) {
        repository.removeById(id);
    }

    public Movie[] showFeed(){
        if (repository.findAll().length < feedLength) {feedLength = repository.findAll().length;}
        Movie[] feed = new Movie[feedLength];
        Movie[] tmp = getAll();
        System.arraycopy(tmp,0,feed,0,feedLength);
        return feed;
    }

}
