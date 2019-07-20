package com.application.mwth2019kotlin.login

data class LoginResponse(val status:String,
                         var data:LoginData)

data class LoginData(val token:String,
                     val type:String)