package com.application.mwth2019kotlin.ukm.bizops


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.application.mwth2019kotlin.R
import kotlinx.android.synthetic.main.fragment_bizops_ukm.view.*

class BizopsFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.alurkas.setOnClickListener {
            context?.let {ctx->
                val intent= Intent(ctx,AlurkasActivity::class.java)
                startActivity(intent)
            }
        }
        view.kreditskoring.setOnClickListener {
            context?.let {ctx->
                val intent= Intent(ctx,KreditSkoringActivity::class.java)
                startActivity(intent)
            }
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bizops_ukm, container, false)
    }


}
