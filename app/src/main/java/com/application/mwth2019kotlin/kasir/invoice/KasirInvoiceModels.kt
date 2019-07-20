package com.application.mwth2019kotlin.kasir.invoice

import com.application.mwth2019kotlin.kasir.transaksi.TransaksiItem

data class KasirInvoiceResponse(val result:Boolean,
                                val message:TransaksiItem)