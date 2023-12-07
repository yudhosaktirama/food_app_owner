package com.example.food_app_owner.Model.Adapter

import android.os.Bundle
import android.os.Parcelable
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.food_app_owner.Model.ModelClass.Pesanan
import com.example.food_app_owner.Model.ModelClass.pesananUser
import com.example.food_app_owner.R
import com.example.food_app_owner.View.Fragment.DetailPesananFragment
import com.example.food_app_owner.ViewModel.PesananViewModel
import java.util.ArrayList

class PesananAdapter(val listpesanan: List<Pesanan>,val fragment: FragmentManager,val viewModel: PesananViewModel) : RecyclerView.Adapter<PesananAdapter.PesananViewHolder>() {

    class PesananViewHolder(baris: View) : RecyclerView.ViewHolder(baris) {
        val nama = baris.findViewById<TextView>(R.id.nama)
        val layoutPesanan = baris.findViewById<ConstraintLayout>(R.id.pesananLayout)
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

        holder.layoutPesanan.setOnClickListener {
            viewModel.clear()
            viewModel.addList(binding.listMakanan)
            val transaksi = fragment.beginTransaction()
            val fragmentDetail = DetailPesananFragment()
            val bundle = Bundle()
            bundle.putString("nama",binding.nama)
            bundle.putString("alamat",binding.alamat)
            fragmentDetail.arguments = bundle
            transaksi.replace(R.id.fragmentContainerView,fragmentDetail)
            transaksi.addToBackStack(null)
            transaksi.commit()



        }
    }
}