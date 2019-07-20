package com.application.mwth2019kotlin.kasir.home

import com.application.mwth2019kotlin.basemvp.BaseMvpPresenter
import com.application.mwth2019kotlin.basemvp.BaseMvpPresenterImpl
import com.application.mwth2019kotlin.basemvp.BaseMvpView

object HomeKasirContract{
    interface View : BaseMvpView {
        fun progressHomeKasir()
        fun showHomeKasir(homeResponse: HomeResponse)
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun loadHomeKasir(token:String)
    }
}

class HomeKasirPresenter: BaseMvpPresenterImpl<HomeKasirContract.View>(),HomeKasirContract.Presenter {
    override fun loadHomeKasir(token: String) {
        mView?.progressHomeKasir()
        val homeUkm=HomeUkm(1,"ukm1","ukm1","jakarta")
        val homeOperator=HomeOperator(1,"1","kasir1","kasir1","aktif",
            homeUkm)
        val modelBestSellerProducts=ArrayList<ModelBestSellerProduct>()
        val modelBestSellerProduct1=ModelBestSellerProduct(1,
            "1","1","Sate","10",
            "10000","0.5","15000",
            "https://upload.wikimedia.org/wikipedia/commons/a/ad/Sate_Ponorogo.jpg","description","aktif","2019-19-19 10:00:00",
            "2019-19-19 10:00:00","20",0)
        modelBestSellerProducts.add(modelBestSellerProduct1)
        val modelBestSellerProduct2=ModelBestSellerProduct(2,
            "2","1","Nasi Goreng","10",
            "10000","0.5","15000",
            "https://i2.wp.com/resepkoki.id/wp-content/uploads/2018/01/Resep-Nasi-Goreng-Rendang.jpg","description","aktif","2019-19-19 10:00:00",
            "2019-19-19 10:00:00","20",0)
        modelBestSellerProducts.add(modelBestSellerProduct2)
        val modelBestSellerProduct3=ModelBestSellerProduct(3,
            "3","1","Soto","10",
            "10000","0.5","15000",
            "https://doyanresep.com/wp-content/uploads/2016/06/resep-soto-lamongan-1.jpg","description","aktif","2019-19-19 10:00:00",
            "2019-19-19 10:00:00","20",0)
        modelBestSellerProducts.add(modelBestSellerProduct3)
        val modelBestSellerProduct4=ModelBestSellerProduct(4,
            "4","1","Bubur ayam","10",
            "10000","0.5","15000",
            "https://upload.wikimedia.org/wikipedia/commons/a/a8/Bubur_ayam_chicken_porridge.JPG","description","aktif","2019-19-19 10:00:00",
            "2019-19-19 10:00:00","20",0)
        modelBestSellerProducts.add(modelBestSellerProduct4)
        val modelBestSellerProduct5=ModelBestSellerProduct(5,
            "5","1","Rawon","10",
            "10000","0.5","15000",
            "https://selerasa.com/wp-content/uploads/2018/11/rawon-daging-bumbu-instan-500x375.jpg","description","aktif","2019-19-19 10:00:00",
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
        val homeMessage=HomeMessage(homeOperator,10000000,modelBestSellerProducts,listModelRecentTransaction)
        val homeResponse=HomeResponse(true,homeMessage)
        mView?.showHomeKasir(homeResponse)
    }
}