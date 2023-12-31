package com.example.food_app_owner.View.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.food_app_owner.Model.Adapter.DaftarMenuAdapter
import com.example.food_app_owner.Model.Lokal.listMenuMakanan
import com.example.food_app_owner.Model.ModelClass.Menu
import com.example.food_app_owner.R
import com.example.food_app_owner.ViewModel.MenuViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await

class MenuFragment : Fragment() {
    lateinit var recyclerViewMenu: RecyclerView
    lateinit var circularProges : ProgressBar
    lateinit var btnAdd: FloatingActionButton
    val menuViewModel: MenuViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewMenu = view.findViewById(R.id.menuRecyclerView)
        circularProges = view.findViewById(R.id.progressBar)
        btnAdd = view.findViewById(R.id.floatingActionButton)



        menuViewModel.menu.observe(viewLifecycleOwner){newValue ->
            recyclerViewMenu.layoutManager = LinearLayoutManager(requireContext())
            recyclerViewMenu.adapter = DaftarMenuAdapter(newValue,requireContext(),requireActivity().supportFragmentManager)
            if (newValue.size != 0){
                circularProges.visibility = View.GONE
            }
        }

        btnAdd.setOnClickListener {
            val transaki  = requireActivity().supportFragmentManager.beginTransaction()
            val fragmentAdd = TambahMenuFragment()
            transaki.replace(R.id.fragmentContainerView,fragmentAdd)
            transaki.addToBackStack(null)
            transaki.commit()


        }


        GlobalScope.launch { getDataMakanan(view) }









    }
    suspend fun getDataMakanan(view: View){
        try {
            val firestore = FirebaseFirestore.getInstance()
            val document = firestore.collection("makanan").get().await()
        withContext(Dispatchers.IO){
            document?.let { document ->
                val listMakanan = document.map {doc ->
                    Menu(doc.id,
                        doc.getString("nama")?:"",
                    doc.getString("gambar")?:"",
                        (doc.get("harga") as? Number)?.toInt() ?:0,
                    doc.getString("lamaMemasak")?:"",
                    doc.getString("Kategori")?:"",
                    doc.getString("deskripsi")?: "")
                }
                menuViewModel.listMenu.postValue(listMakanan.toMutableList())
            }
        }

        }catch (e: Exception){
            e.printStackTrace()
        }
    }
}