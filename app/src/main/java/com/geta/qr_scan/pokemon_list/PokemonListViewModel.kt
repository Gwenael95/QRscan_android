package com.geta.qr_scan.pokemon_list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geta.qr_scan.Pokemon
import com.geta.qr_scan.api.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonListViewModel : ViewModel() {
    val pokemonState = MutableLiveData<PokemonListState>()

    fun loadPokemon(id: Int) {
        pokemonState.postValue(PokemonListState.Loading)

        // Create a new coroutine to move the execution off the UI thread
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = ApiClient.apiService.getPokemonById(id)
                Log.w("Pokemon VIEW_MODEL", response.toString())
                if (response.isSuccessful && response.body() != null) {
                    val content = response.body()
                    pokemonState.postValue(PokemonListState.Success(content!!))
                    Log.w("Pokemon VIEW_MODEL R", content.toString())
                } else {
                    pokemonState.postValue(PokemonListState.Failure("can't get pokemon data"))
                }
            }
            catch (e: Exception) {
                Log.e("PokemonViewModel", e.toString())
                pokemonState.postValue(PokemonListState.Failure("Error when calling Api, $e"))
            }
        }
    }
}

sealed class PokemonListState(
    open val errorMessage: String = "can't load pokemon"
) {
    object Loading : PokemonListState()
    data class Error(val error : Exception) : PokemonListState()
    data class Success(val pokemon: Pokemon) : PokemonListState()
    data class Failure(override val errorMessage: String) : PokemonListState(
        errorMessage = errorMessage
    )
}