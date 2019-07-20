package com.application.mwth2019kotlin.ukm.order

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.application.mwth2019kotlin.R
import com.application.mwth2019kotlin.formatRupiah
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.template_order_product_review.*

class AdapterOrderProductReview(private val data: MutableList<OrderDetailKasirCart>,
                                private val onClick: (OrderDetailKasirCart) -> Unit)
    : RecyclerView.Adapter<AdapterOrderProductReview.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(data[position])
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return LayoutInflater.from(parent.context)
                .inflate(R.layout.template_order_product_review, parent, false).let {
                    ViewHolder(it, onClick)
                }
    }

    class ViewHolder(override val containerView: View, private val onClick: (OrderDetailKasirCart) -> Unit) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindData(data:OrderDetailKasirCart) {
            with(data) {
                text_nama.text=data.product.name
                text_qtysold.text=qty+" Pcs"
                text_priceperitem.text=data.product.price_publish.formatRupiah()
                text_totalperitem.text=(qty.toLong()*data.product.price_publish.toLong()).toString().formatRupiah()
                containerView.setOnClickListener { onClick(this) }
            }
        }
    }


    fun addDatas(datas: List<OrderDetailKasirCart>) {
        data.addAll(datas)
        notifyDataSetChanged()
    }

    fun setDatas(datas: List<OrderDetailKasirCart>) {
        data.clear()
        data.addAll(datas)
        notifyDataSetChanged()
    }
    fun clearDatas(){
        data.clear()
        notifyDataSetChanged()
    }
}