package ru.netology.manager;

import ru.netology.domain.Movie;

public class MovieManager {
    private Movie[] movies = new Movie[0];
    int feedLength = 10;

    public MovieManager(){}
    public MovieManager(int feedLength){
        this.feedLength = feedLength;
    }

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

    public Movie[] showFeed(){
        if (movies.length < feedLength) {feedLength = movies.length;}
        Movie[] feed = new Movie[feedLength];
        Movie[] tmp = getAll();
        System.arraycopy(tmp,0,feed,0,feedLength);
        return feed;
    }

}
