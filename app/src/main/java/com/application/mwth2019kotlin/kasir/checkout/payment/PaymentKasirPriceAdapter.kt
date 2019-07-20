package com.application.mwth2019kotlin.kasir.checkout.payment

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.application.mwth2019kotlin.R
import com.application.mwth2019kotlin.formatRupiah
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.template_payment_nominal.*

class PaymentKasirPriceAdapter(private val data: MutableList<ModelPrice>,
                               private val onClick: (ModelPrice) -> Unit)
    : RecyclerView.Adapter<PaymentKasirPriceAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(data[position])
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return LayoutInflater.from(parent.context)
                .inflate(R.layout.template_payment_nominal, parent, false).let {
                    ViewHolder(it, onClick)
                }
    }

    class ViewHolder(override val containerView: View, private val onClick: (ModelPrice) -> Unit) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindData(data:ModelPrice) {
            with(data) {
                text_price.text=uang.toString().formatRupiah()
                containerView.setOnClickListener { onClick(this) }
            }
        }
    }


    fun addDatas(datas: List<ModelPrice>) {
        data.addAll(datas)
    }
}