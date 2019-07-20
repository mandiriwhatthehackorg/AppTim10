package com.application.mwth2019kotlin.ukm.home


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.application.mwth2019kotlin.*
import com.application.mwth2019kotlin.basemvp.BaseMvpFragment
import com.application.mwth2019kotlin.ukm.MainUkmActivity
import kotlinx.android.synthetic.main.fragment_home_ukm.view.*
import java.util.*

class HomeUkmFragment : BaseMvpFragment<HomeUkmContract.View, HomeUkmContract.Presenter>(),
    HomeUkmContract.View{
    override var mPresenter: HomeUkmContract.Presenter=HomeUkmPresenter()

    override fun progressHomeUkm() {
        progressLayout.showView()
    }

    override fun showHomeUkm(homeResponse: HomeResponse) {
        progressLayout.hideView()
        adapterBestSellerProduct.setDatas(homeResponse.message.best_selling)
        viewparent.text_namashop.text=homeResponse.message.operator.name
        viewparent.text_address.text=homeResponse.message.operator.address
        viewparent.text_totalsale.text=homeResponse.message.sale_today.toString().formatRupiah()
        ctx?.let {
            saveOperatorUkm(it,homeResponse.message.operator)
            (it as MainUkmActivity).onCreate()//aktifkan event view di home
        }
    }
    private lateinit var adapterBestSellerProduct:AdapterBestSellerProduct
    private lateinit var viewparent:View

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewparent=view
        adapterBestSellerProduct = AdapterBestSellerProduct(ArrayList()) {

        }
        viewparent.list_sellingproduct.layoutManager= LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        viewparent.list_sellingproduct.adapter = adapterBestSellerProduct

        ctx?.let {ctx->
            getCredential(ctx)?.let {logindata->
                mPresenter.loadHomeUkm(logindata.token)
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_ukm, container, false)
    }


}
