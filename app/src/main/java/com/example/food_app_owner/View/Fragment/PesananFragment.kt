package com.example.food_app_owner.View.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.food_app_owner.Model.Adapter.PesananAdapter
import com.example.food_app_owner.Model.Lokal.listLokal
import com.example.food_app_owner.Model.ModelClass.Pesanan
import com.example.food_app_owner.Model.ModelClass.pesananUser
import com.example.food_app_owner.R
import com.example.food_app_owner.ViewModel.PesananViewModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.awt.font.NumericShaper

class PesananFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var circularProgress: ProgressBar
    val pesananViewModel: PesananViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pesanan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.pesananRecyclerView)
        circularProgress = view.findViewById(R.id.progressBar2)

        pesananViewModel.listPesanan.observe(viewLifecycleOwner){NewValue ->
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = PesananAdapter(NewValue,requireActivity().supportFragmentManager,pesananViewModel)
            if (NewValue.size != 0){
               circularProgress.visibility = View.GONE
            }

        }

        GlobalScope.launch { getPesanan() }




    }

    suspend fun getPesanan(){
        try {
            val firestore = FirebaseFirestore.getInstance()
            val documentData = firestore.collection("pesanan").get().await()
            withContext(Dispatchers.IO){
                documentData?.let {document ->
                    val listPesanan = document.map {doc ->
                        val listPesananUser = (doc.get("pesanan_user") as List<Map<String,Any>>).map {pesanan ->
                            pesananUser(
                                pesanan["namaMakanan"] as? String?: "",
                                (pesanan["jumlah"] as? Number)?.toInt() ?: 0,
                                (pesanan["hargaSatuan"] as? Number)?.toInt() ?: 0,
                                (pesanan["harga"] as? Number)?.toInt() ?: 0,

                            )

                        }?: emptyList()
                        Pesanan(doc.id,
                            doc.getString("nama")?: "",
                            doc.getString("alamat")?:"",
                            listPesananUser,
                            doc.getString("status")?:"",
                            (doc["harga_total"] as? Number)?.toInt()?:0
                        )

                    }

                    pesananViewModel._listPesanan.postValue(listPesanan.toMutableList())

                }
            }

        }catch (e: Exception){
            e.printStackTrace()
        }

    }
}