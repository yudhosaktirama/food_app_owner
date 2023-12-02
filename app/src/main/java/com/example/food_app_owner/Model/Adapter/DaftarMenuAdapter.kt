package com.example.food_app_owner.Model.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.food_app_owner.Model.ModelClass.Menu
import com.example.food_app_owner.R

class DaftarMenuAdapter(val listMenu: List<Menu>,val konteks: Context): RecyclerView.Adapter<DaftarMenuAdapter.DaftarMenuViewHolder>() {

    class DaftarMenuViewHolder(baris: View): RecyclerView.ViewHolder(baris){
        val iconMakanan = baris.findViewById<ImageView>(R.id.iconMakanan)
        val namaMakanan = baris.findViewById<TextView>(R.id.namaMakanan)
        val btnEdit = baris.findViewById<ImageView>(R.id.btnEditMakanan)
        val btnDelete = baris.findViewById<ImageView>(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaftarMenuViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.layout_daftar_menu,parent,false)
        return  DaftarMenuViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return listMenu.size
    }

    override fun onBindViewHolder(holder: DaftarMenuViewHolder, position: Int) {
        val binding = listMenu[position]

        Glide.with(konteks).load(binding.gambar).centerCrop().placeholder(R.drawable.gambar_makanan).into(holder.iconMakanan)

        holder.namaMakanan.text = binding.nama

    }
}