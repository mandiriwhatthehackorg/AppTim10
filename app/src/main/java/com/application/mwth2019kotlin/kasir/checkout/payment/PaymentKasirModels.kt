package com.application.mwth2019kotlin.kasir.checkout.payment

import com.application.mwth2019kotlin.kasir.transaksi.TransaksiItem

data class ModelPrice(val uang:Long)

data class TotalPriceResponse(val result:Boolean,
                              val message:String)

data class PaymentResponse(val result: Boolean,
                           val message: TransaksiItem)