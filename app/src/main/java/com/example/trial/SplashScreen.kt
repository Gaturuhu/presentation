package com.example.trial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash_screen)

        //Animation define
        val fade_in = AnimationUtils.loadAnimation(this,R.anim.fade_in)
        val fade_out = AnimationUtils.loadAnimation(this,R.anim.fade_out)


        //grab id from the front end xml

        logo.startAnimation(fade_in)
        logo.startAnimation(fade_out)

        //handle splashscreen
        Handler().postDelayed({
            startActivity(Intent(this, Onboard::class.java))
            finish()
        },5000)
    }
}
