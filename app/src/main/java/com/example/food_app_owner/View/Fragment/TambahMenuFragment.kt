package com.example.food_app_owner.View.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView.EdgeEffectFactory.EdgeDirection
import com.example.food_app_owner.R
import com.google.firebase.firestore.FirebaseFirestore


class TambahMenuFragment : Fragment() {
    lateinit var etMenu: EditText
    lateinit var etGambar : EditText
    lateinit var etLamaMemasak: EditText
    lateinit var etHarga : EditText
    lateinit var etDeskripsi : EditText
    lateinit var dropdown : Spinner
    lateinit var firestore: FirebaseFirestore
    lateinit var btnSimpan: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tambah_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        etMenu = view.findViewById(R.id.tambahMenu)
        etGambar = view.findViewById(R.id.tambahGambar)
        etLamaMemasak = view.findViewById(R.id.tambahLamaMasak)
        etHarga = view.findViewById(R.id.tambahHarga)
        etDeskripsi = view.findViewById(R.id.tambahDeskripsi)
        dropdown = view.findViewById(R.id.spinner3)
        btnSimpan = view.findViewById(R.id.btnSimpan)
        firestore = FirebaseFirestore.getInstance()



        btnSimpan.setOnClickListener {
            firestore.collection("makanan").add(addMenuFirestore()).addOnSuccessListener{
                Toast.makeText(requireContext(), "Berhasil Menambahkan Menu", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(requireContext(), "Gagal Menambahkan Menu", Toast.LENGTH_SHORT).show()
            }
        }







    }

    fun addMenuFirestore() :com.example.food_app_owner.Model.ModelClass.MenuAdd{
        val listku = resources.getStringArray(R.array.list_kategori)
        val menu = etMenu.text.toString()
        val gambar = etGambar.text.toString()
        val lamaMemasak = etLamaMemasak.text.toString()
        val harga = etHarga.text.toString().toInt()
        val deskripsi = etDeskripsi.text.toString()
        var kategori = listku[dropdown.selectedItemPosition]
        dropdown.onItemSelectedListener = object : OnItemClickListener,
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, posisi: Int, p3: Long) {
                kategori = resources.getStringArray(R.array.list_kategori)[posisi]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

            }

        }

        return com.example.food_app_owner.Model.ModelClass.MenuAdd(menu,gambar,harga,lamaMemasak,kategori,deskripsi)

    }
}