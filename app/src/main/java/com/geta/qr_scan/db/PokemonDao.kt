package com.geta.qr_scan.db

import androidx.room.*
import com.geta.qr_scan.Pokemon
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemon")
    fun getAll(): Flow<List<Pokemon>>

    @Query("SELECT * FROM pokemon WHERE id IN (:pokemonIds)")
    fun loadAllByIds(pokemonIds: IntArray): Flow<List<Pokemon>>

    @Query("SELECT * FROM pokemon WHERE name LIKE :name LIMIT 1")
    fun findByName(name: String): Flow<Pokemon>

    @Query("SELECT * FROM pokemon WHERE id = :id LIMIT 1")
    fun findById(id: Int): Flow<Pokemon>

    //example with list , example for pokemon capacities
    //@Query("SELECT * FROM pokemon JOIN capacities ON pokemon.id = book.pokemon_id")
    //fun loadUserAndBookNames(): Flow<Map<Pokemon, List<Capacities>>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg pokemons: Pokemon)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(pokemon: Pokemon)

    @Update
    fun updateUsers(vararg pokemons: Pokemon)

    @Delete
    fun delete(pokemon: Pokemon)
}