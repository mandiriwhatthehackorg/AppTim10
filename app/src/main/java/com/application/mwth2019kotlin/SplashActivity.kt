package com.application.mwth2019kotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.application.mwth2019kotlin.login.LoginActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            finish()
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
        }, 3000)
    }
}
