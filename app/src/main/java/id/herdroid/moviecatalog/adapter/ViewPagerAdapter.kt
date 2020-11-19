package id.herdroid.moviecatalog.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import id.herdroid.moviecatalog.R
import id.herdroid.moviecatalog.enum.TypeData
import id.herdroid.moviecatalog.ui.fragment.MovieFragment
import id.herdroid.moviecatalog.ui.fragment.TvShowFragment

class ViewPagerAdapter(private val context: Context, fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> MovieFragment.newInstance(TypeData.MOVIES)
            1 -> TvShowFragment.newInstance(TypeData.TV_SHOWS)
            else -> Fragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.getString(
            when (position) {
                0 -> R.string.movie
                1 -> R.string.tv_show
                else -> R.string.empty_movie
            }
        )
    }

}