package com.geta.qr_scan

//tuto recommend all defined or nullable, is that real ?
//@TODO : set params depending on json get from API (if urlImg name is 'image', write 'val image: String')
data class Pokemon(
    val name: String,
    val id: Int,
    val urlImg : String,
    val type: String,
    val updatedAt : String //= now().toString() (@require v26^, current is 21^)
)