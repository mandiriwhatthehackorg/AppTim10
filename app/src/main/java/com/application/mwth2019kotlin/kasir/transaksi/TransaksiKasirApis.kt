package com.application.mwth2019kotlin.kasir.transaksi

import com.application.mwth2019kotlin.basemvp.BaseMvpPresenter
import com.application.mwth2019kotlin.basemvp.BaseMvpPresenterImpl
import com.application.mwth2019kotlin.basemvp.BaseMvpView
import com.application.mwth2019kotlin.errorhandler.ErrorResponseHandler
import rx.functions.Action1

object TransaksiKasirContract{
    interface View : BaseMvpView {
        fun progressTransaksiKasir()
        fun showTransaksiKasir(transaksiResponse: TransaksiResponse)
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun loadTransaksiKasir(token:String,tanggal_awal:String,tanggal_akhir: String,page:String,ukm_id:String)
    }
}

class TransaksiKasirPresenter: BaseMvpPresenterImpl<TransaksiKasirContract.View>(),TransaksiKasirContract.Presenter {

    override fun loadTransaksiKasir(
        token: String,
        tanggal_awal: String,
        tanggal_akhir: String,
        page: String,
        ukm_id: String
    ) {
        mView?.progressTransaksiKasir()
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
        val listTransaksiItem=ArrayList<TransaksiItem>()
        listTransaksiItem.add(transaksiItem)
        listTransaksiItem.add(transaksiItem)
        listTransaksiItem.add(transaksiItem)
        listTransaksiItem.add(transaksiItem)
        val transaksiBody=TransaksiBody("2019-19-19 10:00:00",listTransaksiItem)
        val transaksiBody1=TransaksiBody("2019-19-18 10:00:00",listTransaksiItem)
        val transaksiBody2=TransaksiBody("2019-19-17 10:00:00",listTransaksiItem)
        val listTransaksiBody=ArrayList<TransaksiBody>()
        listTransaksiBody.add(transaksiBody)
        listTransaksiBody.add(transaksiBody1)
        listTransaksiBody.add(transaksiBody2)
        val transaksiMessage=TransaksiMessage("0",listTransaksiBody)
        val transaksiResponse=TransaksiResponse(true,transaksiMessage)
        mView?.showTransaksiKasir(transaksiResponse)
    }
}