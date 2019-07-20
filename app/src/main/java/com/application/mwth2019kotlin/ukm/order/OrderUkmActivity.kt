package com.application.mwth2019kotlin.ukm.order

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.transition.Fade
import android.transition.TransitionManager
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.TextView
import com.application.library.EndlessRecyclerViewScrollListener
import com.application.mwth2019kotlin.*
import com.application.mwth2019kotlin.basemvp.BaseMvpActivity
import com.application.mwth2019kotlin.kasir.checkout.CheckoutActivity
import kotlinx.android.synthetic.main.activity_order.*
import kotlinx.android.synthetic.main.fragment_bottomsheet_order.*

class OrderUkmActivity : BaseMvpActivity<OrderKasirContract.ViewOrder, OrderKasirContract.Presenter>(),
    OrderKasirContract.ViewOrder{


    override fun showOrderCartKasirMin(
        position: Int,
        text_qty: TextView,
        frame_minus: View,
        orderCartResponse: OrderCartResponse
    ) {
        progressLayout.hideView()
        if(adapterOrderProduct.getDatas()[position].qtybeli==1){
            hideViewAnimation(text_qty,frame_minus)
        }
        adapterOrderProduct.getDatas()[position].qtybeli=orderCartResponse.message.qty.toInt()
        text_qty.text=adapterOrderProduct.getDatas()[position].qtybeli.toString()
        initDataDetail()
    }

    override fun showOrderCartKasirPlus(
        position: Int,
        text_qty: TextView,
        frame_plus: View,
        frame_minus: View,
        orderCartResponse: OrderCartResponse
    ) {
        progressLayout.hideView()
        if(text_qty.visibility== GONE) {
            showViewAnimation(text_qty, frame_plus, frame_minus)
        }
        adapterOrderProduct.getDatas()[position].qtybeli=orderCartResponse.message.qty.toInt()
        adapterOrderProduct.notifyDataSetChanged()
        initDataDetail()

    }


    override fun progressOrderCartKasir() {
        progressLayout.showView()
    }


    override var mPresenter: OrderKasirContract.Presenter=OrderKasirPresenter()

    override fun progressOrderKasir() {
        progressLayout.showView()
    }
    override fun showOrderKasir(orderKasirResponse: OrderKasirResponse) {
        progressLayout.hideView()
        page++
        if(adapterOrderKategori.getDatas().size <= 0) {//cek jika kateogri kosong(berarti pertama kali buka page,
            // aktifkan infinite scroll
            adapterOrderKategori.setDatas(orderKasirResponse.message.category)
            adapterOrderProduct.setDatas(orderKasirResponse.message.product)
            recycler_orderproduct.addOnScrollListener(object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
                override fun onLoadMore(halaman: Int, totalItemsCount: Int, view: RecyclerView) {
                    pagingData()
                }
            })
            initDataDetail()
        }else{
            adapterOrderProduct.setDatas(orderKasirResponse.message.product)
        }
    }


    override fun progressOrderDetailKasir() {
        layout_progress_detail.showView()
    }
    override fun showOrderDetailKasir(orderDetailKasirResponse: OrderDetailKasirResponse) {
        layout_progress_detail.hideView()
        pagedetail++
        adapterOrderProductReview.setDatas(orderDetailKasirResponse.message.cart)
        recycler_orderproductreview.addOnScrollListener(object : EndlessRecyclerViewScrollListener(linearLayoutManagerDetail) {
            override fun onLoadMore(halaman: Int, totalItemsCount: Int, view: RecyclerView) {
                pagingDataDetail()
            }
        })
        text_amount.text=orderDetailKasirResponse.message.total_cart.toString().formatRupiah()
    }

    override fun progressClearCart() {
        layout_progress_detail.showView()
    }

    override fun showClearCart(clearCartResponse: ClearCartResponse) {
        layout_progress_detail.hideView()
        initData()
        adapterOrderProductReview.clearDatas()
        text_amount.text="0".formatRupiah()
    }


    private lateinit var adapterOrderKategori: AdapterOrderKategori
    private lateinit var adapterOrderProduct: AdapterOrderProduct
    private lateinit var adapterOrderProductReview: AdapterOrderProductReview
    private var page=0
    private var pagedetail=0
    private var kategori="all"
    private lateinit var linearLayoutManager:LinearLayoutManager
    private lateinit var linearLayoutManagerDetail:LinearLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_order)
        super.onCreate(savedInstanceState)

        adapterOrderKategori = AdapterOrderKategori(ArrayList()) {
            //klik kategori
            for (i in 0 until adapterOrderKategori.getDatas().size) {
                adapterOrderKategori.getDatas()[i].isselect=false
            }
            adapterOrderKategori.getDatas()[it].isselect=true
            adapterOrderKategori.notifyDataSetChanged()

            //baca ulang produknya sesuai kategori
            kategori = if(it>0) {
                adapterOrderKategori.getDatas()[it].id.toString()
            }else{
                "all"
            }
            text_kateg.text=adapterOrderKategori.getDatas()[it].name
            initData()
        }
        ic_back.setOnClickListener {
            finish()
        }
        recycler_kategori.layoutManager= LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recycler_kategori.adapter = adapterOrderKategori

        adapterOrderProduct = AdapterOrderProduct(ArrayList(),{}, {position,text_qty,frame_plus,frame_minus->
            addToCart(position, text_qty, frame_plus, frame_minus)
        },{position,text_qty,frame_minus->
            delToCart(position, text_qty, frame_minus)
        })
        linearLayoutManager=LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler_orderproduct.layoutManager= linearLayoutManager
        recycler_orderproduct.adapter = adapterOrderProduct



        adapterOrderProductReview = AdapterOrderProductReview(ArrayList()) {}
        linearLayoutManagerDetail=LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler_orderproductreview.layoutManager= linearLayoutManagerDetail
        recycler_orderproductreview.adapter = adapterOrderProductReview

        button_checkout.setOnClickListener {
            startActivity(Intent(this,CheckoutActivity::class.java))
        }
        button_clear.setOnClickListener {
            hapusCart()
        }
        initData()
    }

    private fun initData(){
        page=0
        getCredential(this)?.let { loginData ->
            getOperatorUkm(this)?.let {homeOperator ->
                mPresenter.loadOrderKasir(loginData.token,page.toString(),
                    homeOperator.id.toString(),kategori,
                    homeOperator.id.toString()+"cart")
            }

        }
    }
    private fun pagingData(){

    }

    private fun initDataDetail(){
        pagedetail=0
        getCredential(this)?.let { loginData ->
            getOperatorUkm(this)?.let {homeOperator ->
                mPresenter.loadOrderDetailKasir(loginData.token,pagedetail.toString(),
                    homeOperator.id.toString()+"cart")
            }
        }
    }
    private fun pagingDataDetail(){

    }

    private fun addToCart(position: Int,
                          text_qty: TextView, frame_plus: View, frame_minus: View){
        getCredential(this)?.let {loginData ->
            getOperatorUkm(this)?.let { homeOperator ->
                mPresenter.loadOrderCartPlus(
                    position, text_qty, frame_plus, frame_minus,loginData.token,
                    homeOperator.id.toString()+"cart",
                    adapterOrderProduct.getDatas()[position].id.toString())
            }
        }
    }

    private fun delToCart(position: Int,
                                 text_qty: TextView,frame_minus: View){
        getCredential(this)?.let {loginData ->
            getOperatorUkm(this)?.let { homeOperator ->
                mPresenter.loadOrderCartMin(
                    position, text_qty, frame_minus,loginData.token,
                    homeOperator.id.toString()+"cart",
                    adapterOrderProduct.getDatas()[position].id.toString())
            }
        }
    }

    private fun hideViewAnimation(text_qty:TextView,frame_minus: View){
        val fadeOut = AlphaAnimation(1f, 0f)
        fadeOut.interpolator = AccelerateInterpolator()
        fadeOut.duration = 300

        fadeOut.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(animation: Animation) {
                text_qty.hideView()
                frame_minus.hideView()
            }
            override fun onAnimationRepeat(animation: Animation) {}
            override fun onAnimationStart(animation: Animation) {}
        })

        text_qty.startAnimation(fadeOut)
        frame_minus.startAnimation(fadeOut)
    }

    private fun showViewAnimation(text_qty:TextView,frame_plus: View,frame_minus: View){
        val transition = Fade()
        transition.duration = 300
        transition.addTarget(text_qty)
        transition.addTarget(frame_minus)

        TransitionManager.beginDelayedTransition((frame_plus.parent as ViewGroup), transition)

        text_qty.showView()
        frame_minus.showView()
    }
    private fun hapusCart(){
        getCredential(this)?.let {loginData->
            getOperatorUkm(this)?.let {homeOperator ->
                mPresenter.loadClearCart(loginData.token,homeOperator.id.toString()+"cart")
            }

        }
    }

    override fun onResume() {
        super.onResume()
        if(adapterOrderKategori.getDatas().size > 0) {//cek jika kateogri kosong(berarti pertama kali buka page,
            text_amount.text=getString(R.string.loading1)
            initData()
            initDataDetail()
        }
    }
}
