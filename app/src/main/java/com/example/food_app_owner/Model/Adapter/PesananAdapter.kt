package com.example.food_app_owner.Model.Adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.food_app_owner.Model.ModelClass.Pesanan
import com.example.food_app_owner.R

class PesananAdapter(val listpesanan: List<Pesanan>) : RecyclerView.Adapter<PesananAdapter.PesananViewHolder>() {

    class PesananViewHolder(baris: View) : RecyclerView.ViewHolder(baris) {
        val nama = baris.findViewById<TextView>(R.id.nama)
        val alamat = baris.findViewById<TextView>(R.id.alamat)
        val status = baris.findViewById<TextView>(R.id.status)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PesananViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.layout_pesanan_masuk,parent,false)
        return PesananViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return  listpesanan.size
    }

    override fun onBindViewHolder(holder: PesananViewHolder, position: Int) {
        val binding = listpesanan[position]

        holder.nama.text = binding.nama
        holder.alamat.text = binding.alamat
        holder.status.text = binding.status
    }
}