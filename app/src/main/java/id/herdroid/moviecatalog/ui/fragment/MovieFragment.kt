package id.herdroid.moviecatalog.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.herdroid.moviecatalog.R
import id.herdroid.moviecatalog.adapter.MovieAdapter
import id.herdroid.moviecatalog.data.response.MovieItem
import id.herdroid.moviecatalog.enum.TypeData
import id.herdroid.moviecatalog.ui.detail.DetailActivity
import id.herdroid.moviecatalog.viewmodel.MovieViewModel
import id.herdroid.moviecatalog.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment() {

    private var type: TypeData = TypeData.MOVIES
    private lateinit var adapter: MovieAdapter

    companion object {
        const val MOVIE_TYPE = "type"

        @JvmStatic
        fun newInstance(type: TypeData) =
            MovieFragment().apply {
                arguments = Bundle().apply {
                    putInt(MOVIE_TYPE, type.ordinal)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

            val academyAdapter = MovieAdapter()
            pb_movie.visibility = View.VISIBLE
            viewModel.getMovies().observe(viewLifecycleOwner, Observer{ movie ->
                pb_movie.visibility = View.GONE
                academyAdapter.setMovie(movie)
                academyAdapter.notifyDataSetChanged()
            })

            with(rv_movie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = academyAdapter
            }
        }
    }




}
