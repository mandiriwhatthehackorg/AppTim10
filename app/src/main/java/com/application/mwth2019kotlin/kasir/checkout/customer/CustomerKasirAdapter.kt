package com.application.mwth2019kotlin.kasir.checkout.customer

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.application.mwth2019kotlin.R
import kotlinx.android.extensions.LayoutContainer

class AdapterCustomerVariasi(private var data: MutableList<ModelCustomer>,
                      private val onClick: (ModelCustomer) -> Unit)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val holderData = data[position]
        when (holder.itemViewType) {
            TYPE_RECENT -> {
                (holder as RecentHolder).bindData(holderData)
            }
            TYPE_ALPHABET -> {
                (holder as AlphabetHolder).bindData(holderData)
            }
            TYPE_CONTENT->{
                (holder as ContentHolder).bindData(holderData)
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_RECENT) {
            return LayoutInflater.from(parent.context)
                .inflate(R.layout.template_customer_recent, parent, false).let {
                    RecentHolder(it,onClick)
                }
        }else if (viewType == TYPE_ALPHABET) {
            return LayoutInflater.from(parent.context)
                .inflate(R.layout.template_customer_alphabet, parent, false).let {
                    AlphabetHolder(it,onClick)
                }
        }else {
            return LayoutInflater.from(parent.context)
                .inflate(R.layout.template_customer_content, parent, false).let {
                    ContentHolder(it,onClick)
                }
        }

    }

    override fun getItemViewType(position: Int): Int {
        return when {
            data[position].type=="recent" -> TYPE_RECENT
            data[position].type=="alphabet" -> TYPE_ALPHABET
            else -> TYPE_CONTENT
        }
    }


    class AlphabetHolder(override val containerView: View, private val onClick: (ModelCustomer) -> Unit) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindData(data: ModelCustomer) {
            (containerView.findViewById(R.id.text_alphabet) as TextView).text = data.caption
            containerView.setOnClickListener { onClick(data) }
        }
    }
    class RecentHolder(override val containerView: View, private val onClick: (ModelCustomer) -> Unit) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindData(data: ModelCustomer) {
            (containerView.findViewById(R.id.text_recent) as TextView).text = data.caption
            containerView.setOnClickListener { onClick(data) }
        }
    }

    class ContentHolder(override val containerView: View, private val onClick: (ModelCustomer) -> Unit) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindData(data: ModelCustomer) {
            (containerView.findViewById(R.id.text_namacustomer) as TextView).text = data.name
            (containerView.findViewById(R.id.text_subnamacustomer) as TextView).text = data.subname
            containerView.setOnClickListener { onClick(data) }
        }
    }


    fun addDatas(datas: MutableList<ModelCustomer>) {
        data = datas
    }

    companion object {
        const val TYPE_ALPHABET = 1
        const val TYPE_RECENT = 2
        const val TYPE_CONTENT=3
    }
}

class CustomerKasirAdapter(private var data: MutableList<String>,
                             private val onClick: (String) -> Unit)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val holderData = data[position]
        (holder as ContentHolder).bindData(holderData)
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return LayoutInflater.from(parent.context)
                .inflate(R.layout.template_customer_content, parent, false).let {
                    ContentHolder(it,onClick)
                }

    }


    class ContentHolder(override val containerView: View, private val onClick: (String) -> Unit) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        @SuppressLint("SetTextI18n")
        fun bindData(data: String) {
            (containerView.findViewById(R.id.text_namacustomer) as TextView).text = data
            (containerView.findViewById(R.id.text_subnamacustomer) as TextView).text = "Customer"
            containerView.setOnClickListener { onClick(data) }
        }
    }


    fun addDatas(datas: List<String>) {
        data.addAll(datas)
        notifyDataSetChanged()
    }
    fun setDatas(datas: List<String>){
        data.clear()
        data.addAll(datas)
        notifyDataSetChanged()
    }
}