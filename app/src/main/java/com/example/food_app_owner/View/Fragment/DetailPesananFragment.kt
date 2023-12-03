package com.example.food_app_owner.View.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.food_app_owner.Model.Adapter.MenuStrukAdapter
import com.example.food_app_owner.R

class DetailPesananFragment : Fragment() {
    lateinit var MenuStrukAdapter: Adapter
    lateinit var tvNamaPemesan: TextView
    lateinit var tvAlamatPemesan: TextView
    lateinit var tvBiayaPesanan: TextView
    lateinit var tvBiayaAntar: TextView
    lateinit var tvTotalBiaya: TextView
    lateinit var detailPesanRecyclerView: RecyclerView
    lateinit var btnUpStatus: Button
    lateinit var spinnerStatus: Spinner


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_pesanan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvNamaPemesan = view.findViewById(R.id.tvNamaPemesan)
        tvAlamatPemesan = view.findViewById(R.id.tvAlamatPemesan)
        tvBiayaPesanan = view.findViewById(R.id.tvBiayaPesanan)
        tvBiayaAntar = view.findViewById(R.id.tvBiayaAntar)
        tvTotalBiaya = view.findViewById(R.id.tvTotalBiaya)

        detailPesanRecyclerView = view.findViewById(R.id.detailPesanRecyclerView)
        btnUpStatus = view.findViewById(R.id.btnUpStatus)
        spinnerStatus = view.findViewById(R.id.spinnerStatus)
    }
}