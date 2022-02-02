package com.geta.qr_scan

import androidx.room.*

//tuto recommend all defined or nullable, is that real ?
//@TODO : set params depending on json get from API (if urlImg name is 'image', write 'val image: String')
@Entity //(tableName = "word_table") // to defined table nameused in Dao request
data class Pokemon(
    @PrimaryKey val id: Int, //@PrimaryKey(autoGenerate = true) // mysql equivalent : AI
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "url_img")val urlImg : String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "updated_at") val updatedAt : String //= now().toString() (@require v26^, current is 21^)
)
