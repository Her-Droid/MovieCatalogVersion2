package id.herdroid.moviecatalog.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.herdroid.moviecatalog.R
import id.herdroid.moviecatalog.adapter.TvShowAdapter
import id.herdroid.moviecatalog.data.response.TvShowItem
import id.herdroid.moviecatalog.enum.TypeData
import id.herdroid.moviecatalog.ui.detail.DetailActivity
import id.herdroid.moviecatalog.viewmodel.TvShowViewModel
import id.herdroid.moviecatalog.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_tvshow.*

class TvShowFragment :Fragment(){
    private var type: TypeData = TypeData.TV_SHOWS

    companion object {
        const val TVSHOW_TYPE = "type"

        @JvmStatic
        fun newInstance(type: TypeData) =
                TvShowFragment().apply {
                    arguments = Bundle().apply {
                        putInt(TVSHOW_TYPE, type.ordinal)
                    }
                }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tvshow, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

            val academyAdapter = TvShowAdapter()
            pb_tv.visibility = View.VISIBLE
            viewModel.getTvShow().observe(viewLifecycleOwner, Observer{ tvShow ->
                pb_tv.visibility = View.GONE
                academyAdapter.setTvShow(tvShow)
                academyAdapter.notifyDataSetChanged()
            })

            with(rv_tvShow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = academyAdapter
            }
        }
    }


}
