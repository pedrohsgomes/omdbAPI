package br.com.phsg.omdbapi.client;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import br.com.phsg.omdbapi.dto.Detail;

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder> {

    private List<Detail> mValues;

    private MainActivity context;

    public MovieRecyclerViewAdapter(Context context, List<Detail> items) {
        mValues = items;
        this.context = (MainActivity)context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final Detail detail = mValues.get(position);
        final String title = detail.Title;
        final String imdbId = detail.imdbID;
        final String director = detail.Director;
        final String year = detail.Year;
        holder.mDirectorView.setText(director);
        holder.mTitleView.setText(title);
        holder.mYearView.setText(year);

        final String imageUrl;
        if (! detail.Poster.equals("N/A")) {
            imageUrl = detail.Poster;
        } else {
            // default image if there is no poster available
            imageUrl = holder.mView.getResources().getString(R.string.default_poster);
        }
        holder.mThumbImageView.layout(0, 0, 0, 0); // invalidate the width so that glide wont use that dimension
        Glide.with(context).load(imageUrl).into(holder.mThumbImageView);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                // Pass data object in the bundle and populate details activity.
                intent.putExtra(DetailActivity.MOVIE_DETAIL, detail);
                intent.putExtra(DetailActivity.IMAGE_URL, imageUrl);

                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(context,
                                holder.mThumbImageView, "poster");
                context.startActivity(intent, options.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mValues == null) {
            return 0;
        }
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTitleView;
        public final TextView mYearView;
        public final TextView mDirectorView;
        public final ImageView mThumbImageView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitleView = (TextView) view.findViewById(R.id.movie_title);
            mYearView = (TextView) view.findViewById(R.id.movie_year);
            mThumbImageView = (ImageView) view.findViewById(R.id.thumbnail);
            mDirectorView = (TextView) view.findViewById(R.id.movie_director);
        }

    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        super.onViewRecycled(holder);
        Glide.clear(holder.mThumbImageView);
    }

    public void swapData(List<Detail> items) {
        if(items != null) {
            mValues = items;
            notifyDataSetChanged();

        } else {
            mValues = null;
        }
    }
}