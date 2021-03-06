package id.herdroid.moviecatalog.ui


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import id.herdroid.moviecatalog.R
import id.herdroid.moviecatalog.ui.main.MainActivity


class SplashActivity : AppCompatActivity() {
        private val splashTime: Long = 2000

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_splash)

            Handler(mainLooper).postDelayed({
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }, splashTime)
        }


}