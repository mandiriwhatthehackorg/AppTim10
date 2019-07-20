package com.application.mwth2019kotlin.ukm.product

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.application.mwth2019kotlin.R
import kotlinx.android.extensions.LayoutContainer

class ProductUkmKategoriAdapter(private val data: MutableList<ProductUkmKategori>,
                         private val onClick: (Int) -> Unit)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val profileMemberData = data[position]
        when (holder.itemViewType) {
            TYPE_SELECT -> {
                (holder as SelectHolder).bindData(profileMemberData)
            }
            TYPE_UNSELECT -> {
                (holder as UnselectHolder).bindData(profileMemberData)
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_SELECT) {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.template_order_kategoriselect, parent, false).let {
                    SelectHolder(it,onClick)
                }
        }else {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.template_order_kategoriunselect, parent, false).let {
                    UnselectHolder(it,onClick)
                }
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if (data[position].isselect) {
            TYPE_SELECT
        }else {
            TYPE_UNSELECT
        }
    }


    class SelectHolder(override val containerView: View, private val onClick: (Int) -> Unit) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindData(data: ProductUkmKategori) {
            (containerView.findViewById(R.id.text_kategori) as TextView).text = data.name
            containerView.setOnClickListener { onClick(adapterPosition) }
        }
    }

    class UnselectHolder(override val containerView: View, private val onClick: (Int) -> Unit) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindData(data: ProductUkmKategori) {
            (containerView.findViewById(R.id.text_kategori) as TextView).text = data.name
            containerView.setOnClickListener { onClick(adapterPosition) }
        }
    }


    fun setDatas(datas: List<ProductUkmKategori>) {
        data.clear()
        data.add(0,ProductUkmKategori(0,"All",true))
        data.addAll(datas)
        notifyDataSetChanged()
    }

    fun getDatas(): MutableList<ProductUkmKategori> {
        return data
    }

    companion object {
        const val TYPE_SELECT = 1
        const val TYPE_UNSELECT = 2
    }
}