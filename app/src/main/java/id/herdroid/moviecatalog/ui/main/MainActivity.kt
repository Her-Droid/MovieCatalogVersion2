package id.herdroid.moviecatalog.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import id.herdroid.moviecatalog.R
import id.herdroid.moviecatalog.adapter.ViewPagerAdapter
import id.herdroid.moviecatalog.enum.TypeData
import id.herdroid.moviecatalog.ui.fragment.MovieFragment
import id.herdroid.moviecatalog.ui.fragment.TvShowFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar_main)
        supportActionBar?.setDisplayShowTitleEnabled(false);
        supportActionBar?.setIcon(R.drawable.ic_tmdb);

        val viewPagerAdapter = ViewPagerAdapter(this, supportFragmentManager)
        vp_main.adapter = viewPagerAdapter
        tabs.setupWithViewPager(vp_main)

    }


}