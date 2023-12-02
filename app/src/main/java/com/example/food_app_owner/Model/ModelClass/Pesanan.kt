package com.example.food_app_owner.Model.ModelClass

data class Pesanan(
    val nama: String = "Yudho",
    val alamat: String = "Jl Raya Ponorogo",
    val listMakanan:List<pesananUser> = listOf<pesananUser>(pesananUser("Test",0)),
    val status: String = "Sedang Dibuat"
    )

data class  pesananUser(
    val namaMakanan: String,
    val jumlahUnit: Int,

)
