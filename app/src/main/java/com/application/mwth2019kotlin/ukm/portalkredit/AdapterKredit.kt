package com.application.mwth2019kotlin.ukm.portalkredit

import android.support.v7.widget.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.application.mwth2019kotlin.*
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.template_portalkredit.*


class AdapterKredit(private val data: MutableList<ModelKredit>,
                    private val onClick: (ModelKredit) -> Unit)
    : RecyclerView.Adapter<AdapterKredit.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindData(data[position])
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return LayoutInflater.from(parent.context)
                .inflate(R.layout.template_portalkredit, parent, false).let {
                    ViewHolder(it, onClick)
                }
    }

    class ViewHolder(override val containerView: View, private val onClick: (ModelKredit) -> Unit) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindData(data:ModelKredit) {
            with(data) {
                text_title.text=title
                text_description.text=description
                GlideApp.with(containerView).asBitmap().load(image_src).into(image_portal)
            }
        }
    }


    fun setDatas(datas: List<ModelKredit>) {
        data.clear()
        data.addAll(datas)
        notifyDataSetChanged()
    }
}