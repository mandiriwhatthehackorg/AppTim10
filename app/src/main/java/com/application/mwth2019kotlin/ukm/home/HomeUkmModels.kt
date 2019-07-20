package com.application.mwth2019kotlin.ukm.home

data class HomeResponse(val result:Boolean,val message: HomeMessage)

data class HomeMessage(val operator:HomeOperatorUkm,val sale_today:Long,
                       val best_selling:List<ModelBestSellerProduct>)

data class HomeOperatorUkm(val id:Int, val name:String?,val username:String,val address:String?,
                        val description:String?,val created_at:String?)

data class ModelBestSellerProduct(val id:Int,val ukm_id:String,
                                  val category_id:String,
                                  val name:String,
                                  val stock:String,
                                  val price:String,
                                  val discount:String,
                                  val price_publish:String,
                                  val image_src:String,
                                  val description:String,
                                  val status:String,
                                  val created_at:String,
                                  val updated_at:String,
                                  val mostbuy:String,
                                  var qtybeli:Int=0)

data class ModelRecentTransaction(val id:Int, val ukm_id:String,
                                  val transaction_code:String,
                                  val invoice:String,val customer_name:String,
                                  val tax:String?,
                                  val total:String,//akumulasi hanya dari list product
                                  val total_margin:String,
                                  val total_price:String,//akumulasi list product dan penjumlahan/pemotongan diskon, pajak dll
                                  val total_items:String,
                                  val date:String,
                                  val paid_by:String,
                                  val paid:String,
                                  val change_money:String,
                                  val user:String,
                                  val operatorname:String,
                                  val status:String,
                                  val created_at: String,
                                  val updated_at: String)
