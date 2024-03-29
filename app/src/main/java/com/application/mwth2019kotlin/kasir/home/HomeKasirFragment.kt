package com.application.mwth2019kotlin.kasir.home


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.application.mwth2019kotlin.*
import com.application.mwth2019kotlin.basemvp.BaseMvpFragment
import com.application.mwth2019kotlin.kasir.MainKasirActivity
import com.application.mwth2019kotlin.kasir.invoice.KasirInvoiceActivity
import kotlinx.android.synthetic.main.fragment_home_kasir.view.*
import java.util.ArrayList
import android.support.v7.app.AlertDialog
import com.application.mwth2019kotlin.login.LoginActivity


/**
 * A simple [Fragment] subclass.
 *
 */
class HomeKasirFragment : BaseMvpFragment<HomeKasirContract.View, HomeKasirContract.Presenter>(),
    HomeKasirContract.View{
    override var mPresenter: HomeKasirContract.Presenter=HomeKasirPresenter()
    override fun progressHomeKasir() {
        progressLayout.showView()
    }

    override fun showHomeKasir(homeResponse: HomeResponse) {
        progressLayout.hideView()
        adapterBestSellerProduct.setDatas(homeResponse.message.best_selling)
        adapterRecentTransaction.setDatas(homeResponse.message.recent_transaction)
        viewparent.text_salestoday.text=homeResponse.message.sale_today.toString().formatRupiah()
        viewparent.text_namashop.text=homeResponse.message.operator.ukm.name
        viewparent.text_address.text=homeResponse.message.operator.ukm.address
        ctx?.let {
            saveOperator(it,homeResponse.message.operator)
            (it as MainKasirActivity).onCreate()//aktifkan event view di home
        }

    }



    private lateinit var adapterBestSellerProduct:AdapterBestSellerProduct
    private lateinit var adapterRecentTransaction: AdapterRecentTransaction
    private lateinit var viewparent:View

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewparent=view
        adapterBestSellerProduct = AdapterBestSellerProduct(ArrayList()) {

        }
        viewparent.list_sellingproduct.layoutManager= LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        viewparent.list_sellingproduct.adapter = adapterBestSellerProduct

        adapterRecentTransaction= AdapterRecentTransaction(ArrayList()){
            ctx?.let {ctx->
                val intent= Intent(ctx, KasirInvoiceActivity::class.java)
                intent.putExtra("transaction_id",it.id)
                startActivity(intent)
            }
        }
        viewparent.list_recenttransaction.layoutManager= LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        viewparent.list_recenttransaction.adapter=adapterRecentTransaction
        ctx?.let {ctx->
            getCredential(ctx)?.let {logindata->
                mPresenter.loadHomeKasir(logindata.token)
            }
        }
        viewparent.avatar.setOnClickListener {
            ctx?.let {ctx->
                val alertDialogBuilder = AlertDialog.Builder(ctx)

                // set title dialog
                alertDialogBuilder.setTitle("Keluar dari aplikasi.")

                // set pesan dari dialog
                alertDialogBuilder
                    .setMessage("Anda yakin ingin keluar dari aplikasi?")
                    .setCancelable(false)
                    .setPositiveButton("Ya") { _, _ ->
                        delCredential(ctx)
                        val intent = Intent(ctx, LoginActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    }
                    .setNegativeButton("Tidak") { dialog, _ ->
                        // jika tombol ini diklik, akan menutup dialog
                        // dan tidak terjadi apa2
                        dialog.cancel()
                    }

                // membuat alert dialog dari builder
                val alertDialog = alertDialogBuilder.create()
                alertDialog.show()
                // menampilkan alert dialog
            }

        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_kasir, container, false)
    }


}
