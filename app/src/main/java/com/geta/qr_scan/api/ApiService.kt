package com.geta.qr_scan.api

import com.geta.qr_scan.Pokemon
import retrofit2.Response
import retrofit2.http.*

//@TODO : change route to get data
interface ApiService {

    //@HEADER if an header is required for Api

    @GET("pokemons")
    suspend fun getAllPokemon(): Response<MutableList<Pokemon>>

    @GET("pokemon/{num}")
    suspend fun getPokemonById(@Path("num") num : Int): Response<Pokemon>

    // case with url like api/pokemons?id=1
    //@GET("pokemon")
    //suspend fun getCommentsByPost(@Query("id") siren : Int): Response<Pokemon>

}