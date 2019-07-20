package com.application.mwth2019kotlin.kasir

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.application.mwth2019kotlin.R
import com.application.mwth2019kotlin.disableShiftMode
import com.application.mwth2019kotlin.kasir.home.HomeKasirFragment
import com.application.mwth2019kotlin.kasir.order.OrderKasirActivity
import com.application.mwth2019kotlin.kasir.transaksi.TransaksiKasirFragment
import kotlinx.android.synthetic.main.activity_kasir_home.*

class MainKasirActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when(p0.itemId){
            R.id.navigation_home->{
                supportFragmentManager.beginTransaction().replace(R.id.framelayout_kasir, HomeKasirFragment()).commitAllowingStateLoss()
            }
            R.id.navigation_transaksi->{
                supportFragmentManager.beginTransaction().replace(R.id.framelayout_kasir, TransaksiKasirFragment()).commitAllowingStateLoss()
            }
            R.id.navigation_akun->{

            }
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kasir_home)
        bottomnavigation_kasir.itemIconTintList=null
        bottomnavigation_kasir.disableShiftMode()
        supportFragmentManager.beginTransaction().replace(R.id.framelayout_kasir, HomeKasirFragment()).commitAllowingStateLoss()
    }

    fun onCreate(){
        bottomnavigation_kasir.setOnNavigationItemSelectedListener(this)
        button_order.setOnClickListener {
            startActivity(Intent(this,OrderKasirActivity::class.java))
        }
    }
}
