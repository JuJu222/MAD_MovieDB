package com.uc.moviedb.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uc.moviedb.R;
import com.uc.moviedb.helper.Const;
import com.uc.moviedb.model.NowPlaying;
import com.uc.moviedb.view.MovieDetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class NowPlayingAdapter extends RecyclerView.Adapter<NowPlayingAdapter.ViewHolder> {
    private Context context;
    private List<NowPlaying.Results> listNowPlaying;
    private List<NowPlaying.Results> getListNowPlaying() {return listNowPlaying;}
    public void setListNowPlaying(List<NowPlaying.Results> listNowPlaying) {
        this.listNowPlaying = listNowPlaying;
    }

    public NowPlayingAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public NowPlayingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_now_playing, parent, false);
        return new NowPlayingAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NowPlayingAdapter.ViewHolder holder, int position) {
        final NowPlaying.Results results = getListNowPlaying().get(position);
        holder.cardNowPlayingTitleTextView.setText(results.getTitle());
        holder.cardNowPlayingOverviewTextView.setText(results.getOverview());
        holder.cardNowPlayingReleaseDateTextView.setText(results.getRelease_date());
        Glide.with(context).load(Const.IMG_URL + results.getPoster_path()).into(holder.cardNowPlayingImageView);
        holder.nowPlayingCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MovieDetailsActivity.class);
                intent.putExtra("movie", results);
                intent.putIntegerArrayListExtra("genreIds", (ArrayList<Integer>) results.getGenre_ids());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getListNowPlaying().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView cardNowPlayingImageView;
        TextView cardNowPlayingTitleTextView;
        TextView cardNowPlayingOverviewTextView;
        TextView cardNowPlayingReleaseDateTextView;
        CardView nowPlayingCardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardNowPlayingImageView = itemView.findViewById(R.id.cardNowPlayingImageView);
            cardNowPlayingTitleTextView = itemView.findViewById(R.id.cardNowPlayingTitleTextView);
            cardNowPlayingOverviewTextView = itemView.findViewById(R.id.cardNowPlayingOverviewTextView);
            cardNowPlayingReleaseDateTextView = itemView.findViewById(R.id.cardNowPlayingReleaseDateTextView);
            nowPlayingCardView = itemView.findViewById(R.id.nowPlayingCardView);
        }
    }
}
