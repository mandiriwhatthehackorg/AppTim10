package com.application.mwth2019kotlin.kasir.checkout.customer

import com.application.mwth2019kotlin.basemvp.BaseMvpPresenter
import com.application.mwth2019kotlin.basemvp.BaseMvpPresenterImpl
import com.application.mwth2019kotlin.basemvp.BaseMvpView
import com.application.mwth2019kotlin.kasir.transaksi.TransaksiDetail
import com.application.mwth2019kotlin.kasir.transaksi.TransaksiItem

object CustomerKasirContract{
    interface View : BaseMvpView {
        fun progressCustomerKasir()
        fun showCustomerKasir(customerKasirResponse: CustomerKasirResponse)

        fun progressSkipAdd()
        fun addCustomerKasir()
        fun skipCustomerKasir()
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun loadCustomerKasir(token:String, page:String,ukm_id: String)

        fun skipCustomer(token:String,
                         ukm_id: String,
                         transaction_code: String)
        fun addCustomer(token:String,
                        ukm_id: String,
                        transaction_code: String,customer_name: String)
    }
}


class CustomerKasirPresenter: BaseMvpPresenterImpl<CustomerKasirContract.View>(),CustomerKasirContract.Presenter {
    override fun skipCustomer(token: String, ukm_id: String, transaction_code: String) {
        mView?.progressSkipAdd()
        val transaksiDetail=ArrayList<TransaksiDetail>()
        val transaksiDetail1=TransaksiDetail(1,
            "000111","1","rawon",
            "4","10000","10","100000","2019-19-19 10:00:00",
            "2019-19-19 10:00:00")
        transaksiDetail.add(transaksiDetail1)
        val transaksiDetail2=TransaksiDetail(2,
            "000111","2","sate",
            "4","10000","10","100000","2019-19-19 10:00:00",
            "2019-19-19 10:00:00")
        transaksiDetail.add(transaksiDetail2)
        val transaksiDetail3=TransaksiDetail(3,
            "000111","3","nasi goreng",
            "4","10000","10","100000","2019-19-19 10:00:00",
            "2019-19-19 10:00:00")
        transaksiDetail.add(transaksiDetail3)
        val transaksiDetail4=TransaksiDetail(4,
            "000111","4","bubur ayam",
            "4","10000","10","100000","2019-19-19 10:00:00",
            "2019-19-19 10:00:00")
        transaksiDetail.add(transaksiDetail4)
        val transaksiDetail5=TransaksiDetail(5,
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
        val customerKasirSkipResponse=CustomerKasirSkipResponse(true,message)
        mView?.skipCustomerKasir()

    }

    override fun addCustomer(token: String, ukm_id: String, transaction_code: String, customer_name: String) {
        mView?.progressSkipAdd()
        mView?.addCustomerKasir()
    }

    override fun loadCustomerKasir(token: String, page: String, ukm_id: String) {
        mView?.progressCustomerKasir()
        val name=ArrayList<String>()
        name.add("CUSTOMER1")
        name.add("CUSTOMER2")
        name.add("CUSTOMER3")
        val customerKasirMessage=CustomerKasirMessage("1",name)
        val customerKasirResponse=CustomerKasirResponse(true,customerKasirMessage)
        mView?.showCustomerKasir(customerKasirResponse)
    }
}

