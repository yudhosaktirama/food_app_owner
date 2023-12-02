package com.example.food_app_owner.View.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.food_app_owner.Model.Adapter.DaftarMenuAdapter
import com.example.food_app_owner.Model.Lokal.listMenuMakanan
import com.example.food_app_owner.R

class MenuFragment : Fragment() {
    lateinit var recyclerViewMenu: RecyclerView


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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewMenu = view.findViewById(R.id.menuRecyclerView)

        recyclerViewMenu.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewMenu.adapter = DaftarMenuAdapter(listMenuMakanan,requireContext())

    }
}