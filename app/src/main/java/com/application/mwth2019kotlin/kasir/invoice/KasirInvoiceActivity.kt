package com.application.mwth2019kotlin.kasir.invoice

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.application.mwth2019kotlin.*
import com.application.mwth2019kotlin.basemvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_invoice.*

class KasirInvoiceActivity : BaseMvpActivity<InvoiceKasirContract.View, InvoiceKasirContract.Presenter>(),
    InvoiceKasirContract.View{
    override var mPresenter: InvoiceKasirContract.Presenter=InvoiceKasirPresenter()

    override fun progressInvoiceKasir() {
        progressLayout.showView()
    }

    override fun showInvoiceKasir(kasirInvoiceResponse: KasirInvoiceResponse) {
        progressLayout.hideView()
        kasirInvoiceAdapter.setDatas(kasirInvoiceResponse.message.detail)
        val drawable = TextDrawable.builder()
            .buildRound(kasirInvoiceResponse.message.customer_name[0].toString(), generator.randomColor)
        image_user.setImageDrawable(drawable)
        text_invoice.text=kasirInvoiceResponse.message.invoice
        text_namacustomer.text=kasirInvoiceResponse.message.customer_name
        text_date.text=kasirInvoiceResponse.message.date.formatTanggal("MMMM dd, yyyy","yyyy-MM-dd")
        text_total.text=kasirInvoiceResponse.message.total.formatRupiah()
        text_totalprice.text=kasirInvoiceResponse.message.total_price.formatRupiah()
        text_pajak.text="0".formatRupiah()
        kasirInvoiceResponse.message.tax?.let {
            text_pajak.text=it.formatRupiah()
        }
    }

    private lateinit var kasirInvoiceAdapter: KasirInvoiceAdapter
    private val generator = ColorGenerator.MATERIAL
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_invoice)
        super.onCreate(savedInstanceState)

        kasirInvoiceAdapter = KasirInvoiceAdapter(ArrayList()) {
        }
        recycler_productreview.layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler_productreview.adapter = kasirInvoiceAdapter
        ic_back.setOnClickListener {
            finish()
        }

        getCredential(this)?.let {loginData ->
            val id=intent.getIntExtra(TRANSACTIONID,0)
            if(id>0) {
                mPresenter.loadInvoiceKasir(loginData.token,id)
            }
        }
    }
    private val TRANSACTIONID="transaction_id"
}
