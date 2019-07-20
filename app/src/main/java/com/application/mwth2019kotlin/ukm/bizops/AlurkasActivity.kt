package com.application.mwth2019kotlin.ukm.bizops

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.application.mwth2019kotlin.R
import kotlinx.android.synthetic.main.activity_alurkas.*

class AlurkasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alurkas)
        val adapterAlurKas = AdapterAlurKas(ArrayList()) {}
        val linearLayoutManagerDetail= LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler_kas.layoutManager= linearLayoutManagerDetail
        recycler_kas.adapter = adapterAlurKas
        val listModelAlurKas=ArrayList<ModelAlurKas>()
        listModelAlurKas.add(ModelAlurKas("Judul Kas","Rp. 10.000","115001230131","22 - April - 2019","Pembelanjaan Buah Melon 5 Kg"))
        listModelAlurKas.add(ModelAlurKas("Judul Kas","Rp. 10.000","115001230131","22 - April - 2019","Pembelanjaan Buah Melon 5 Kg"))
        listModelAlurKas.add(ModelAlurKas("Judul Kas","Rp. 10.000","115001230131","22 - April - 2019","Pembelanjaan Buah Melon 5 Kg"))
        listModelAlurKas.add(ModelAlurKas("Judul Kas","Rp. 10.000","115001230131","22 - April - 2019","Pembelanjaan Buah Melon 5 Kg"))
        listModelAlurKas.add(ModelAlurKas("Judul Kas","Rp. 10.000","115001230131","22 - April - 2019","Pembelanjaan Buah Melon 5 Kg"))
        listModelAlurKas.add(ModelAlurKas("Judul Kas","Rp. 10.000","115001230131","22 - April - 2019","Pembelanjaan Buah Melon 5 Kg"))
        listModelAlurKas.add(ModelAlurKas("Judul Kas","Rp. 10.000","115001230131","22 - April - 2019","Pembelanjaan Buah Melon 5 Kg"))
        listModelAlurKas.add(ModelAlurKas("Judul Kas","Rp. 10.000","115001230131","22 - April - 2019","Pembelanjaan Buah Melon 5 Kg"))
        adapterAlurKas.setDatas(listModelAlurKas)

    }
}
