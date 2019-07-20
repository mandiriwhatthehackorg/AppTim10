package com.application.mwth2019kotlin.landing

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.application.mwth2019kotlin.R
import com.application.mwth2019kotlin.login.LoginActivity
import kotlinx.android.synthetic.main.activity_landing.*

class LandingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)
        button_usaha.setOnClickListener {
            val intent=Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
        button_kasir.setOnClickListener {
            val intent=Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
