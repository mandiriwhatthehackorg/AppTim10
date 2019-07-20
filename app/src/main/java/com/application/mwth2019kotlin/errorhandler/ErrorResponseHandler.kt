package com.application.mwth2019kotlin.errorhandler

import android.support.annotation.StringRes
import android.text.TextUtils
import android.util.Log
import com.application.mwth2019kotlin.BuildConfig
import com.application.mwth2019kotlin.R
import com.application.mwth2019kotlin.basemvp.BaseMvpView
import org.json.JSONObject
import retrofit2.HttpException
import rx.functions.Action1
import java.lang.ref.WeakReference
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by andrewkhristyan on 10/31/16.
 */

class ErrorResponseHandler(view: BaseMvpView? = null,
                           private val mShowError: Boolean = false,
                           val onFailure: (Throwable, ErrorResponse, Boolean) -> Unit)
: Action1<Throwable> {

    private val mViewReference: WeakReference<BaseMvpView>

    init {
        mViewReference = WeakReference<BaseMvpView>(view)
    }

    override fun call(throwable: Throwable) {
        var isNetwork = false
        var errorBody = ErrorResponse(0,false,"Internet connection unavailable")
        if (isNetworkError(throwable)) {
            isNetwork = true
            showMessage(R.string.str_error_connection)
            errorBody= ErrorResponse(0,false,"Internet connection unavailable")
            onFailure(throwable, errorBody, isNetwork)
        } else if (throwable is HttpException) {
            val response=throwable.response()
            response?.errorBody()?.string()?.let {data->
                if(BuildConfig.DEBUG){
                    Log.e("throwable",data)
                }
                val jsonObject=JSONObject(data)


                errorBody.code=response.code()
                errorBody.status=jsonObject.getBoolean("result")
                errorBody.message=jsonObject.getString("message")

            }
            onFailure(throwable, errorBody, isNetwork)
        }


    }

    private fun isNetworkError(throwable: Throwable): Boolean {
        return throwable is SocketException ||
                throwable is UnknownHostException ||
                throwable is SocketTimeoutException
    }

    private fun handleError(errorBody: ErrorResponseBody) {
        if (errorBody.code != ErrorResponseBody.UNKNOWN_ERROR) {
            showErrorIfRequired(R.string.str_server_error)
        }
    }

    private fun showErrorIfRequired(@StringRes strResId: Int) {
        if (mShowError) {
            mViewReference.get()?.showError(strResId)
        }
    }

    private fun showErrorIfRequired(error: String) {
        if (mShowError && !TextUtils.isEmpty(error)) {
            mViewReference.get()?.showError(error)
        }
    }

    private fun showMessage(@StringRes strResId: Int) {
        mViewReference.get()?.showMessage(strResId)
    }

    private fun showMessage(error: String) {
        if (error.isNotEmpty()) {
            mViewReference.get()?.showError(error)
        }
    }

}