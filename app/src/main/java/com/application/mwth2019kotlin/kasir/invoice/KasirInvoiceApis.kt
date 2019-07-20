package com.application.mwth2019kotlin.kasir.invoice

import com.application.mwth2019kotlin.basemvp.BaseMvpPresenter
import com.application.mwth2019kotlin.basemvp.BaseMvpPresenterImpl
import com.application.mwth2019kotlin.basemvp.BaseMvpView
import com.application.mwth2019kotlin.kasir.transaksi.TransaksiDetail
import com.application.mwth2019kotlin.kasir.transaksi.TransaksiItem

object InvoiceKasirContract{
    interface View : BaseMvpView {
        fun progressInvoiceKasir()
        fun showInvoiceKasir(kasirInvoiceResponse: KasirInvoiceResponse)
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun loadInvoiceKasir(token:String,transaction_id: Int)
    }
}

class InvoiceKasirPresenter: BaseMvpPresenterImpl<InvoiceKasirContract.View>(),InvoiceKasirContract.Presenter {
    override fun loadInvoiceKasir(token: String, transaction_id: Int) {
        mView?.progressInvoiceKasir()
        val transaksiDetail=ArrayList<TransaksiDetail>()
        val transaksiDetail1= TransaksiDetail(1,
            "000111","1","rawon",
            "4","10000","10","100000","2019-19-19 10:00:00",
            "2019-19-19 10:00:00")
        transaksiDetail.add(transaksiDetail1)
        val transaksiDetail2= TransaksiDetail(2,
            "000111","2","sate",
            "4","10000","10","100000","2019-19-19 10:00:00",
            "2019-19-19 10:00:00")
        transaksiDetail.add(transaksiDetail2)
        val transaksiDetail3= TransaksiDetail(3,
            "000111","3","nasi goreng",
            "4","10000","10","100000","2019-19-19 10:00:00",
            "2019-19-19 10:00:00")
        transaksiDetail.add(transaksiDetail3)
        val transaksiDetail4= TransaksiDetail(4,
            "000111","4","bubur ayam",
            "4","10000","10","100000","2019-19-19 10:00:00",
            "2019-19-19 10:00:00")
        transaksiDetail.add(transaksiDetail4)
        val transaksiDetail5= TransaksiDetail(5,
            "000111","5","soto",
            "4","10000","10","100000","2019-19-19 10:00:00",
            "2019-19-19 10:00:00")
        transaksiDetail.add(transaksiDetail5)
        val transaksiItem=TransaksiItem(1,
            "ukm1",
            "000111",
            "INVOICE000111",
            "CUSTOMERNAME",
            "5.0",
            "200000",
            "5",
            "95000",
            "100000",
            "2019-19-19 10:00:00",
            "transfer",
            "200000",
            105000,
            "kasir1",
            "kasir1",
            "done","2019-19-19 10:00:00",
            "2019-19-19 10:00:00",transaksiDetail)
        val kasirInvoiceResponse=KasirInvoiceResponse(true,transaksiItem)
    }
}