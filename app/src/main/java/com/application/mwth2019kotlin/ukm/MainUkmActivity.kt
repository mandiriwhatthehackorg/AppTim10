package com.application.mwth2019kotlin.ukm

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.application.mwth2019kotlin.R
import com.application.mwth2019kotlin.ukm.bizops.BizopsFragment
import com.application.mwth2019kotlin.ukm.home.HomeUkmFragment
import com.application.mwth2019kotlin.ukm.order.OrderUkmActivity
import com.application.mwth2019kotlin.ukm.portalkredit.PortalKreditFragment
import com.application.mwth2019kotlin.ukm.product.ProductUkmFragment
import com.application.mwth2019kotlin.ukm.transaksi.TransaksiFragment
import kotlinx.android.synthetic.main.activity_ukm_home.*

class MainUkmActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when(p0.itemId){
            R.id.navigation_ukm1->{
                supportFragmentManager.beginTransaction().replace(R.id.framelayout_ukm, BizopsFragment()).commitAllowingStateLoss()
            }
            R.id.navigation_ukm2->{
                supportFragmentManager.beginTransaction().replace(R.id.framelayout_ukm, ProductUkmFragment()).commitAllowingStateLoss()
            }
            R.id.navigation_ukm4->{
                supportFragmentManager.beginTransaction().replace(R.id.framelayout_ukm, TransaksiFragment()).commitAllowingStateLoss()

            }
            R.id.navigation_ukm5->{
                supportFragmentManager.beginTransaction().replace(R.id.framelayout_ukm, PortalKreditFragment()).commitAllowingStateLoss()

            }
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ukm_home)
        supportFragmentManager.beginTransaction().replace(R.id.framelayout_ukm, HomeUkmFragment()).commitAllowingStateLoss()
        button_order.setOnClickListener {
            startActivity(Intent(this, OrderUkmActivity::class.java))
        }
    }
    fun onCreate(){
        bottomnavigation_ukm.selectedItemId=-1
        bottomnavigation_ukm.setOnNavigationItemSelectedListener(this)
    }
}
