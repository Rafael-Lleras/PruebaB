package com.example.pruebasebastianb.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pruebasebastianb.db.DetailsEntity
import com.example.pruebasebastianb.db.PokemonDbProvider
import com.example.pruebasebastianb.db.PokemonEntity
import com.example.pruebasebastianb.db.ShoppingCartEntity

class ShoppingCartViewModel(): ViewModel() {

    var data = MutableLiveData<List<ShoppingCartEntity>>()
    var pokemonData = MutableLiveData<PokemonEntity>()

    suspend fun getAllItems(pokemonDbProvider: PokemonDbProvider): List<PokemonEntity> {
        var auxData = pokemonDbProvider.getDatBase().shoppingCartDao().getAllItems() ?: listOf()
        var auxListShoppingCartEntity: MutableList<ShoppingCartEntity> = mutableListOf()
        auxData.forEach { auxListShoppingCartEntity.add(ShoppingCartEntity(it.id)) }
        data.postValue(auxListShoppingCartEntity.toList())
        return auxData
    }

    suspend fun insertItem(pokemonDbProvider: PokemonDbProvider, item: ShoppingCartEntity) {
        pokemonDbProvider.getDatBase().shoppingCartDao().insert(item)
        getAllItems(pokemonDbProvider)
    }

    suspend fun deleteItems(pokemonDbProvider: PokemonDbProvider) {
        pokemonDbProvider.getDatBase().shoppingCartDao().deleteItems()
        getAllItems(pokemonDbProvider)
    }

    suspend fun deleteItemById(pokemonDbProvider: PokemonDbProvider, id: Int) {
        pokemonDbProvider.getDatBase().shoppingCartDao().deleteItemById(id)
        getAllItems(pokemonDbProvider)
    }

}