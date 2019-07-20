package com.application.mwth2019kotlin.kasir.checkout

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.application.mwth2019kotlin.R
import com.application.mwth2019kotlin.kasir.checkout.customer.CustomerKasirFragment
import com.application.mwth2019kotlin.kasir.checkout.done.DoneKasirFragment
import com.application.mwth2019kotlin.kasir.checkout.payment.PaymentKasirFragment

class CheckoutActivity : AppCompatActivity() {
    fun nextStepCustomer(idtransaksi:String) {
        supportFragmentManager.beginTransaction().replace(R.id.framelayout_kasir_checkout,
            PaymentKasirFragment.newInstance(idtransaksi)
        ).commitAllowingStateLoss()
        page=1
    }

    fun prevStepCustomer() {
        finish()
    }

    fun nextStepPayment(changemoney:String,transid:String) {
        supportFragmentManager.beginTransaction().replace(R.id.framelayout_kasir_checkout,
            DoneKasirFragment.newInstance(changemoney,transid)
        ).commitAllowingStateLoss()
        page=0
    }

    fun prevStepPayment() {
        supportFragmentManager.beginTransaction().replace(R.id.framelayout_kasir_checkout,
            CustomerKasirFragment()).commitAllowingStateLoss()
        page=0
    }

    fun nextStepDone() {
        finish()
    }

    private var page=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)
        supportFragmentManager.beginTransaction().replace(R.id.framelayout_kasir_checkout,
            CustomerKasirFragment()).commitAllowingStateLoss()
    }

    override fun onBackPressed() {
        if(page==0) {
            super.onBackPressed()
        }else{
            prevStepPayment()
        }

    }
}