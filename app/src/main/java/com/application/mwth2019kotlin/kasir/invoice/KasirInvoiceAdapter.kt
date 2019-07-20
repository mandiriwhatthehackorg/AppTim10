package com.application.mwth2019kotlin.kasir.invoice

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.application.mwth2019kotlin.R
import com.application.mwth2019kotlin.formatRupiah
import com.application.mwth2019kotlin.kasir.transaksi.TransaksiDetail
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.template_invoice_product_review.*

class KasirInvoiceAdapter(private val data: MutableList<TransaksiDetail>,
                          private val onClick: (TransaksiDetail) -> Unit)
    : RecyclerView.Adapter<KasirInvoiceAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(data[position])
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return LayoutInflater.from(parent.context)
                .inflate(R.layout.template_invoice_product_review, parent, false).let {
                    ViewHolder(it, onClick)
                }
    }

    class ViewHolder(override val containerView: View, private val onClick: (TransaksiDetail) -> Unit) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindData(data:TransaksiDetail) {
            with(data) {
                text_nama.text=name
                text_qtysold.text= "Qty : $qty"
                text_priceperitem.text=price.formatRupiah()
                text_totalperitem.text=price_total.formatRupiah()
                containerView.setOnClickListener { onClick(this) }
            }
        }
    }


    fun setDatas(datas: List<TransaksiDetail>) {
        data.clear()
        data.addAll(datas)
        notifyDataSetChanged()
    }
}