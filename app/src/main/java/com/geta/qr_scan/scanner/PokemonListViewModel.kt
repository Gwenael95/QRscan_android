package com.geta.qr_scan.scanner

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.geta.qr_scan.Pokemon
import com.geta.qr_scan.db.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonListViewModel(private val repository: PokemonRepository) : ViewModel() {
    val pokemonState = MutableLiveData<PokemonListState>()

    fun loadPokemonList(id: Int) {
        pokemonState.postValue(PokemonListState.Loading)

        // Create a new coroutine to move the execution off the UI thread
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.allPokemons.asLiveData().value!!
                pokemonState.postValue(PokemonListState.Success(response))
            }
            catch (e: Exception) {
                pokemonState.postValue(PokemonListState.Error(e))
            }
        }
    }

    fun insert(pokemon: Pokemon) = viewModelScope.launch {
        repository.insert(pokemon)
    }
}

sealed class PokemonListState(
    open val errorMessage: String = "can't load pokemon"
) {
    object Loading : PokemonListState()
    data class Error(val error : Exception) : PokemonListState()
    data class Success(val pokemons: List<Pokemon>) : PokemonListState()
    data class Failure(override val errorMessage: String) : PokemonListState(
        errorMessage = errorMessage
    )
}