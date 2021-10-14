package com.uc.moviedb.repositories;

import androidx.lifecycle.MutableLiveData;

import com.uc.moviedb.helper.Const;
import com.uc.moviedb.model.Genre;
import com.uc.moviedb.model.Movies;
import com.uc.moviedb.model.NowPlaying;
import com.uc.moviedb.retrofit.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    private static MovieRepository repository;

    private MovieRepository(){}

    public static MovieRepository getInstance() {
        if (repository == null) {
            repository = new MovieRepository();
        }
        return repository;
    }

    public MutableLiveData<Movies> getMovieData(String movieId) {
        final MutableLiveData<Movies> result = new MutableLiveData<>();

        ApiService.endpoint().getMovieById(movieId, Const.API_KEY).enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {

            }
        });

        return result;
    }

    public MutableLiveData<NowPlaying> getNowPlayingData() {
        final MutableLiveData<NowPlaying> result = new MutableLiveData<>();

        ApiService.endpoint().getNowPLaying(Const.API_KEY).enqueue(new Callback<NowPlaying>() {
            @Override
            public void onResponse(Call<NowPlaying> call, Response<NowPlaying> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<NowPlaying> call, Throwable t) {

            }
        });

        return result;
    }

    public MutableLiveData<List<Genre.Genres>> getGenres(List<Integer> ids) {
        final MutableLiveData<List<Genre.Genres>> result = new MutableLiveData<>();

        ApiService.endpoint().getGenres(Const.API_KEY).enqueue(new Callback<Genre>() {
            @Override
            public void onResponse(Call<Genre> call, Response<Genre> response) {
                List<Genre.Genres> temp = new ArrayList<>();
                for (int id : ids) {
                    for (Genre.Genres item : response.body().getGenres()) {
                        if (item.getId() == id) {
                            temp.add(item);
                        }
                    }
                }

                result.setValue(temp);
            }

            @Override
            public void onFailure(Call<Genre> call, Throwable t) {
                System.err.println(t);
            }
        });

        return result;
    }
}
