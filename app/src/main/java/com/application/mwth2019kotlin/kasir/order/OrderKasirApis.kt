package com.application.mwth2019kotlin.kasir.order

import android.view.View
import android.widget.TextView
import com.application.mwth2019kotlin.basemvp.BaseMvpPresenter
import com.application.mwth2019kotlin.basemvp.BaseMvpPresenterImpl
import com.application.mwth2019kotlin.basemvp.BaseMvpView
import com.application.mwth2019kotlin.errorhandler.ErrorResponseHandler
import com.application.mwth2019kotlin.kasir.home.ModelBestSellerProduct
import rx.functions.Action1

object OrderKasirContract{
    interface ViewOrder : BaseMvpView {
        fun progressOrderKasir()
        fun showOrderKasir(orderKasirResponse: OrderKasirResponse)


        fun progressOrderDetailKasir()
        fun showOrderDetailKasir(orderDetailKasirResponse: OrderDetailKasirResponse)

        fun progressOrderCartKasir()
        fun showOrderCartKasirPlus(position:Int,
                               text_qty: TextView, frame_plus: View, frame_minus: View,
                               orderCartResponse: OrderCartResponse)
        fun showOrderCartKasirMin(position:Int,
                                   text_qty: TextView, frame_minus: View,
                                   orderCartResponse: OrderCartResponse)

        fun progressClearCart()
        fun showClearCart(clearCartResponse: ClearCartResponse)
    }

    interface Presenter : BaseMvpPresenter<ViewOrder> {
        fun loadOrderKasir(token:String,
                           page:String,
                           ukm_id:String,
                           category:String,
                           code_cart:String)

        fun loadOrderDetailKasir(token:String,
                                 page:String,
                                 code_cart:String)

        fun loadOrderCartPlus(position: Int,
                              text_qty: TextView, frame_plus: View, frame_minus: View,
                              token:String, code_cart:String,
                              product_id:String)

        fun loadOrderCartMin(position: Int,
                              text_qty: TextView,frame_minus: View,
                              token:String, code_cart:String,
                              product_id:String)

        fun loadClearCart(token:String,code_cart: String)
    }
}

class OrderKasirPresenter: BaseMvpPresenterImpl<OrderKasirContract.ViewOrder>(),OrderKasirContract.Presenter {
    override fun loadClearCart(token: String, code_cart: String) {
        mView?.progressClearCart()
        val clearCartResponse=ClearCartResponse(true,"cart sudah di hapus")
        mView?.showClearCart(clearCartResponse)
    }

    override fun loadOrderCartPlus(position: Int,text_qty: TextView,frame_plus:View,frame_minus: View,
                                   token: String, code_cart: String, product_id: String) {
        mView?.progressOrderCartKasir()
        val modelBestSellerProduct=ModelBestSellerProduct(1,
            "1","1","Sate","10",
            "10000","0.5","15000",
            "https://upload.wikimedia.org/wikipedia/commons/a/ad/Sate_Ponorogo.jpg","description","aktif","2019-19-19 10:00:00",
            "2019-19-19 10:00:00","20",0)
        val orderDetailKasirCart=OrderDetailKasirCart(1,
            "1","1","1","2019-19-19 10:00:00",
            "2019-19-19 10:00:00",modelBestSellerProduct)
        val orderCartResponse=OrderCartResponse(true,orderDetailKasirCart)
        mView?.showOrderCartKasirPlus(position,text_qty, frame_plus, frame_minus, orderCartResponse)
    }

    override fun loadOrderCartMin(position: Int,text_qty: TextView,frame_minus: View,
                                   token: String, code_cart: String, product_id: String) {
        mView?.progressOrderCartKasir()
        val modelBestSellerProduct=ModelBestSellerProduct(1,
            "1","1","Sate","10",
            "10000","0.5","15000",
            "https://upload.wikimedia.org/wikipedia/commons/a/ad/Sate_Ponorogo.jpg","description","aktif","2019-19-19 10:00:00",
            "2019-19-19 10:00:00","20",0)
        val orderDetailKasirCart=OrderDetailKasirCart(1,
            "1","1","1","2019-19-19 10:00:00",
            "2019-19-19 10:00:00",modelBestSellerProduct)
        val orderCartResponse=OrderCartResponse(true,orderDetailKasirCart)
        mView?.showOrderCartKasirMin(position,text_qty, frame_minus, orderCartResponse)
    }

