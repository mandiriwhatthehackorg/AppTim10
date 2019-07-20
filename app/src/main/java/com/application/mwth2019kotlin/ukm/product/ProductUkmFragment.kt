package com.application.mwth2019kotlin.ukm.product


import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.application.library.EndlessRecyclerViewScrollListener
import com.application.mwth2019kotlin.*
import com.application.mwth2019kotlin.basemvp.BaseMvpFragment
import com.application.mwth2019kotlin.ukm.product.add.ProductAddActivity
import kotlinx.android.synthetic.main.fragment_product_ukm.view.*
import kotlinx.android.synthetic.main.partial_progress_paging.view.*

class ProductUkmFragment : BaseMvpFragment<ProductUkmContract.ViewProductUkm, ProductUkmContract.Presenter>(),
    ProductUkmContract.ViewProductUkm{
    override var mPresenter: ProductUkmContract.Presenter=ProductUkmPresenter()

    override fun progressProductUkm() {
        progressLayout.showView()
    }

    override fun showProductUkm(productUkmResponse: ProductUkmResponse) {
        progressLayout.hideView()
        page++
        if(productUkmKategoriAdapter.getDatas().size <= 0) {//cek jika kateogri kosong(berarti pertama kali buka page,
            // aktifkan infinite scroll
            productUkmKategoriAdapter.setDatas(productUkmResponse.message.category)
            productUkmProductAdapter.setDatas(ArrayList(productUkmResponse.message.product))
            viewparent.recycler_product.addOnScrollListener(object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
                override fun onLoadMore(halaman: Int, totalItemsCount: Int, view: RecyclerView) {
                }
            })
        }else{
            productUkmProductAdapter.setDatas(ArrayList(productUkmResponse.message.product))
        }
    }
    private lateinit var productUkmKategoriAdapter: ProductUkmKategoriAdapter
    private lateinit var productUkmProductAdapter: ProductUkmProductAdapter
    private var page=0
    private var kategori="all"
    private lateinit var viewparent:View
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewparent=view
        productUkmKategoriAdapter = ProductUkmKategoriAdapter(ArrayList()) {
            //klik kategori
            for (i in 0 until productUkmKategoriAdapter.getDatas().size) {
                productUkmKategoriAdapter.getDatas()[i].isselect=false
            }
            productUkmKategoriAdapter.getDatas()[it].isselect=true
            productUkmKategoriAdapter.notifyDataSetChanged()

            //baca ulang produknya sesuai kategori
            kategori = if(it>0) {
                productUkmKategoriAdapter.getDatas()[it].id.toString()
            }else{
                "all"
            }
            viewparent.text_kateg.text=productUkmKategoriAdapter.getDatas()[it].name
            initData()
        }
        viewparent.recycler_kategori.layoutManager= LinearLayoutManager(ctx, LinearLayoutManager.HORIZONTAL, false)
        viewparent.recycler_kategori.adapter = productUkmKategoriAdapter

        productUkmProductAdapter = ProductUkmProductAdapter(ArrayList()){}
        linearLayoutManager=LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)
        viewparent.recycler_product.layoutManager= linearLayoutManager
        viewparent.recycler_product.adapter = productUkmProductAdapter
        viewparent.button_add.setOnClickListener {
            val intent= Intent(ctx,ProductAddActivity::class.java)
            startActivity(intent)
        }
        initData()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_ukm, container, false)
    }

    private fun initData(){
        page=0
        ctx?.let {
            getCredential(it)?.let { loginData ->
                getOperatorUkm(it)?.let { homeOperator ->
                    mPresenter.loadProduct(loginData.token,page.toString(),
                        homeOperator.id.toString(),kategori)
                }
            }
        }
    }
}
