package com.application.mwth2019kotlin.basemvp

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import com.application.mwth2019kotlin.R

/**
 * Created by andrewkhristyan on 10/2/16.
 */
@Suppress("UNCHECKED_CAST")
abstract class BaseMvpFragment<in V : BaseMvpView, T : BaseMvpPresenter<V>>
    : Fragment(), BaseMvpView {


    var ctx: Context? = null
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mPresenter.attachView(this as V)
        ctx=context

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressLayout=view.findViewById(R.id.layout_progress)
    }
    override fun getContext(): Context{
        return ctx!!
    }
    protected lateinit var progressLayout: FrameLayout
    protected abstract var mPresenter: T

    override fun showError(error: String?) {
        Toast.makeText(ctx, error, Toast.LENGTH_LONG).show()
    }

    override fun showError(stringResId: Int) {
        Toast.makeText(ctx, stringResId, Toast.LENGTH_LONG).show()
    }

    override fun showMessage(srtResId: Int) {
        Toast.makeText(ctx, srtResId, Toast.LENGTH_LONG).show()
    }

    override fun showMessage(message: String) {
        Toast.makeText(ctx, message, Toast.LENGTH_LONG).show()
    }

    override fun onDetach() {
        super.onDetach()
        mPresenter.detachView()
        ctx=null
    }
}