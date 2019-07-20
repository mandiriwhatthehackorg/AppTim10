package com.application.mwth2019kotlin.ukm.product
import com.application.mwth2019kotlin.basemvp.BaseMvpPresenter
import com.application.mwth2019kotlin.basemvp.BaseMvpPresenterImpl
import com.application.mwth2019kotlin.basemvp.BaseMvpView
import com.application.mwth2019kotlin.ukm.home.ModelBestSellerProduct

object ProductUkmContract{
    interface ViewProductUkm : BaseMvpView {
        fun progressProductUkm()
        fun showProductUkm(productUkmResponse: ProductUkmResponse)
    }

    interface Presenter : BaseMvpPresenter<ViewProductUkm> {
        fun loadProduct(token:String, page:String,ukm_id:String,category:String)
    }
}

class ProductUkmPresenter: BaseMvpPresenterImpl<ProductUkmContract.ViewProductUkm>(),ProductUkmContract.Presenter {
    override fun loadProduct(token: String, page: String, ukm_id: String, category: String) {
        mView?.progressProductUkm()

        val modelBestSellerProducts=ArrayList<ModelBestSellerProduct>()
        val modelBestSellerProduct1= ModelBestSellerProduct(1,
            "1","1","Sate","10",
            "10000","0.5","15000",
            "placeholder","description","aktif","2019-19-19 10:00:00",
            "2019-19-19 10:00:00","20",0)
        modelBestSellerProducts.add(modelBestSellerProduct1)
        val modelBestSellerProduct2= ModelBestSellerProduct(2,
            "2","1","Nasi Goreng","10",
            "10000","0.5","15000",
            "placeholder","description","aktif","2019-19-19 10:00:00",
            "2019-19-19 10:00:00","20",0)
        modelBestSellerProducts.add(modelBestSellerProduct2)
        val modelBestSellerProduct3= ModelBestSellerProduct(3,
            "3","1","Soto","10",
            "10000","0.5","15000",
            "placeholder","description","aktif","2019-19-19 10:00:00",
            "2019-19-19 10:00:00","20",0)
        modelBestSellerProducts.add(modelBestSellerProduct3)
        val modelBestSellerProduct4= ModelBestSellerProduct(4,
            "4","1","Bubur ayam","10",
            "10000","0.5","15000",
            "placeholder","description","aktif","2019-19-19 10:00:00",
            "2019-19-19 10:00:00","20",0)
        modelBestSellerProducts.add(modelBestSellerProduct4)
        val modelBestSellerProduct5= ModelBestSellerProduct(5,
            "5","1","Rawon","10",
            "10000","0.5","15000",
            "placeholder","description","aktif","2019-19-19 10:00:00",
            "2019-19-19 10:00:00","20",0)
        modelBestSellerProducts.add(modelBestSellerProduct5)

        val listOrderUkmKategori=ArrayList<ProductUkmKategori>()
        listOrderUkmKategori.add(ProductUkmKategori(1,"Semua",true))
        listOrderUkmKategori.add(ProductUkmKategori(2,"Makanan",false))
        listOrderUkmKategori.add(ProductUkmKategori(3,"Minuman",false))
        listOrderUkmKategori.add(ProductUkmKategori(4,"Jajanan",false))
        val productUkmMessage=ProductUkmMessage("0",modelBestSellerProducts,listOrderUkmKategori)
        val productUkmResponse=ProductUkmResponse(true,productUkmMessage)
        mView?.showProductUkm(productUkmResponse)
    }
}