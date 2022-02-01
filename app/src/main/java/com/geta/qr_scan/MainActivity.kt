package com.geta.qr_scan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.geta.qr_scan.pokemon_list.PokemonListState
import com.geta.qr_scan.pokemon_list.PokemonListViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel : PokemonListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.pokemonState.observe(this, Observer { updateUi(it!!) })
        viewModel.loadPokemon(1)

        setContentView(R.layout.activity_main)
    }


    private fun updateUi(state: PokemonListState) {
        return when (state) {
            is PokemonListState.Success -> {
                displayToast(this, "Login OK pour pokemon ${state.pokemon.siren}! ")
            }
            is PokemonListState.Failure -> {
                displayToast(this, state.errorMessage)
            }
            is PokemonListState.Loading -> {
                // loading element
            }
            is PokemonListState.Error -> displayToast(this, state.errorMessage)
        }
    }
}