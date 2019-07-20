package com.application.mwth2019kotlin.kasir.transaksi

data class TransaksiResponse(val result:Boolean,
                             val message:TransaksiMessage)
data class TransaksiMessage(val page:String,
                            val transaction:List<TransaksiBody>)
data class TransaksiBody(val date:String,
                         val data:List<TransaksiItem>)

data class TransaksiItem(val id:Int, val ukm_id:String,
                         val transaction_code:String,
                         val invoice:String,val customer_name:String,
                         val tax:String?,
                         val total:String,//akumulasi hanya dari list product
                         val total_margin:String,
                         val total_price:String,//akumulasi list product dan penjumlahan/pemotongan diskon, pajak dll
                         val total_items:String,
                         val date:String,
                         val paid_by:String?,
                         val paid:String?,
                         val change_money:Long?,
                         val user:String,
                         val operatorname:String,
                         val status:String,
                         val created_at: String,
                         val updated_at: String,
                         val detail:List<TransaksiDetail>)

data class TransaksiDetail(val id:Int,
                           val transaction_id:String,
                           val product_id:String,
                           val name:String,
                           val price_margin:String,
                           val price:String,
                           val qty:String,
                           val price_total:String,
                           val created_at:String,
                           val updated_at:String)
