package com.application.mwth2019kotlin.ukm.bizops

import android.support.v7.widget.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.application.mwth2019kotlin.*
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.template_alurkas.*


class AdapterAlurKas(private val data: MutableList<ModelAlurKas>,
                     private val onClick: (ModelAlurKas) -> Unit)
    : RecyclerView.Adapter<AdapterAlurKas.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindData(data[position])
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return LayoutInflater.from(parent.context)
                .inflate(R.layout.template_alurkas, parent, false).let {
                    ViewHolder(it, onClick)
                }
    }

    class ViewHolder(override val containerView: View, private val onClick: (ModelAlurKas) -> Unit) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindData(data:ModelAlurKas) {
            with(data) {
                text_title.text=title
                text_id.text=id
                text_describtion.text=describtion
                text_price.text=price
                text_date.text=date
            }
        }
    }


    fun setDatas(datas: List<ModelAlurKas>) {
        data.clear()
        data.addAll(datas)
        notifyDataSetChanged()
    }
}