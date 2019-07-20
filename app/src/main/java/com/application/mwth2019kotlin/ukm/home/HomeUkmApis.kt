package com.application.mwth2019kotlin.ukm.home

import com.application.mwth2019kotlin.basemvp.BaseMvpPresenter
import com.application.mwth2019kotlin.basemvp.BaseMvpPresenterImpl
import com.application.mwth2019kotlin.basemvp.BaseMvpView
object HomeUkmContract{
    interface View : BaseMvpView {
        fun progressHomeUkm()
        fun showHomeUkm(homeResponse: HomeResponse)
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun loadHomeUkm(token:String)
    }
}

class HomeUkmPresenter: BaseMvpPresenterImpl<HomeUkmContract.View>(),HomeUkmContract.Presenter {
    override fun loadHomeUkm(token: String) {
        mView?.progressHomeUkm()
        val homeOperator=HomeOperatorUkm(1,"1","kasir1","kasir1","aktif"
        ,"2019-19-19 10:00:00")
        val modelBestSellerProducts=ArrayList<ModelBestSellerProduct>()
        val modelBestSellerProduct1=ModelBestSellerProduct(1,
            "1","1","Sate","10",
            "10000","0.5","15000",
            "placeholder","description","aktif","2019-19-19 10:00:00",
            "2019-19-19 10:00:00","20",0)
        modelBestSellerProducts.add(modelBestSellerProduct1)
        val modelBestSellerProduct2=ModelBestSellerProduct(2,
            "2","1","Nasi Goreng","10",
            "10000","0.5","15000",
            "placeholder","description","aktif","2019-19-19 10:00:00",
            "2019-19-19 10:00:00","20",0)
        modelBestSellerProducts.add(modelBestSellerProduct2)
        val modelBestSellerProduct3=ModelBestSellerProduct(3,
            "3","1","Soto","10",
            "10000","0.5","15000",
            "placeholder","description","aktif","2019-19-19 10:00:00",
            "2019-19-19 10:00:00","20",0)
        modelBestSellerProducts.add(modelBestSellerProduct3)
        val modelBestSellerProduct4=ModelBestSellerProduct(4,
            "4","1","Bubur ayam","10",
            "10000","0.5","15000",
            "placeholder","description","aktif","2019-19-19 10:00:00",
            "2019-19-19 10:00:00","20",0)
        modelBestSellerProducts.add(modelBestSellerProduct4)
        val modelBestSellerProduct5=ModelBestSellerProduct(5,
            "5","1","Rawon","10",
            "10000","0.5","15000",
            "placeholder","description","aktif","2019-19-19 10:00:00",
            "2019-19-19 10:00:00","20",0)
        modelBestSellerProducts.add(modelBestSellerProduct5)

        val listModelRecentTransaction=ArrayList<ModelRecentTransaction>()
        val modelRecentTransaction1=ModelRecentTransaction(1,
            "ukm1",
            "000111",
            "INVOICE000111",
            "CUSTOMERNAME_1",
            "5.0",
            "200000",
            "5",
            "95000",
            "100000",
            "2019-19-19 10:00:00",
            "transfer",
            "200000",
            "105000",
            "kasir1",
            "kasir1",
            "done","2019-19-19 10:00:00",
            "2019-19-19 10:00:00")
        listModelRecentTransaction.add(modelRecentTransaction1)
        val modelRecentTransaction2=ModelRecentTransaction(2,
            "ukm1",
            "000111",
            "INVOICE000111",
            "CUSTOMERNAME_2",
            "5.0",
            "200000",
            "5",
            "95000",
            "100000",
            "2019-19-19 10:00:00",
            "transfer",
            "200000",
            "105000",
            "kasir1",
            "kasir1",
            "done","2019-19-19 10:00:00",
            "2019-19-19 10:00:00")
        listModelRecentTransaction.add(modelRecentTransaction2)
        val modelRecentTransaction3=ModelRecentTransaction(3,
            "ukm1",
            "000111",
            "INVOICE000111",
            "CUSTOMERNAME_3",
            "5.0",
            "200000",
            "5",
            "95000",
            "100000",
            "2019-19-19 10:00:00",
            "transfer",
            "200000",
            "105000",
            "kasir1",
            "kasir1",
            "done","2019-19-19 10:00:00",
            "2019-19-19 10:00:00")
        listModelRecentTransaction.add(modelRecentTransaction3)
        val modelRecentTransaction4=ModelRecentTransaction(4,
            "ukm1",
            "000111",
            "INVOICE000111",
            "CUSTOMERNAME_4",
            "5.0",
            "200000",
            "5",
            "95000",
            "100000",
            "2019-19-19 10:00:00",
            "transfer",
            "200000",
            "105000",
            "kasir1",
            "kasir1",
            "done","2019-19-19 10:00:00",
            "2019-19-19 10:00:00")
        listModelRecentTransaction.add(modelRecentTransaction4)
        val modelRecentTransaction5=ModelRecentTransaction(5,
            "ukm1",
            "000111",
            "INVOICE000111",
            "CUSTOMERNAME_5",
            "5.0",
            "200000",
            "5",
            "95000",
            "100000",
            "2019-19-19 10:00:00",
            "transfer",
            "200000",
            "105000",
            "kasir1",
            "kasir1",
            "done","2019-19-19 10:00:00",
            "2019-19-19 10:00:00")
        listModelRecentTransaction.add(modelRecentTransaction5)
        val homeMessage=HomeMessage(homeOperator,10000000,modelBestSellerProducts)

        val homeResponse=HomeResponse(true,homeMessage)
        mView?.showHomeUkm(homeResponse)
    }
}