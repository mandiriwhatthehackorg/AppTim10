package com.application.mwth2019kotlin.ukm.bizops

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.application.mwth2019kotlin.R
import kotlinx.android.synthetic.main.activity_alurkas.*
import kotlinx.android.synthetic.main.layout_formtambahkas.view.*
import kotlinx.android.synthetic.main.layout_formtambahkas_selasai.view.*


class AlurkasActivity : AppCompatActivity() {
    private lateinit var alertDialog: AlertDialog
    private lateinit var alertDialog1: AlertDialog

    @SuppressLint("InflateParams")
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

        fab_addalurkas.setOnClickListener {
            val dialogView = layoutInflater.inflate(R.layout.layout_formtambahkas, null);
            dialogView.text_tambah.setOnClickListener {
                alertDialog.dismiss()
                val dialogView1 = layoutInflater.inflate(R.layout.layout_formtambahkas_selasai, null);
                dialogView1.text_oke.setOnClickListener {
                    alertDialog1.dismiss()
                }
                val alertDialogBuilder = AlertDialog.Builder(
                    this
                )
                alertDialogBuilder.setView(dialogView1)
                alertDialog1 = alertDialogBuilder.create()

                // menampilkan alert dialog
                alertDialog1.show()
            }
            val alertDialogBuilder = AlertDialog.Builder(
                this
            )
            alertDialogBuilder.setView(dialogView)
            alertDialog = alertDialogBuilder.create()

            // menampilkan alert dialog
            alertDialog.show()
        }

    }
}
