package br.com.phsg.omdbapi.client;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import br.com.phsg.omdbapi.domain.DataApplication;
import br.com.phsg.omdbapi.domain.DetailEntity;
import br.com.phsg.omdbapi.domain.MovieEntity;
import br.com.phsg.omdbapi.domain.RatingEntity;
import br.com.phsg.omdbapi.dto.Detail;
import br.com.phsg.omdbapi.dto.Rating;
import io.requery.Persistable;
import io.requery.reactivex.ReactiveEntityStore;

public class DetailActivity extends AppCompatActivity {

    public static final String MOVIE_DETAIL = "movie_detail";
    public static final String IMAGE_URL = "image_url";

    private ReactiveEntityStore<Persistable> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final Detail detail = (Detail) getIntent().getSerializableExtra(MOVIE_DETAIL);
        final String imageUrl =  getIntent().getStringExtra(IMAGE_URL);
        Glide.with(this).load(imageUrl).into( (ImageView) findViewById(R.id.main_backdrop));

        // set title for the appbar
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.main_collapsing);
        collapsingToolbarLayout.setTitle(detail.Title);

        ((TextView) findViewById(R.id.grid_title)).setText(detail.Title);
        ((TextView) findViewById(R.id.grid_writers)).setText(detail.Writer);
        ((TextView) findViewById(R.id.grid_actors)).setText(detail.Actors);
        ((TextView) findViewById(R.id.grid_director)).setText(detail.Director);
        ((TextView) findViewById(R.id.grid_genre)).setText(detail.Genre);
        ((TextView) findViewById(R.id.grid_released)).setText(detail.Released);
        ((TextView) findViewById(R.id.grid_plot)).setText(detail.Plot);
        ((TextView) findViewById(R.id.grid_runtime)).setText(detail.Runtime);

        data = DataApplication.getData(getApplicationContext());

        MovieEntity movie = null;
        movie = (MovieEntity) data.findByKey(MovieEntity.class,detail.imdbID).blockingGet();

        if (movie == null) {
            movie = new MovieEntity();
            movie.setImdbID(detail.imdbID);
            movie.setPoster(detail.Poster);
            movie.setTitle(detail.Title);
            movie.setType(detail.Type);
            movie.setYear(detail.Year);

            DetailEntity detailE = new DetailEntity();

            detailE.setImdbID(detail.imdbID);
            detailE.setGenre(detail.Genre);
            detailE.setRated(detail.Rated);
            detailE.setReleased(detail.Released);
            detailE.setRuntime(detail.Runtime);

            movie.setDetail(detailE);
            for (Rating rat : detail.Ratings) {
                RatingEntity rating = new RatingEntity();
                rating.setMovie(movie);
                rating.setSource(rat.Source);
                rating.setValue(rat.Value);
                movie.getRatings().add(rating);
            }
            movie = data.insert(movie).toObservable().blockingFirst();
        } else {
            movie = data.update(movie).toObservable().blockingFirst();
        }
    }
}