package com.example.food_app_owner.View.Fragment

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.food_app_owner.R
import com.google.firebase.firestore.FirebaseFirestore

class EditMenuFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_edit_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        etMenu = view.findViewById(R.id.editMenu)
        etGambar = view.findViewById(R.id.editGambar)
        etLamaMemasak = view.findViewById(R.id.editLamaMasak)
        etHarga = view.findViewById(R.id.editHarga)
        etDeskripsi = view.findViewById(R.id.editDeskripsi)
        dropdown = view.findViewById(R.id.spinner3)
        btnSimpan = view.findViewById(R.id.btnSimpan)
        firestore = FirebaseFirestore.getInstance()

        val menu = arguments?.getString("nama")
        val gambar = arguments?.getString("gambar")
        val lamaMemasak = arguments?.getString("lamaMemasak")
        val id = arguments?.getString("id")
        val harga = arguments?.getInt("harga")
        val desskripsi = arguments?.getString("deskripsi")

        etMenu.text = menu!!.toEditable()
        etGambar.text = gambar!!.toEditable()
        etLamaMemasak.text = lamaMemasak!!.toEditable()
        etHarga.text = harga!!.toString().toEditable()
        etDeskripsi.text = desskripsi!!.toEditable()

        btnSimpan.setOnClickListener {
            val listku = resources.getStringArray(R.array.list_kategori)
            val hasilMenu = etMenu.text.toString()
            val hasilGambar = etGambar.text.toString()
            val hasilLamaMemasak = etLamaMemasak.text.toString()
            val hasilHarga = etHarga.text.toString().toInt()
            val hasilDeskripsi = etDeskripsi.text.toString()

            var kategori = listku[dropdown.selectedItemPosition]
            dropdown.onItemSelectedListener = object : AdapterView.OnItemClickListener,
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, posisi: Int, p3: Long) {
                    kategori = resources.getStringArray(R.array.list_kategori)[posisi]
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

                override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                }

            }

            updateDataToFirestore(id!!,hasilMenu,hasilGambar,kategori,hasilLamaMemasak,hasilHarga,hasilDeskripsi)

        }


    }
    fun updateDataToFirestore(id: String,menu: String,gambar: String,kategori: String,lamaMemasak: String,harga: Int,deskripsi: String) {
        firestore.collection("makanan").document(id).update(
            "nama", menu,
            "lamaMemasak", lamaMemasak,
            "kategori", kategori,
            "harga", harga,
            "gambar", gambar,
            "deskripsi", deskripsi
        ).addOnSuccessListener {
            Toast.makeText(requireContext(), "Update Berhasil", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(requireContext(), "Update Gagal", Toast.LENGTH_SHORT).show()
        }

    }

    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)
}
