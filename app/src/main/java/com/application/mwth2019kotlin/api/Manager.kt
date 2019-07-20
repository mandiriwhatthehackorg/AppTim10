package com.application.mwth2019kotlin.api

import com.application.mwth2019kotlin.BuildConfig
import com.application.mwth2019kotlin.login.LoginResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


object ManagerApi {
    private const val SERVER: String = BuildConfig.SERVER_URL


    private lateinit var mServiceAPI: Service
    init {
        val retrofit = initRetrofit()
        initServices(retrofit)
    }

    private fun initRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder().apply {
            networkInterceptors().add(Interceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
                    .method(original.method(), original.body())
                    .build()
                chain.proceed(request)
            })
            addInterceptor(interceptor)
        }

        return Retrofit.Builder().baseUrl(SERVER)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(createMoshiConverter())
            .client(client.build())
            .build()
    }

    private fun createMoshiConverter(): MoshiConverterFactory = MoshiConverterFactory.create()

    private fun initServices(retrofit: Retrofit) {
        mServiceAPI = retrofit.create(Service::class.java)
    }


    /*fun loginMember(username:String,password:String): Observable<LoginResponse> =
        mServiceAPI.loginMember(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())*/

}