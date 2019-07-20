package com.application.mwth2019kotlin.kasir.checkout.done

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.application.mwth2019kotlin.R
import com.application.mwth2019kotlin.formatRupiah
import com.application.mwth2019kotlin.kasir.checkout.CheckoutActivity
import com.application.mwth2019kotlin.kasir.invoice.KasirInvoiceActivity
import kotlinx.android.synthetic.main.activity_done.view.*

class DoneKasirFragment : Fragment() {

    private lateinit var viewparent: View
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewparent = view
        viewparent.button_done.setOnClickListener {
            activity?.let {fragmentActivity ->
                (fragmentActivity as CheckoutActivity).nextStepDone()
            }
        }
        changemoney?.let {
            viewparent.text_changemoney.text=it.formatRupiah()
        }
        viewparent.button_preview.setOnClickListener {
            transid?.let {id->
                context?.let {ctx->
                    val intent= Intent(ctx, KasirInvoiceActivity::class.java)
                    intent.putExtra("transaction_id",id.toInt())
                    startActivity(intent)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_done, container, false)
    }

    private var changemoney: String? = null
    private var transid: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            changemoney = it.getString(ARG_PARAM1)
            transid=it.getString(ARG_PARAM2)
        }
    }
    companion object {

        @JvmStatic
        fun newInstance(changemoney: String,transid:String) =
            DoneKasirFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, changemoney)
                    putString(ARG_PARAM2, transid)
                }
            }
    }
}
private const val ARG_PARAM1 = "changemoney"
private const val ARG_PARAM2 = "transid"
