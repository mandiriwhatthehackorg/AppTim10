package com.application.mwth2019kotlin.api

import com.application.mwth2019kotlin.login.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST
import rx.Observable

interface Service{
    /*@Headers("Accept:application/json")
    @FormUrlEncoded
    @POST("login")
    fun loginMember(@Field("username") username:String, @Field("password") password:String): Observable<LoginResponse>*/
}
