package com.application.mwth2019kotlin.login

import android.os.Bundle
import com.application.mwth2019kotlin.*
import com.application.mwth2019kotlin.basemvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_login.*
import android.content.Intent
import com.application.mwth2019kotlin.kasir.MainKasirActivity
import com.application.mwth2019kotlin.ukm.MainUkmActivity


class LoginActivity : BaseMvpActivity<LoginContract.View, LoginContract.Presenter>(),LoginContract.View{
    override var mPresenter:LoginContract.Presenter=LoginPresenter()
    override fun progressLogin() {
        progressLayout.showView()
    }

    override fun showLogin(loginResponse: LoginResponse) {
        progressLayout.hideView()
        saveCredential(this, loginResponse.data)
        getCredential(this)?.let {
            startMainActivity(it)
        }
    }

    override fun showReloadLogin(error: String) {
        progressLayout.hideView()
        layout_parent.showSnackbar(error,"Close"){
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        getCredential(this)?.let {
            startMainActivity(it)
        }
        setContentView(R.layout.activity_login)
        super.onCreate(savedInstanceState)
        button_login.setOnClickListener {
            mPresenter.loginMember(textinput_username.text.toString(),
                textinput_password.text.toString())
        }
    }

    private fun startMainActivity(data:LoginData){
        if(data.type=="kasir") {
            val intent = Intent(this, MainKasirActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }else{
            val intent = Intent(this, MainUkmActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}
