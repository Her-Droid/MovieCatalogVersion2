package id.herdroid.moviecatalog.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import id.herdroid.moviecatalog.R
import id.herdroid.moviecatalog.adapter.FavoriteMovieAdapter
import id.herdroid.moviecatalog.viewmodel.MovieViewModel
import id.herdroid.moviecatalog.viewmodel.ViewModelFactory

class FavoriteMovieFragment : Fragment() {

    private lateinit var favMovieAdapter: FavoriteMovieAdapter
    private lateinit var movieViewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            movieViewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
        }
    }

}