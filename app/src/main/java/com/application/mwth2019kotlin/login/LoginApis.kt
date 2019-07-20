package com.application.mwth2019kotlin.login

import com.application.mwth2019kotlin.api.ManagerApi
import com.application.mwth2019kotlin.basemvp.BaseMvpPresenter
import com.application.mwth2019kotlin.basemvp.BaseMvpPresenterImpl
import com.application.mwth2019kotlin.basemvp.BaseMvpView
import com.application.mwth2019kotlin.errorhandler.ErrorResponseHandler
import rx.functions.Action1

object LoginContract{
    interface View : BaseMvpView {
        fun progressLogin()
        fun showLogin(loginResponse: LoginResponse)
        fun showReloadLogin(error:String)
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun loginMember(username:String,password:String)
    }
}

class LoginPresenter: BaseMvpPresenterImpl<LoginContract.View>(),LoginContract.Presenter {
    override fun loginMember(username: String, password: String) {
        mView?.progressLogin()
        if(username=="kasir1"){
            val loginData=LoginData("token","kasir")
            val loginResponse=LoginResponse("true",loginData)
            mView?.showLogin(loginResponse)
        }else if(username=="ukm1"){
            val loginData=LoginData("token","ukm")
            val loginResponse=LoginResponse("true",loginData)
            mView?.showLogin(loginResponse)
        }else{
            mView?.showReloadLogin("Username password anda salah")
        }
    }
}