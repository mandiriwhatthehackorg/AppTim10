package com.application.mwth2019kotlin.ukm.product.add

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.application.mwth2019kotlin.GlideApp
import com.application.mwth2019kotlin.R
import kotlinx.android.synthetic.main.activity_product_ukm_add.*

class ProductAddActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_ukm_add)
        GlideApp.with(this).asBitmap().load(R.drawable.placeholder).into(image_picker)

    }
}
