package com.example.nandi

import android.animation.ObjectAnimator
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val logoImageView = findViewById<ImageView>(R.id.logoImageView)
        val fadeInAnimator = ObjectAnimator.ofFloat(logoImageView, "alpha", 0f, 1f)
        fadeInAnimator.duration = 1000L
        fadeInAnimator.start()
    }
}

