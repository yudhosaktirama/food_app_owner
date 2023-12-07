package com.example.food_app_owner.Model.ModelClass

data class Pesanan(
    val id: String = "",
    val nama: String = "Yudho",
    val alamat: String = "Jl Raya Ponorogo",
    val listMakanan:List<pesananUser>,
    val status: String = "Sedang Dibuat",
    val hargaTotal: Int = 10000
    )

data class  pesananUser(
    val namaMakanan: String = "test",
    val jumlah: Int =0,
    val hargaSatuan: Int = 1,
    val harga: Int = 2,
    val iconMakanan: String ="https://image.gambarpng.id/pngs/gambar-transparent-fried-rice_41459.png"



)
