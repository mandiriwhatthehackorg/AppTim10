package com.application.mwth2019kotlin.ukm.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.application.mwth2019kotlin.GlideApp
import com.application.mwth2019kotlin.R
import com.application.mwth2019kotlin.formatRupiah
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.template_product_bestselling_ukm.*

class AdapterBestSellerProduct(private var data: MutableList<ModelBestSellerProduct>,
                               private val onClick: (ModelBestSellerProduct) -> Unit)
    : RecyclerView.Adapter<AdapterBestSellerProduct.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(data[position])
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return LayoutInflater.from(parent.context)
                .inflate(R.layout.template_product_bestselling_ukm, parent, false).let {
                    ViewHolder(it, onClick)
                }
    }

    class ViewHolder(override val containerView: View, private val onClick: (ModelBestSellerProduct) -> Unit) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindData(data:ModelBestSellerProduct) {
            with(data) {
                text_namaproduct.text= name
                text_priceperitem.text=price_publish.formatRupiah()
                GlideApp.with(containerView).asBitmap().load(image_src).into(image_product)
                containerView.setOnClickListener { onClick(this) }
            }
        }
    }


    fun setDatas(datas: List<ModelBestSellerProduct>) {
        this.data.clear()
        this.data.addAll(datas)
        notifyDataSetChanged()
    }
}