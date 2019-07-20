package com.application.mwth2019kotlin.kasir.transaksi

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
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
import kotlinx.android.synthetic.main.template_captiondate_transaction.*
import kotlinx.android.synthetic.main.template_recent_transaction.*
import java.util.*


class TransaksiCaptionAdapter(private var data: MutableList<TransaksiBody>,
                            private val onClick: (TransaksiItem) -> Unit)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val isiData = data[position]
        (holder as CaptionHolder).bindData(isiData)
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return LayoutInflater.from(parent.context)
                    .inflate(R.layout.template_captiondate_transaction, parent, false).let {
                        CaptionHolder(it,parent.context,onClick)
                    }

    }



    class CaptionHolder(override val containerView: View,val context: Context,
                        private val onClick: (TransaksiItem) -> Unit) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindData(transaksiBody: TransaksiBody) {
            text_caption.text=transaksiBody.date.formatTanggal("MMM dd, yyyy","yyyy-MM-dd")
            val adapterRecentTransaction= AdapterAllTransaction(ArrayList(transaksiBody.data)){
                onClick(it)
            }
            recycler_transaksi.layoutManager= LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            recycler_transaksi.adapter=adapterRecentTransaction
        }
    }

    fun setDatas(datas: MutableList<TransaksiBody>){
        data.clear()
        data.addAll(datas)
        notifyDataSetChanged()
    }
    fun addDatas(datas: MutableList<TransaksiBody>) {
        data.addAll(datas)
        notifyDataSetChanged()
    }
}

class AdapterAllTransaction(private val data: MutableList<TransaksiItem>,
                               private val onClick: (TransaksiItem) -> Unit)
    : RecyclerView.Adapter<AdapterAllTransaction.AllTransactionHolder>() {

    override fun onBindViewHolder(holder: AllTransactionHolder, position: Int) {
        holder.bindData(data[position])
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllTransactionHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.template_recent_transaction, parent, false).let {
                AllTransactionHolder(it, onClick)
            }
    }


    class AllTransactionHolder(override val containerView: View, private val onClick: (TransaksiItem) -> Unit) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        private val generator = ColorGenerator.MATERIAL
        fun bindData(data:TransaksiItem) {
            with(data) {
                val drawable = TextDrawable.builder()
                    .buildRound(data.customer_name[0].toString(), generator.randomColor)
                text_namacustomer.text=data.customer_name
                text_invoice.text=data.invoice
                text_tanggal.text=data.created_at.formatTanggal(
                    "HH:mm","yyyy-MM-dd HH:mm:ss")
                text_totalprize.text=data.total_price.formatRupiah()
                image_avatar.setImageDrawable(drawable)
                containerView.setOnClickListener { onClick(this) }
            }
        }
    }
}