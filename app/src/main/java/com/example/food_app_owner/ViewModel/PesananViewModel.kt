package com.example.food_app_owner.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.food_app_owner.Model.Lokal.listLokal
import com.example.food_app_owner.Model.Lokal.listmakanan
import com.example.food_app_owner.Model.ModelClass.Pesanan
import com.example.food_app_owner.Model.ModelClass.pesananUser

class PesananViewModel : ViewModel() {

    var _listPesanan: MutableLiveData<MutableList<Pesanan>> = MutableLiveData(listLokal)

    var _listdetail : MutableLiveData<MutableList<pesananUser>> = MutableLiveData(listmakanan)

    val listPesanan: LiveData<MutableList<Pesanan>>
        get() = _listPesanan

    val listDetail: LiveData<MutableList<pesananUser>>
        get() = _listdetail

    fun clear(){
        _listdetail.value!!.clear()
    }

    fun addList(list:List<pesananUser>){
        for (i in list){
            _listdetail.value!!.add(i)
        }

    }



}