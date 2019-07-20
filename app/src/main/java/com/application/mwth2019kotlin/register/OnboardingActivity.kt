package com.application.mwth2019kotlin.register

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.application.mwth2019kotlin.R
import com.application.mwth2019kotlin.login.LoginActivity
import kotlinx.android.synthetic.main.activity_onboarding.*

class OnboardingActivity : AppCompatActivity() {
    private var page:Int=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        supportFragmentManager.beginTransaction().replace(R.id.framelayout_onboarding, Step1Fragment()).commitAllowingStateLoss()
        button_lanjut.setOnClickListener {
            if(page==1){
                view_a1.setBackgroundColor(ContextCompat.getColor(this,R.color.textMandiri))
                text_a1.setTextColor(ContextCompat.getColor(this,R.color.textMandiri))
                supportFragmentManager.beginTransaction().replace(R.id.framelayout_onboarding, Step2Fragment()).commitAllowingStateLoss()
                view_a2.setBackgroundColor(ContextCompat.getColor(this,R.color.colorAccentMandiri))
                text_a2.setTextColor(ContextCompat.getColor(this,R.color.colorAccentMandiri))
                page++
            }else if(page==2){
                view_a2.setBackgroundColor(ContextCompat.getColor(this,R.color.textMandiri))
                text_a2.setTextColor(ContextCompat.getColor(this,R.color.textMandiri))
                supportFragmentManager.beginTransaction().replace(R.id.framelayout_onboarding, Step3Fragment()).commitAllowingStateLoss()
                view_a3.setBackgroundColor(ContextCompat.getColor(this,R.color.colorAccentMandiri))
                text_a3.setTextColor(ContextCompat.getColor(this,R.color.colorAccentMandiri))
                page++
            }else if(page==3){
                view_a3.setBackgroundColor(ContextCompat.getColor(this,R.color.textMandiri))
                text_a3.setTextColor(ContextCompat.getColor(this,R.color.textMandiri))
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
    }
}
