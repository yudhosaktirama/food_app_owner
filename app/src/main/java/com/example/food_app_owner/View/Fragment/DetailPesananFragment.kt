package com.example.food_app_owner.View.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.food_app_owner.Model.Adapter.MenuStrukAdapter
import com.example.food_app_owner.R
import com.example.food_app_owner.ViewModel.PesananViewModel
import com.google.firebase.firestore.FirebaseFirestore

class DetailPesananFragment : Fragment() {
    lateinit var tvNamaPemesan: TextView
    lateinit var tvAlamatPemesan: TextView
    lateinit var tvBiayaPesanan: TextView
    lateinit var tvBiayaAntar: TextView
    lateinit var tvTotalBiaya: TextView
    lateinit var detailPesanRecyclerView: RecyclerView
    lateinit var btnUpStatus: ImageView
    lateinit var spinnerStatus: Spinner
    lateinit var firebaseFirestore: FirebaseFirestore
    val pesananViewModel: PesananViewModel by activityViewModels()


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
        firebaseFirestore = FirebaseFirestore.getInstance()

        detailPesanRecyclerView = view.findViewById(R.id.detailPesanRecyclerView)
        btnUpStatus = view.findViewById(R.id.btnUpStatus)
        spinnerStatus = view.findViewById(R.id.spinnerStatus)

        pesananViewModel.listDetail.observe(viewLifecycleOwner){newValue ->
            detailPesanRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            detailPesanRecyclerView.adapter = MenuStrukAdapter(newValue,requireContext())
            val subHarga: MutableList<Int> = mutableListOf()
            for (i in newValue){
                subHarga.add(i.harga)
            }
            tvBiayaPesanan.text = subHarga.sum().toString()
        }

        setNilai()

        btnUpStatus.setOnClickListener {
            val id = arguments?.getString("id")
            val status = updateStatus()
            firebaseFirestore.collection("pesanan").document(id!!).update("status",status).addOnSuccessListener {
                Toast.makeText(requireContext(), "Berhasil Update", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(requireContext(), "Gagal Update", Toast.LENGTH_SHORT).show()
            }
        }


    }
    fun setNilai(){
        tvNamaPemesan.text = arguments?.getString("nama")
        tvAlamatPemesan.text = arguments?.getString("alamat")
        tvTotalBiaya.text = arguments?.getInt("harga",0).toString()

    }

    fun updateStatus(): String{
        val listStatus = resources.getStringArray(R.array.list_status)
        var updateStatus = listStatus[spinnerStatus.selectedItemPosition]
        spinnerStatus.onItemSelectedListener = object : OnItemClickListener,
            AdapterView.OnItemSelectedListener {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
               updateStatus = resources.getStringArray(R.array.list_status)[p2]
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
        return updateStatus
    }
}