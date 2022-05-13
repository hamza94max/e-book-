package com.ibrahem.islamic.ebook.app.ui.Activites.SplashScreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.ibrahem.islamic.ebook.app.ui.Activites.mainActivity.MainActivity
import ibrahem.islamic.ebook.R
import ibrahem.islamic.ebook.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val binding = ActivitySplashBinding.inflate(layoutInflater)

        val animation = AnimationUtils.loadAnimation(this, R.anim.splashtransition)

        binding.SplashImage.startAnimation(animation)
        val handler = Handler()
        handler.postDelayed({
            val intent = Intent(this@SplashScreen, MainActivity::class.java)
            startActivity(intent)
            finish()

        }, 1200)
    }
}