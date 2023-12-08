package com.example.food_app_owner.View.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
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


    }
}