package com.application.mwth2019kotlin.ukm.order

import android.support.v7.widget.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.application.mwth2019kotlin.*

/*import android.view.View.GONE
import android.view.View.VISIBLE
import android.transition.Fade
import android.transition.TransitionManager
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.Animation*/
import com.application.mwth2019kotlin.kasir.home.ModelBestSellerProduct
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.template_order_product.*


class AdapterOrderProduct(private val data: MutableList<ModelBestSellerProduct>,
                          private val onClick: (ModelBestSellerProduct) -> Unit,
                          private val onPlus: (Int,TextView,View,View) -> Unit,
                          private val onMinus: (Int,TextView,View) -> Unit)
    : RecyclerView.Adapter<AdapterOrderProduct.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindData(data[position])
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return LayoutInflater.from(parent.context)
                .inflate(R.layout.template_order_product, parent, false).let {
                    ViewHolder(it, onClick,onPlus,onMinus)
                }
    }

    class ViewHolder(override val containerView: View, private val onClick: (ModelBestSellerProduct) -> Unit,
                     private val onPlus: (Int,TextView,View,View) -> Unit,
                     private val onMinus: (Int, TextView, View) -> Unit) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindData(data:ModelBestSellerProduct) {
            with(data) {
                text_namaproduct.text=name
                if(qtybeli<=0){
                    text_qty.hideView()
                    frame_minus.hideView()
                }else{
                    text_qty.showView()
                    frame_minus.showView()
                }
                text_qty.text=qtybeli.toString()
                text_priceperitem.text=price_publish.formatRupiah()
                GlideApp.with(containerView).asBitmap().load(image_src).into(image_product)
                image_product.setOnClickListener { onClick(this) }
                frame_plus.setOnClickListener {
                    onPlus(adapterPosition,text_qty,frame_plus,frame_minus)
                }
                frame_minus.setOnClickListener {
                    onMinus(adapterPosition,text_qty,frame_minus)
                }
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