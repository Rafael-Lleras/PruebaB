package com.example.pruebasebastianb.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pruebasebastianb.db.DetailsEntity
import com.example.pruebasebastianb.db.PokemonDbProvider
import com.example.pruebasebastianb.db.PokemonEntity

class PokemonDbViewModel(): ViewModel() {

    var data = MutableLiveData<List<PokemonEntity>>()
    var detailsData = MutableLiveData<DetailsEntity>()

    suspend fun getAllPokemons(pokemonDbProvider: PokemonDbProvider): List<PokemonEntity> {
        var auxData = pokemonDbProvider.getDatBase().pokemonDao().getAllPokemons() ?: listOf()
        data.postValue(auxData)
        return auxData
    }

    suspend fun insertPokemon(pokemonDbProvider: PokemonDbProvider, pokemon: PokemonEntity) {
        pokemonDbProvider.getDatBase().pokemonDao().insert(pokemon)
        getAllPokemons(pokemonDbProvider)
    }

    suspend fun deletePokemons(pokemonDbProvider: PokemonDbProvider) {
        pokemonDbProvider.getDatBase().pokemonDao().deletePokemons()
        getAllPokemons(pokemonDbProvider)
    }

    suspend fun insertDetails(pokemonDbProvider: PokemonDbProvider, details: DetailsEntity) {
        pokemonDbProvider.getDatBase().detailsDao().insert(details)
        getDetails(pokemonDbProvider, details.id)
    }

    suspend fun getDetails(pokemonDbProvider: PokemonDbProvider, id: Int): DetailsEntity? {
        var auxData = pokemonDbProvider.getDatBase().detailsDao().getDetailsById(id) ?: DetailsEntity(0, "", 0, 0, "", "", "")
        if (auxData.name == "") {
            return null
        } else {
            detailsData.postValue(auxData)
            return auxData
        }
    }

    suspend fun deleteDetails(pokemonDbProvider: PokemonDbProvider, id: Int) {
        pokemonDbProvider.getDatBase().detailsDao().deleteDetails()
        getDetails(pokemonDbProvider, id)
    }

    suspend fun deleteDetailsById(pokemonDbProvider: PokemonDbProvider, id: Int) {
        pokemonDbProvider.getDatBase().detailsDao().deleteDetailsById(id)
        getDetails(pokemonDbProvider, id)
    }

}