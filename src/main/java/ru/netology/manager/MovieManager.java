package ru.netology.manager;

import ru.netology.domain.Movie;

public class MovieManager {
    private Movie[] movies = new Movie[0];

    public void add (Movie movie){
        int length = movies.length+1;
        Movie[] tmp = new Movie[length];
        System.arraycopy(movies,0,tmp,0,movies.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = movie;
        movies = tmp;
    }

    public Movie[] getAll() {
        Movie[] result = new Movie[movies.length];
        for (int i = 0; i < result.length; i++) {
            int index = movies.length - i - 1;
            result[i] = movies[index];
        }
        return result;
    }

    public void removeById(int id) {
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
