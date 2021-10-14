package com.uc.moviedb.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.uc.moviedb.model.Genre;
import com.uc.moviedb.model.Movies;
import com.uc.moviedb.model.NowPlaying;
import com.uc.moviedb.repositories.MovieRepository;

import java.util.List;

public class MovieViewModel extends AndroidViewModel {

    private MovieRepository repository;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        repository = MovieRepository.getInstance();
    }

    //==Begin of viewmodel get movie by id
    private MutableLiveData<Movies> resultGetMovieById = new MutableLiveData<>();
    public void getMovieById(String movieId) {
        resultGetMovieById = repository.getMovieData(movieId);
    }
    public LiveData<Movies> getResultGetMovieById() {
        return resultGetMovieById;
    }
    //==End of viewmodel get movie by id

    //==Begin of viewmodel get now playing
    private MutableLiveData<NowPlaying> resultGetNowPlaying = new MutableLiveData<>();
    public void getNowPlaying(){
        resultGetNowPlaying = repository.getNowPlayingData();
    }
    public LiveData<NowPlaying> getResultNowPlaying(){
        return resultGetNowPlaying;
    }
    //==End of viewmodel get now playing

    //==Begin of viewmodel get genres
    private MutableLiveData<List<Genre.Genres>> resultGetGenres = new MutableLiveData<>();
    public void getGenres(List<Integer> ids){
        resultGetGenres = repository.getGenres(ids);
    }
    public LiveData<List<Genre.Genres>> getResultGetGenres(){
        return resultGetGenres;
    }
    //==End of viewmodel get genres
}
