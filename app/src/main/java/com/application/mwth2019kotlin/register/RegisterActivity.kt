package com.application.mwth2019kotlin.register

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.application.mwth2019kotlin.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        button_nasabahbaru.setOnClickListener {
            val intent= Intent(this,OnboardingActivity::class.java)
            startActivity(intent)
        }
    }
}
