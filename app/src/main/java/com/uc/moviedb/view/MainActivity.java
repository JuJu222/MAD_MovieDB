package com.uc.moviedb.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputLayout;
import com.uc.moviedb.R;
import com.uc.moviedb.helper.Const;
import com.uc.moviedb.model.Movies;
import com.uc.moviedb.viewmodel.MovieViewModel;

public class MainActivity extends AppCompatActivity {

    private MovieViewModel movieViewModel;
    private Button mainHitButton;
    private TextInputLayout mainMovieIdTextInputLayout;
    private TextView mainShowTextView;
    private ImageView mainImagePosterTextInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieViewModel = new ViewModelProvider(MainActivity.this).get(MovieViewModel.class);
        mainHitButton = findViewById(R.id.mainHitButton);
        mainMovieIdTextInputLayout = findViewById(R.id.mainMovieIdTextInputLayout);
        mainShowTextView = findViewById(R.id.mainShowTextView);
        mainImagePosterTextInputLayout = findViewById(R.id.mainImagePosterTextInputLayout);

        mainHitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String movieId = mainMovieIdTextInputLayout.getEditText().getText().toString().trim();

                if (movieId.isEmpty()) {
                    mainMovieIdTextInputLayout.setError("Please fill this field!");
                } else {
                    mainMovieIdTextInputLayout.setError(null);
                    movieViewModel.getMovieById(movieId);
                    movieViewModel.getResultGetMovieById().observe(MainActivity.this, new Observer<Movies>() {
                        @Override
                        public void onChanged(Movies movies) {
                            if (movies == null) {
                                mainShowTextView.setText("Movie ID is not available in MovieDB!");
                            } else {
                                String title = movies.getTitle();
                                String image_path = movies.getPoster_path().toString();
                                String full_path = Const.IMG_URL + image_path;
                                Glide.with(MainActivity.this).load(full_path).into(mainImagePosterTextInputLayout);
                                mainShowTextView.setText(title);
                            }
                        }
                    });
                }
            }
        });
    }
}