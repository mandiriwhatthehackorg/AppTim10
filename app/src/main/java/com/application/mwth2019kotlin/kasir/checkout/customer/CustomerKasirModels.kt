package com.application.mwth2019kotlin.kasir.checkout.customer

import com.application.mwth2019kotlin.kasir.transaksi.TransaksiItem

data class ModelCustomer(val caption:String?,
                         val name:String?,
                         val subname:String?,
                         val type:String//recent, alphabet, content
)

data class CustomerKasirResponse(val result:Boolean,
                                 val message:CustomerKasirMessage)

data class CustomerKasirMessage(val page:String,
                                val customer:List<String>)

data class CustomerKasirAddResponse(val result: Boolean,
                                    val message:TransaksiItem)

data class CustomerKasirSkipResponse(val result: Boolean,
                                    val message:TransaksiItem)