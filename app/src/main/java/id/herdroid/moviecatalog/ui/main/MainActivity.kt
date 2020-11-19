package id.herdroid.moviecatalog.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.herdroid.moviecatalog.R
import id.herdroid.moviecatalog.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = getString(R.string.app_name)

        val viewPagerAdapter = ViewPagerAdapter(this, supportFragmentManager)
        vp_main.adapter = viewPagerAdapter
        tabs.setupWithViewPager(vp_main)
    }
}