    override fun loadOrderDetailKasir(token: String, page: String, code_cart: String) {
        mView?.progressOrderDetailKasir()
        val listOrderDetailKasirCart=ArrayList<OrderDetailKasirCart>()
        listOrderDetailKasirCart.add(
            OrderDetailKasirCart(1,"1","1"
        ,"1","2019-19-19 10:00:00","2019-19-19 10:00:00", ModelBestSellerProduct(
                    1,
                    "1","1","Sate","10",
                    "10000","0.5","15000",
                    "https://upload.wikimedia.org/wikipedia/commons/a/ad/Sate_Ponorogo.jpg","description","aktif","2019-19-19 10:00:00",
                    "2019-19-19 10:00:00","20",0
                )))
        listOrderDetailKasirCart.add(
            OrderDetailKasirCart(1,"2","2"
                ,"1","2019-19-19 10:00:00","2019-19-19 10:00:00", ModelBestSellerProduct(
                    2,
                    "2","1","Nasi Goreng","10",
                    "10000","0.5","15000",
                    "https://i2.wp.com/resepkoki.id/wp-content/uploads/2018/01/Resep-Nasi-Goreng-Rendang.jpg","description","aktif","2019-19-19 10:00:00",
                    "2019-19-19 10:00:00","20",0
                )))
        listOrderDetailKasirCart.add(
            OrderDetailKasirCart(1,"3","3"
                ,"1","2019-19-19 10:00:00","2019-19-19 10:00:00", ModelBestSellerProduct(
                    3,
                    "3","1","Soto","10",
                    "10000","0.5","15000",
                    "https://doyanresep.com/wp-content/uploads/2016/06/resep-soto-lamongan-1.jpg","description","aktif","2019-19-19 10:00:00",
                    "2019-19-19 10:00:00","20",0
                )))
        val orderDetailKasirMessage=OrderDetailKasirMessage("0",listOrderDetailKasirCart,100000)
        val orderDetailKasirResponse=OrderDetailKasirResponse(true,orderDetailKasirMessage)
        mView?.showOrderDetailKasir(orderDetailKasirResponse)

    }
    override fun loadOrderKasir(token: String, page: String, ukm_id: String, category: String, code_cart: String) {
        mView?.progressOrderKasir()
        val listModelBestSellerProduct=ArrayList<ModelBestSellerProduct>()
        val modelBestSellerProduct1=ModelBestSellerProduct(1,
            "1","1","Sate","10",
            "10000","0.5","15000",
            "https://upload.wikimedia.org/wikipedia/commons/a/ad/Sate_Ponorogo.jpg","description","aktif","2019-19-19 10:00:00",
            "2019-19-19 10:00:00","20",0)
        listModelBestSellerProduct.add(modelBestSellerProduct1)
        val modelBestSellerProduct2=ModelBestSellerProduct(2,
            "2","1","Nasi Goreng","10",
            "10000","0.5","15000",
            "https://i2.wp.com/resepkoki.id/wp-content/uploads/2018/01/Resep-Nasi-Goreng-Rendang.jpg","description","aktif","2019-19-19 10:00:00",
            "2019-19-19 10:00:00","20",0)
        listModelBestSellerProduct.add(modelBestSellerProduct2)
        val modelBestSellerProduct3=ModelBestSellerProduct(3,
            "3","1","Soto","10",
            "10000","0.5","15000",
            "https://doyanresep.com/wp-content/uploads/2016/06/resep-soto-lamongan-1.jpg","description","aktif","2019-19-19 10:00:00",
            "2019-19-19 10:00:00","20",0)
        listModelBestSellerProduct.add(modelBestSellerProduct3)
        val modelBestSellerProduct4=ModelBestSellerProduct(4,
            "4","1","https://upload.wikimedia.org/wikipedia/commons/a/a8/Bubur_ayam_chicken_porridge.JPG","10",
            "10000","0.5","15000",
            "placeholder","description","aktif","2019-19-19 10:00:00",
            "2019-19-19 10:00:00","20",0)
        listModelBestSellerProduct.add(modelBestSellerProduct4)
        val modelBestSellerProduct5=ModelBestSellerProduct(5,
            "5","1","Rawon","10",
            "10000","0.5","15000",
            "https://selerasa.com/wp-content/uploads/2018/11/rawon-daging-bumbu-instan-500x375.jpg","description","aktif","2019-19-19 10:00:00",
            "2019-19-19 10:00:00","20",0)
        listModelBestSellerProduct.add(modelBestSellerProduct5)

        val listOrderKasirKategori=ArrayList<OrderKasirKategori>()
        listOrderKasirKategori.add(OrderKasirKategori(0,"Semua",true))
        listOrderKasirKategori.add(OrderKasirKategori(1,"Makanan",false))
        listOrderKasirKategori.add(OrderKasirKategori(2,"Minuman",false))
        listOrderKasirKategori.add(OrderKasirKategori(4,"Jajanan",false))
        val orderKasirMessage=OrderKasirMessage("0",listModelBestSellerProduct,listOrderKasirKategori,200000)
        val orderKasirResponse=OrderKasirResponse(true,orderKasirMessage)
        mView?.showOrderKasir(orderKasirResponse)
    }
}