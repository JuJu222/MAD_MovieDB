package com.uc.moviedb.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.uc.moviedb.R;
import com.uc.moviedb.adapter.NowPlayingAdapter;
import com.uc.moviedb.model.NowPlaying;
import com.uc.moviedb.viewmodel.MovieViewModel;

public class NowPlayingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);

        RecyclerView recyclerView = findViewById(R.id.nowPlayingRecyclerView);
        MovieViewModel movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        setTitle("Now Playing");
        movieViewModel.getNowPlaying();
        movieViewModel.getResultNowPlaying().observe(this, new Observer<NowPlaying>() {
            @Override
            public void onChanged(NowPlaying nowPlaying) {
                NowPlayingAdapter nowPlayingAdapter = new NowPlayingAdapter(NowPlayingActivity.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(NowPlayingActivity.this));
                recyclerView.setAdapter(nowPlayingAdapter);

                nowPlayingAdapter.setListNowPlaying(nowPlaying.getResults());
            }
        });
    }
}