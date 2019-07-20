package com.application.mwth2019kotlin.kasir.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.application.mwth2019kotlin.R
import com.application.mwth2019kotlin.formatRupiah
import com.application.mwth2019kotlin.formatTanggal
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.template_recent_transaction.*


class AdapterRecentTransaction(private val data: MutableList<ModelRecentTransaction>,
                               private val onClick: (ModelRecentTransaction) -> Unit)
    : RecyclerView.Adapter<AdapterRecentTransaction.RecentTransactionHolder>() {

    override fun onBindViewHolder(holder: RecentTransactionHolder, position: Int) {
        holder.bindData(data[position])
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentTransactionHolder {
        return LayoutInflater.from(parent.context)
                .inflate(R.layout.template_recent_transaction, parent, false).let {
                    RecentTransactionHolder(it, onClick)
                }
    }


    class RecentTransactionHolder(override val containerView: View, private val onClick: (ModelRecentTransaction) -> Unit) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        private val generator = ColorGenerator.MATERIAL
        fun bindData(data:ModelRecentTransaction) {
            with(data) {
                val drawable = TextDrawable.builder()
                    .buildRound(data.customer_name[0].toString(), generator.randomColor)
                text_namacustomer.text=data.customer_name
                text_invoice.text=data.invoice
                text_tanggal.text=data.created_at.formatTanggal(
                    "dd MMM yyyy HH:mm","yyyy-MM-dd HH:mm:ss")
                text_totalprize.text=data.total_price.formatRupiah()
                image_avatar.setImageDrawable(drawable)
                containerView.setOnClickListener { onClick(this) }
            }
        }
    }


    fun setDatas(datas: List<ModelRecentTransaction>) {
        data.clear()
        data.addAll(datas)
        notifyDataSetChanged()
    }
}