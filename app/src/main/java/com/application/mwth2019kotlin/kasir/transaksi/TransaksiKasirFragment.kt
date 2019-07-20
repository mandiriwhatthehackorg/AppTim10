package com.application.mwth2019kotlin.kasir.transaksi


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.application.library.EndlessRecyclerViewScrollListener
import com.application.mwth2019kotlin.*

import com.application.mwth2019kotlin.basemvp.BaseMvpFragment
import com.application.mwth2019kotlin.kasir.invoice.KasirInvoiceActivity
import kotlinx.android.synthetic.main.fragment_transaksi_kasir.view.*
import kotlinx.android.synthetic.main.partial_progress_paging.view.*


/**
 * A simple [Fragment] subclass.
 *
 */
class TransaksiKasirFragment :  BaseMvpFragment<TransaksiKasirContract.View, TransaksiKasirContract.Presenter>(),
    TransaksiKasirContract.View {
    override var mPresenter: TransaksiKasirContract.Presenter=TransaksiKasirPresenter()

    override fun progressTransaksiKasir() {
        progressLayout.showView()
    }

    override fun showTransaksiKasir(transaksiResponse: TransaksiResponse) {
        progressLayout.hideView()
        transaksiCaptionAdapter.setDatas(ArrayList(transaksiResponse.message.transaction))
        page++
    }



    private lateinit var transaksiCaptionAdapter: TransaksiCaptionAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var viewparent:View
    private var page=0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewparent=view
        transaksiCaptionAdapter = TransaksiCaptionAdapter(ArrayList()){
            ctx?.let {ctx->
                val intent=Intent(ctx,KasirInvoiceActivity::class.java)
                intent.putExtra("transaction_id",it.id)
                startActivity(intent)
            }
        }
        linearLayoutManager=LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        viewparent.list_transaksi.layoutManager= linearLayoutManager
        viewparent.list_transaksi.adapter=transaksiCaptionAdapter
        ctx?.let {
            getCredential(it)?.let {loginData->
                getOperator(it)?.let {homeOperator ->
                    mPresenter.loadTransaksiKasir(loginData.token,"","",
                        this.page.toString(),homeOperator.ukm_id)
                }
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_transaksi_kasir, container, false)
    }


}
