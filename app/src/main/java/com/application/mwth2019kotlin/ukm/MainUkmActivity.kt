package com.application.mwth2019kotlin.ukm

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.application.mwth2019kotlin.R
import com.application.mwth2019kotlin.disableShiftMode
import com.application.mwth2019kotlin.ukm.home.HomeUkmFragment
import com.application.mwth2019kotlin.ukm.product.ProductUkmFragment
import com.application.mwth2019kotlin.ukm.report.ReportFragment
import com.application.mwth2019kotlin.ukm.transaksi.TransaksiFragment
import kotlinx.android.synthetic.main.activity_ukm_home.*

class MainUkmActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when(p0.itemId){
            R.id.navigation_home->{
                supportFragmentManager.beginTransaction().replace(R.id.framelayout_ukm, HomeUkmFragment()).commitAllowingStateLoss()
            }
            R.id.navigation_transaksi->{
                supportFragmentManager.beginTransaction().replace(R.id.framelayout_ukm, TransaksiFragment()).commitAllowingStateLoss()
            }
            R.id.navigation_product->{
                supportFragmentManager.beginTransaction().replace(R.id.framelayout_ukm, ProductUkmFragment()).commitAllowingStateLoss()
            }
            R.id.navigation_kasir->{
            }
            R.id.navigation_report->{
                supportFragmentManager.beginTransaction().replace(R.id.framelayout_ukm, ReportFragment()).commitAllowingStateLoss()
            }
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ukm_home)
        bottomnavigation_ukm.itemIconTintList=null
        bottomnavigation_ukm.disableShiftMode()
        supportFragmentManager.beginTransaction().replace(R.id.framelayout_ukm, HomeUkmFragment()).commitAllowingStateLoss()
    }
    fun onCreate(){
        bottomnavigation_ukm.setOnNavigationItemSelectedListener(this)
    }
}
