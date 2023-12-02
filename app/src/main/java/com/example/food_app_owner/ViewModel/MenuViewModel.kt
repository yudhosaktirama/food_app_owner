package com.example.food_app_owner.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.food_app_owner.Model.Lokal.listMenuMakanan
import com.example.food_app_owner.Model.ModelClass.Menu

class MenuViewModel : ViewModel() {
    var listMenu: MutableLiveData<MutableList<Menu>> = MutableLiveData(listMenuMakanan)

    val menu:LiveData<MutableList<Menu>>
        get() = listMenu



}