package com.application.mwth2019kotlin.ukm.portalkredit


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.application.mwth2019kotlin.R
import kotlinx.android.synthetic.main.fragment_portalkredit_ukm.*

class PortalKreditFragment : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let {
            val adapterKredit = AdapterKredit(ArrayList()) {}
            val linearLayoutManagerDetail= LinearLayoutManager(it, LinearLayoutManager.VERTICAL, false)
            recycler_portalkredit.layoutManager= linearLayoutManagerDetail
            recycler_portalkredit.adapter = adapterKredit
            val listModelKredit=ArrayList<ModelKredit>()
            listModelKredit.add(ModelKredit("https://www.bankmandiri.co.id/documents/20143/32497/KMK.jpg/3dad3a06-6496-de00-fbff-fe2c6e234b7d?t=1509072764150",
                "Kredit Usaha Rakyat","Kredit Usaha Rakyat (KUR) adalah kredit/pembiayaan yang diberikan oleh perbankan kepada UMKMK yang feasible tapi belum bankable. KUR bertujuan untuk meningkatkan dan memperluas pelayanan Bank kepada UMKM produktif, meningkatkan kapasitas daya saing UMKM, mendorong pertumbuhan ekonomi dan penyerapan tenaga kerja, serta menanggulangi kemiskinan"))
            listModelKredit.add(ModelKredit("https://www.bankmandiri.co.id/documents/20143/32497/KMK.jpg/3dad3a06-6496-de00-fbff-fe2c6e234b7d?t=1509072764150",
                "Kredit Usaha Rakyat","Kredit Usaha Rakyat (KUR) adalah kredit/pembiayaan yang diberikan oleh perbankan kepada UMKMK yang feasible tapi belum bankable. KUR bertujuan untuk meningkatkan dan memperluas pelayanan Bank kepada UMKM produktif, meningkatkan kapasitas daya saing UMKM, mendorong pertumbuhan ekonomi dan penyerapan tenaga kerja, serta menanggulangi kemiskinan"))
            listModelKredit.add(ModelKredit("https://www.bankmandiri.co.id/documents/20143/32497/KMK.jpg/3dad3a06-6496-de00-fbff-fe2c6e234b7d?t=1509072764150",
                "Kredit Usaha Rakyat","Kredit Usaha Rakyat (KUR) adalah kredit/pembiayaan yang diberikan oleh perbankan kepada UMKMK yang feasible tapi belum bankable. KUR bertujuan untuk meningkatkan dan memperluas pelayanan Bank kepada UMKM produktif, meningkatkan kapasitas daya saing UMKM, mendorong pertumbuhan ekonomi dan penyerapan tenaga kerja, serta menanggulangi kemiskinan"))
            listModelKredit.add(ModelKredit("https://www.bankmandiri.co.id/documents/20143/32497/KMK.jpg/3dad3a06-6496-de00-fbff-fe2c6e234b7d?t=1509072764150",
                "Kredit Usaha Rakyat","Kredit Usaha Rakyat (KUR) adalah kredit/pembiayaan yang diberikan oleh perbankan kepada UMKMK yang feasible tapi belum bankable. KUR bertujuan untuk meningkatkan dan memperluas pelayanan Bank kepada UMKM produktif, meningkatkan kapasitas daya saing UMKM, mendorong pertumbuhan ekonomi dan penyerapan tenaga kerja, serta menanggulangi kemiskinan"))
            listModelKredit.add(ModelKredit("https://www.bankmandiri.co.id/documents/20143/32497/KMK.jpg/3dad3a06-6496-de00-fbff-fe2c6e234b7d?t=1509072764150",
                "Kredit Usaha Rakyat","Kredit Usaha Rakyat (KUR) adalah kredit/pembiayaan yang diberikan oleh perbankan kepada UMKMK yang feasible tapi belum bankable. KUR bertujuan untuk meningkatkan dan memperluas pelayanan Bank kepada UMKM produktif, meningkatkan kapasitas daya saing UMKM, mendorong pertumbuhan ekonomi dan penyerapan tenaga kerja, serta menanggulangi kemiskinan"))
            listModelKredit.add(ModelKredit("https://www.bankmandiri.co.id/documents/20143/32497/KMK.jpg/3dad3a06-6496-de00-fbff-fe2c6e234b7d?t=1509072764150",
                "Kredit Usaha Rakyat","Kredit Usaha Rakyat (KUR) adalah kredit/pembiayaan yang diberikan oleh perbankan kepada UMKMK yang feasible tapi belum bankable. KUR bertujuan untuk meningkatkan dan memperluas pelayanan Bank kepada UMKM produktif, meningkatkan kapasitas daya saing UMKM, mendorong pertumbuhan ekonomi dan penyerapan tenaga kerja, serta menanggulangi kemiskinan"))
            adapterKredit.setDatas(listModelKredit)
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_portalkredit_ukm, container, false)
    }


}
