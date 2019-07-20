package com.application.mwth2019kotlin.ukm.order

import com.application.mwth2019kotlin.kasir.home.ModelBestSellerProduct

data class OrderKasirResponse(val result:Boolean,
                              val message:OrderKasirMessage)

data class OrderKasirMessage(val page:String,
                             val product: List<ModelBestSellerProduct>,
                             val category: List<OrderKasirKategori>,
                             val total_cart:Int)
data class OrderKasirKategori(val id:Int,
                              val name:String,
                              var isselect:Boolean)

data class OrderDetailKasirResponse(val result: Boolean,
                               val message:OrderDetailKasirMessage)

data class OrderDetailKasirMessage(val page:String,
                                   val cart: List<OrderDetailKasirCart>,
                                   val total_cart:Int)

data class OrderDetailKasirCart(val id:Int,
                                val product_id:String,
                                val code:String,
                                val qty:String,
                                val created_at:String?,
                                val updated_ar:String?,
                                val product:ModelBestSellerProduct)

data class OrderCartResponse(val result: Boolean,
                             val message:OrderDetailKasirCart)

data class ClearCartResponse(val result: Boolean,
                             val message:String)