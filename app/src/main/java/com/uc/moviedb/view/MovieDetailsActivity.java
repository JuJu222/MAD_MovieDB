package com.uc.moviedb.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.uc.moviedb.R;
import com.uc.moviedb.helper.Const;
import com.uc.moviedb.model.Genre;
import com.uc.moviedb.model.Movies;
import com.uc.moviedb.model.NowPlaying;
import com.uc.moviedb.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Intent intent = getIntent();
        NowPlaying.Results movie = intent.getParcelableExtra("movie");
        ArrayList<Integer> genreIds = intent.getIntegerArrayListExtra("genreIds");

        MovieViewModel movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        ImageView movieDetailsImageView = findViewById(R.id.movieDetailsImageView);
        TextView movieDetailsTitleTextView = findViewById(R.id.movieDetailsTitleTextView);
        TextView movieDetailsReleaseDateTextView = findViewById(R.id.movieDetailsReleaseDateTextView);
        TextView movieDetailsVoteAverageTextView = findViewById(R.id.movieDetailsVoteAverageTextView);
        TextView movieDetailsOverviewTextView = findViewById(R.id.movieDetailsOverviewTextView);
        TextView movieDetailsGenresTextView = findViewById(R.id.movieDetailsGenresTextView);
        TextView movieDetailsFullReleaseDateTextView = findViewById(R.id.movieDetailsFullReleaseDateTextView);

        setTitle("Movie Details");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Glide.with(this).load(Const.IMG_URL + movie.getBackdrop_path()).into(movieDetailsImageView);
        movieDetailsTitleTextView.setText(movie.getTitle());
        movieDetailsReleaseDateTextView.setText(movie.getRelease_date().substring(0, 4));
        String vote = "(" + movie.getVote_count() + ") " + movie.getVote_average();
        movieDetailsVoteAverageTextView.setText(vote);
        movieDetailsOverviewTextView.setText(movie.getOverview());
        movieDetailsFullReleaseDateTextView.setText(movie.getRelease_date());

        movieViewModel.getGenres(genreIds);
        movieViewModel.getResultGetGenres().observe(this, new Observer<List<Genre.Genres>>() {
            @Override
            public void onChanged(List<Genre.Genres> genres) {
                StringBuilder genreToSet = new StringBuilder();
                for (int i = 0; i < genres.size(); i++) {
                    if (i != genres.size() - 1) {
                        genreToSet.append(genres.get(i).getName() + ", ");
                    } else {
                        genreToSet.append(genres.get(i).getName());
                    }
                }

                movieDetailsGenresTextView.setText(genreToSet);
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}