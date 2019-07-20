package com.application.mwth2019kotlin.ukm.product

import com.application.mwth2019kotlin.ukm.home.ModelBestSellerProduct

data class ProductUkmResponse(val result:Boolean,
                              val message:ProductUkmMessage)

data class ProductUkmMessage(val page:String,
                             val product: List<ModelBestSellerProduct>,
                             val category: List<ProductUkmKategori>)
data class ProductUkmKategori(val id:Int,
                              val name:String,
                              var isselect:Boolean)