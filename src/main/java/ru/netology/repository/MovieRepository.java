package ru.netology.repository;

import ru.netology.domain.Movie;

public class MovieRepository {
    private Movie[] movies = new Movie[0];

    public Movie[] findAll(){
        return  movies;
    }

    public void save(Movie newMovie){
        int length = movies.length+1;
        Movie[] tmp = new Movie[length];
        System.arraycopy(movies,0,tmp,0,movies.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = newMovie;
        movies = tmp;
    }

    public Movie findById(int id){
        Movie result = new Movie();
        for (Movie movie : movies) {
            if (movie.getId() == id) {result = movie;}
        }
        return result;
    }

    public void removeById(int id) {
        boolean isIdExist = false;
        for (Movie m : movies){
            if (m.getId() == id) {
                isIdExist = true;
            }
        }
        if (isIdExist) {
            int length = movies.length - 1;
            Movie[] tmp = new Movie[length];
            int index = 0;
            for (Movie item : movies) {
                if (item.getId() != id) {
                    tmp[index] = item;
                    index++;
                }
            }
            movies = tmp;
        }
    }

    public void removeAll(){
        movies = new Movie[0];
    }
}
