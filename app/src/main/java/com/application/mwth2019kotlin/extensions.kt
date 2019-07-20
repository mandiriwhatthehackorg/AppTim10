package com.application.mwth2019kotlin

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import com.application.mwth2019kotlin.kasir.home.HomeOperator
import com.application.mwth2019kotlin.login.LoginData
import com.application.mwth2019kotlin.ukm.home.HomeOperatorUkm
import com.squareup.moshi.Moshi
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*


@SuppressLint("RestrictedApi")
fun BottomNavigationView.disableShiftMode() {
    val TAG="shiftingmode"
    val menuView = getChildAt(0) as BottomNavigationMenuView
    try {
        val shiftingMode = menuView::class.java.getDeclaredField("mShiftingMode")
        shiftingMode.isAccessible = true
        shiftingMode.setBoolean(menuView, false)
        shiftingMode.isAccessible = false
        for (i in 0 until menuView.childCount) {
            val item:BottomNavigationItemView = menuView.getChildAt(i) as BottomNavigationItemView
            item.setShifting(false)
            // set once again checked value, so view will be updated
            item.setChecked(item.itemData.isChecked)
        }
    } catch (e: NoSuchFieldException) {

        Log.e(TAG, "Unable to get shift mode field", e)
    } catch (e: IllegalStateException) {
        Log.e(TAG, "Unable to change value of shift mode", e)
    }
}

fun View.showView(){
    this.visibility=VISIBLE
}
fun View.hideView(){
    this.visibility= GONE
}

fun View.showSnackbar(message:String,action:String,onClick: (View) -> Unit){
    val snackbar=Snackbar.make(this,message,Snackbar.LENGTH_INDEFINITE)
    snackbar.setAction(action,onClick).show()
}
fun saveCredential(ctx: Context, data: LoginData){
    val moshi = Moshi.Builder().build()
    val jsonAdapter = moshi.adapter<LoginData>(LoginData::class.java)
    val json = jsonAdapter.toJson(data)
    val sharedPref: SharedPreferences = ctx.getSharedPreferences("simplepos", Context.MODE_PRIVATE)
    val editor: SharedPreferences.Editor = sharedPref.edit()
    editor.putString("credential", json)
    editor.commit()
}
fun getCredential(ctx:Context):LoginData?{
    val sharedPref: SharedPreferences = ctx.getSharedPreferences("simplepos", Context.MODE_PRIVATE)
    sharedPref.getString("credential", null)?.let {
        val moshi = Moshi.Builder().build()
        return moshi.fromJson(it)
    }
    return null
}

fun saveOperator(ctx: Context,data: HomeOperator){
    val moshi = Moshi.Builder().build()
    val jsonAdapter = moshi.adapter<HomeOperator>(HomeOperator::class.java)
    val json = jsonAdapter.toJson(data)
    val sharedPref: SharedPreferences = ctx.getSharedPreferences("simplepos", Context.MODE_PRIVATE)
    val editor: SharedPreferences.Editor = sharedPref.edit()
    editor.putString("operator", json)
    editor.commit()
}
fun getOperator(ctx:Context):HomeOperator?{
    val sharedPref: SharedPreferences = ctx.getSharedPreferences("simplepos", Context.MODE_PRIVATE)
    sharedPref.getString("operator", null)?.let {
        val moshi = Moshi.Builder().build()
        return moshi.fromJson(it)
    }
    return null
}

fun saveOperatorUkm(ctx: Context,data: HomeOperatorUkm){
    val moshi = Moshi.Builder().build()
    val jsonAdapter = moshi.adapter<HomeOperatorUkm>(HomeOperatorUkm::class.java)
    val json = jsonAdapter.toJson(data)
    val sharedPref: SharedPreferences = ctx.getSharedPreferences("simplepos", Context.MODE_PRIVATE)
    val editor: SharedPreferences.Editor = sharedPref.edit()
    editor.putString("operator", json)
    editor.commit()
}
fun getOperatorUkm(ctx:Context):HomeOperatorUkm?{
    val sharedPref: SharedPreferences = ctx.getSharedPreferences("simplepos", Context.MODE_PRIVATE)
    sharedPref.getString("operator", null)?.let {
        val moshi = Moshi.Builder().build()
        return moshi.fromJson(it)
    }
    return null
}
fun String.formatTanggal(format: String,format_init: String): String {
    val initialFormat = SimpleDateFormat(format_init, Locale.ENGLISH)
    val format_output = SimpleDateFormat(format, Locale.US)
    val date = initialFormat.parse(this)
    return format_output.format(date).toString()
}
fun String.formatRupiah():String{
    val localeID = Locale("in", "ID")
    val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
    return formatRupiah.format(this.toDouble())
}

inline fun <reified T> Moshi.fromJson(json: String): T = this.adapter(T::class.java).fromJson(json)!!
