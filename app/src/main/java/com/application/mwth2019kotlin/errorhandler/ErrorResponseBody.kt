package com.application.mwth2019kotlin.errorhandler

import com.application.mwth2019kotlin.fromJson
import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import retrofit2.Response
import java.io.IOException




/**
 * Created by andrewkhristyan on 10/31/16.
 */


data class ErrorResponseBody(var code: Int, var message: String) {

    override fun toString(): String = "{code:$code, message:\"$message\"}"

    companion object {

        val UNKNOWN_ERROR = 0

        private val moshi = Moshi.Builder().build()

        fun parseErrorNew(response: Response<*>?): ErrorResponse {
            var code = 0
            var msg="Unknown error"
            var tes="{code:$code, message:\"$msg\"}"
            (response?.errorBody())?.let {
                response.let {
                    code=it.code()
                    msg=it.message()
                }
                tes="{code:$code, message:\"$msg\"}"
            }
            return moshi.fromJson(tes)
        }
        fun parseError(response: Response<*>?): ErrorResponseBody? {
            return (response?.errorBody())?.let {
                try {

                    moshi.fromJson(it.string())
                } catch (ignored: IOException) {
                    null
                }
            }
        }
    }

}

