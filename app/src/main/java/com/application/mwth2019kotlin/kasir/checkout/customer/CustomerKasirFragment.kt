package com.application.mwth2019kotlin.kasir.checkout.customer

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.application.library.EndlessRecyclerViewScrollListener
import com.application.mwth2019kotlin.*
import com.application.mwth2019kotlin.basemvp.BaseMvpFragment
import com.application.mwth2019kotlin.kasir.checkout.CheckoutActivity
import kotlinx.android.synthetic.main.activity_customers.view.*
import kotlinx.android.synthetic.main.partial_progress.view.*
import kotlinx.android.synthetic.main.partial_progress_paging.view.*

class CustomerKasirFragment : BaseMvpFragment<CustomerKasirContract.View, CustomerKasirContract.Presenter>(),
    CustomerKasirContract.View {
    override fun progressSkipAdd() {
        viewparent.layout_progress.showView()
    }

    override fun addCustomerKasir() {
        viewparent.layout_progress.hideView()
        activity?.let {
            (it as CheckoutActivity).nextStepCustomer("1")
        }
    }

    override fun skipCustomerKasir() {
        viewparent.layout_progress.hideView()
        activity?.let {
            (it as CheckoutActivity).nextStepCustomer("1")
        }
    }

    override var mPresenter: CustomerKasirContract.Presenter=CustomerKasirPresenter()
    override fun progressCustomerKasir() {
        viewparent.layout_progress.showView()
    }

    override fun showCustomerKasir(customerKasirResponse: CustomerKasirResponse) {
        viewparent.layout_progress.hideView()
        customerKasirAdapter.setDatas(customerKasirResponse.message.customer)
        page++
    }

    private lateinit var customerKasirAdapter: CustomerKasirAdapter
    private lateinit var viewparent:View
    private lateinit var linearLayoutManager:LinearLayoutManager
    private var page=0
    private lateinit var customername: String
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewparent = view
        customerKasirAdapter = CustomerKasirAdapter(ArrayList()) {
            customername=it
            addCustomer()
        }
        viewparent.card_plus.setOnClickListener{
            customername=viewparent.edit_username.text.toString()
            addCustomer()
        }
        linearLayoutManager=LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)
        viewparent.recycler_customer.layoutManager= linearLayoutManager
        viewparent.recycler_customer.adapter = customerKasirAdapter
        ctx?.let {
            getCredential(it)?.let { loginData->
                getOperator(it)?.let {homeOperator ->
                    mPresenter.loadCustomerKasir(loginData.token,page.toString(),homeOperator.ukm_id)
                }
            }
        }
        viewparent.button_skip.setOnClickListener {
            skipCustomer()
        }
        viewparent.ic_back.setOnClickListener {
            activity?.let {
                (it as CheckoutActivity).prevStepCustomer()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_customers, container, false)
    }

    private fun addCustomer(){
        ctx?.let {
            getCredential(it)?.let {loginData ->
                getOperator(it)?.let {homeOperator ->
                    mPresenter.addCustomer(loginData.token,
                        homeOperator.ukm_id,homeOperator.id.toString()+"cart",
                        customername)
                }
            }
        }
    }

    private fun skipCustomer(){
        ctx?.let {
            getCredential(it)?.let {loginData ->
                getOperator(it)?.let {homeOperator ->
                    mPresenter.skipCustomer(loginData.token,
                        homeOperator.ukm_id,homeOperator.id.toString()+"cart")
                }
            }
        }
    }
}
