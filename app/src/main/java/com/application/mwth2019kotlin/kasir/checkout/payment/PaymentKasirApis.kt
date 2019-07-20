package com.application.mwth2019kotlin.kasir.checkout.payment

import com.application.mwth2019kotlin.basemvp.BaseMvpPresenter
import com.application.mwth2019kotlin.basemvp.BaseMvpPresenterImpl
import com.application.mwth2019kotlin.basemvp.BaseMvpView
import com.application.mwth2019kotlin.kasir.transaksi.TransaksiDetail
import com.application.mwth2019kotlin.kasir.transaksi.TransaksiItem
import rx.functions.Action1

object PaymentKasirContract{
    interface View : BaseMvpView {
        fun progressTotalPrice()
        fun showTotalPrice(totalPriceResponse: TotalPriceResponse)

        fun progressPayment()
        fun showPayment(paymentResponse: PaymentResponse)
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun loadTotalPrice(token:String, transaction_id: String)
        fun loadPayment(token:String,
                        transaction_id: String,
                        value: String)
    }
}

class PaymentKasirPresenter: BaseMvpPresenterImpl<PaymentKasirContract.View>(),PaymentKasirContract.Presenter {
    override fun loadTotalPrice(token: String, transaction_id: String) {
        mView?.progressTotalPrice()
        val totalPriceResponse=TotalPriceResponse(true,"150000")
        mView?.showTotalPrice(totalPriceResponse)
    }

    override fun loadPayment(token: String, transaction_id: String, value: String) {
        mView?.progressPayment()
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
        val message= TransaksiItem(1,
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
        val paymentResponse=PaymentResponse(true,message)
        mView?.showPayment(paymentResponse)
    }
}

