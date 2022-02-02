package com.geta.qr_scan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.geta.qr_scan.pokemon_list.PokemonState
import com.geta.qr_scan.pokemon_list.PokemonViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel : PokemonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.pokemonState.observe(this, Observer { updateUi(it!!) })
        viewModel.loadPokemon(888005444)

        setContentView(R.layout.activity_main)
    }


    private fun updateUi(state: PokemonState) {
        return when (state) {
            is PokemonState.Success -> {
                displayToast(this, "Login OK pour pokemon ${state.pokemon.name}! ")
            }
            is PokemonState.Failure -> {
                displayToast(this, state.errorMessage)
            }
            is PokemonState.Loading -> {
                // loading element
            }
            is PokemonState.Error -> displayToast(this, state.errorMessage)
        }
    }
}