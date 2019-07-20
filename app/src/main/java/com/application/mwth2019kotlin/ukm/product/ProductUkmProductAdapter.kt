package com.application.mwth2019kotlin.ukm.product

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.application.mwth2019kotlin.GlideApp
import com.application.mwth2019kotlin.R
import com.application.mwth2019kotlin.formatRupiah
import com.application.mwth2019kotlin.ukm.home.ModelBestSellerProduct
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.template_product_ukm.*


class ProductUkmProductAdapter(private val data: MutableList<ModelBestSellerProduct>,
                               private val onClick: (ModelBestSellerProduct) -> Unit)
    : RecyclerView.Adapter<ProductUkmProductAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindData(data[position])
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return LayoutInflater.from(parent.context)
                .inflate(R.layout.template_product_ukm, parent, false).let {
                    ViewHolder(it, onClick)
                }
    }

    class ViewHolder(override val containerView: View, private val onClick: (ModelBestSellerProduct) -> Unit) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindData(data:ModelBestSellerProduct) {
            with(data) {
                text_namaproduct.text=name
                text_priceperitem.text=price_publish.formatRupiah()
                GlideApp.with(containerView).asBitmap().load(image_src).into(image_product)
                image_product.setOnClickListener { onClick(this) }
            }
        }
    }


    fun setDatas(datas: List<ModelBestSellerProduct>) {
        data.clear()
        data.addAll(datas)
        notifyDataSetChanged()
    }
    fun addDatas(datas: List<ModelBestSellerProduct>) {
        data.addAll(datas)
        notifyDataSetChanged()
    }

    fun getDatas():List<ModelBestSellerProduct>{
        return data
    }
}