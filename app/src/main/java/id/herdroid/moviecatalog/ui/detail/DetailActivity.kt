package id.herdroid.moviecatalog.ui.detail

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import id.herdroid.moviecatalog.R
import id.herdroid.moviecatalog.data.entity.MovieEntity
import id.herdroid.moviecatalog.data.entity.TvShowEntity
import id.herdroid.moviecatalog.viewmodel.DetailViewModel
import id.herdroid.moviecatalog.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private var imageUrl = "https://image.tmdb.org/t/p/w500"

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TVSHOW = "extra_tvshow"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val movieId = intent.getIntExtra(EXTRA_MOVIE, 0)
            if (movieId != 0) {
                viewModel.setSelectedData(movieId)

                progress_barDetail.visibility = View.VISIBLE
                viewModel.getMovie().observe(this, Observer { movie -> populateMovie(movie)
                    progress_barDetail.visibility = View.GONE
                })
            } else {
                val tvId = intent.getIntExtra(EXTRA_TVSHOW, 0)
                if (tvId != 0) {
                    viewModel.setSelectedData(tvId)

                    progress_barDetail.visibility = View.VISIBLE
                    viewModel.getTvShow().observe(this, Observer { tvShow -> populateTv(tvShow)
                        progress_barDetail.visibility = View.GONE
                    })
                }
            }
        }
    }

    private fun populateMovie(data: MovieEntity) {
        movie_title.text = data.title
        movie_detail.text = data.description
        release_date.text = data.releaseDate
        Glide.with(this).load(imageUrl + data.imagePath)
                .into(bg_image)
        Glide.with(this).load(imageUrl + data.imagePath)
                .into(imgPoster)
        title = data.title
    }

    private fun populateTv(data: TvShowEntity) {
        movie_title.text = data.title
        movie_detail.text = data.description
        release_date.text = data.releaseDate
        Glide.with(this).load(imageUrl + data.imagePath)
                .into(bg_image)
        Glide.with(this).load(imageUrl + data.imagePath)
                .into(imgPoster)

        title = data.title
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}
