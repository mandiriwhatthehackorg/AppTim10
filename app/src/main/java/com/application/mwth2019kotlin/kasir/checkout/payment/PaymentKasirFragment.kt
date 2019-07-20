package com.application.mwth2019kotlin.kasir.checkout.payment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import com.application.mwth2019kotlin.*
import com.application.mwth2019kotlin.basemvp.BaseMvpFragment
import com.application.mwth2019kotlin.kasir.checkout.CheckoutActivity
import kotlinx.android.synthetic.main.activity_payment.view.*
import kotlinx.android.synthetic.main.partial_progress.view.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList


class PaymentKasirFragment :  BaseMvpFragment<PaymentKasirContract.View, PaymentKasirContract.Presenter>(),
    PaymentKasirContract.View {
    override var mPresenter: PaymentKasirContract.Presenter=PaymentKasirPresenter()

    override fun progressTotalPrice() {
        viewparent.layout_progress.showView()
    }

    override fun showTotalPrice(totalPriceResponse: TotalPriceResponse) {
        viewparent.layout_progress.hideView()
        viewparent.text_totalprice.text=totalPriceResponse.message.formatRupiah()
        viewparent.button_exact.setOnClickListener {
            uangbayar=totalPriceResponse.message
            loadPayment()
        }
    }


    override fun progressPayment() {
        viewparent.layout_progress.showView()
    }

    override fun showPayment(paymentResponse: PaymentResponse) {
        viewparent.layout_progress.hideView()
        activity?.let {fragmentActivity ->
            paymentResponse.message.change_money?.let {kembalian->
                (fragmentActivity as CheckoutActivity).nextStepPayment(kembalian.toString(),
                    paymentResponse.message.id.toString())
            }
        }
    }
    private lateinit var paymentKasirPriceAdapter: PaymentKasirPriceAdapter

    private lateinit var viewparent:View
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewparent = view
        setCurrency(viewparent.edit_amount)
        paymentKasirPriceAdapter = PaymentKasirPriceAdapter(ArrayList()) {
            uangbayar=it.uang.toString()
            loadPayment()
        }
        viewparent.recycler_price.layoutManager= LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        viewparent.recycler_price.adapter = paymentKasirPriceAdapter
        loadTotalPrice()

        val datas:MutableList<ModelPrice> = ArrayList()
        datas.add(ModelPrice(100000))
        datas.add(ModelPrice(200000))
        datas.add(ModelPrice(300000))
        datas.add(ModelPrice(400000))
        datas.add(ModelPrice(500000))
        datas.add(ModelPrice(600000))
        datas.add(ModelPrice(700000))
        datas.add(ModelPrice(700000))
        datas.add(ModelPrice(900000))
        datas.add(ModelPrice(1000000))
        paymentKasirPriceAdapter.addDatas(datas)
        viewparent.ic_back.setOnClickListener {
            activity?.let {fragmentActivity ->
                (fragmentActivity as CheckoutActivity).prevStepPayment()
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_payment, container, false)
    }
    private var transaksiid: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            transaksiid = it.getString(ARG_PARAM1)
        }
    }
    companion object {

        @JvmStatic
        fun newInstance(transaksiid: String) =
            PaymentKasirFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, transaksiid)
                }
            }
    }

    private fun loadTotalPrice(){
        ctx?.let {
            getCredential(it)?.let {loginData ->
                transaksiid?.let {transid ->
                    mPresenter.loadTotalPrice(loginData.token,transid)
                }
            }
        }
    }

    private var uangbayar: String? = null
    private fun loadPayment(){
        ctx?.let {
            getCredential(it)?.let {loginData ->
                transaksiid?.let {transid ->
                    uangbayar?.let {uang->
                        mPresenter.loadPayment(loginData.token,transid,uang)
                    }

                }
            }
        }
    }

    private fun setCurrency(edt: EditText) {
        edt.addTextChangedListener(object : TextWatcher {
            private var current = ""

            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int,
                count: Int
            ) {

            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) {

            }

            override fun afterTextChanged(s: Editable) {
                if (s.toString() != current) {
                    edt.removeTextChangedListener(this)

                    val local = Locale("id", "id")
                    val replaceable = String.format(
                        "[Rp,.\\s]",
                        NumberFormat.getCurrencyInstance().currency
                            .getSymbol(local)
                    )
                    val cleanString = s.toString().replace(replaceable.toRegex(), "")

                    val parsed: Double
                    parsed = try {
                        java.lang.Double.parseDouble(cleanString)
                    } catch (e: NumberFormatException) {
                        0.00
                    }

                    val formatter = NumberFormat
                        .getCurrencyInstance(local)
                    formatter.maximumFractionDigits = 0
                    formatter.isParseIntegerOnly = true
                    val formatted = formatter.format(parsed)

                    val replace = String.format(
                        "[Rp\\s]",
                        NumberFormat.getCurrencyInstance().currency
                            .getSymbol(local)
                    )
                    val clean = formatted.replace(replace.toRegex(), "")

                    current = formatted
                    edt.setText(clean)
                    edt.setSelection(clean.length)
                    edt.addTextChangedListener(this)
                }
            }
        })

        edt.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_DONE) {
                uangbayar=edt.text.toString()
                    .replace(",","")
                    .replace(".","")
                loadPayment()
                true
            } else {
                false
            }
        }
    }

}
private const val ARG_PARAM1 = "transaksiid"