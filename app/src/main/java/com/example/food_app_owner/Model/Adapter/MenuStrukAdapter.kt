package com.example.food_app_owner.Model.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.food_app_owner.Model.ModelClass.Struk
import com.example.food_app_owner.R

class MenuStrukAdapter(val list: List<Struk>, val konteks: Context) : RecyclerView.Adapter<MenuStrukAdapter.MenuStrukViewHolder>() {
    class MenuStrukViewHolder(row: View): RecyclerView.ViewHolder(row) {
        val Menu = row.findViewById<TextView>(R.id.tvMenu)
        val JmlhMenu = row.findViewById<TextView>(R.id.tvJmlhMenu)
        val HrgMenu = row.findViewById<TextView>(R.id.tvHrgMenu)
        val SubTotalMenu = row.findViewById<TextView>(R.id.tvSubTotalMenu)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuStrukViewHolder{
        val layput = LayoutInflater.from(parent.context).inflate(R.layout.layout_menu_struk, parent, false)
        return MenuStrukViewHolder(layput)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MenuStrukViewHolder, position: Int) {
        val binding = list[position]

        holder.Menu.text = binding.Menu
        holder.HrgMenu.text = binding.HrgMenu.toString()
        holder.JmlhMenu.text = binding.JmlhMenu.toString()
        holder.SubTotalMenu.text = binding.SubTotalMenu.toString()
    }
}