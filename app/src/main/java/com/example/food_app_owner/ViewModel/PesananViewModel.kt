package com.example.food_app_owner.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.food_app_owner.Model.Lokal.listLokal
import com.example.food_app_owner.Model.ModelClass.Pesanan

class PesananViewModel : ViewModel() {

    var _listPesanan: MutableLiveData<MutableList<Pesanan>> = MutableLiveData(listLokal)

    val listPesanan: LiveData<MutableList<Pesanan>>
        get() = _listPesanan



}