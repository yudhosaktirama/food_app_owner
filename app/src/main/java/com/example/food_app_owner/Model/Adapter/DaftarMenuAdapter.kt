package com.example.food_app_owner.Model.Adapter

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.food_app_owner.Model.ModelClass.Menu
import com.example.food_app_owner.R
import com.example.food_app_owner.View.Fragment.EditMenuFragment
import com.google.firebase.firestore.FirebaseFirestore

class DaftarMenuAdapter(val listMenu: MutableList<Menu>,val konteks: Context, val fragement: FragmentManager): RecyclerView.Adapter<DaftarMenuAdapter.DaftarMenuViewHolder>() {

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

        holder.btnDelete.setOnClickListener {
            val firestoreDelete = FirebaseFirestore.getInstance()
            val dialogBuilder = AlertDialog.Builder(konteks)
            dialogBuilder.setTitle("Hapus Menu")
            dialogBuilder.setMessage("Apakah Anda Yakin Ingin Menghapus Menu ini")
            dialogBuilder.setPositiveButton("Hapus"){dialog, which ->
                firestoreDelete.collection("makanan").document(binding.id).delete().addOnSuccessListener {
                    Toast.makeText(konteks, "Berhasil Hapus Data", Toast.LENGTH_SHORT).show()
                    listMenu.removeAt(position)
                    notifyDataSetChanged()
                }

            }
            dialogBuilder.setNegativeButton("Batal"){dialog,which ->
                dialog.cancel()
            }
            dialogBuilder.show()

        }

        holder.btnEdit.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id",binding.id)
            bundle.putString("nama",binding.nama)
            bundle.putString("gambar",binding.gambar)
            bundle.putString("lamaMemasak",binding.lamaMemasak)
            bundle.putInt("harga",binding.harga)
            bundle.putString("deskripsi",binding.deskripsi)
            val transaksi = fragement.beginTransaction()
            val toEdit = EditMenuFragment()
            toEdit.arguments = bundle
            transaksi.replace(R.id.fragmentContainerView,toEdit)
            transaksi.addToBackStack(null)
            transaksi.commit()

        }

    }
}