package com.example.pruebasebastianb.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pruebasebastianb.R
import com.example.pruebasebastianb.databinding.ActivityShoppingCartBinding
import com.example.pruebasebastianb.db.PokemonDbProvider
import com.example.pruebasebastianb.db.PokemonEntity
import com.example.pruebasebastianb.db.ShoppingCartEntity
import com.example.pruebasebastianb.helpers.RoomHelper
import com.example.pruebasebastianb.model.PokemonModel
import com.example.pruebasebastianb.model.ResultsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ShoppingCartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShoppingCartBinding

    lateinit var dataBase: RoomHelper
    lateinit var pokemonDbProvider: PokemonDbProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val shoppingCartViewModel: ShoppingCartViewModel by viewModels()
        pokemonDbProvider = PokemonDbProvider(this)
        val pokemonViewModel: PokemonDbViewModel by viewModels()

        shoppingCartViewModel.data.observe(this) {
            var auxListPokemonEntity = mutableListOf<PokemonEntity>()
            shoppingCartViewModel.data.value!!.forEach {
                runBlocking {
                    auxListPokemonEntity.add(
                        pokemonDbProvider.getDatBase().pokemonDao().getPokemonById(it.id)!!
                    )
                }
            }
            var shoppingCartAdapter = ShoppingCartAdapter(auxListPokemonEntity.toList(), this@ShoppingCartActivity, shoppingCartViewModel)
            binding.rvProducts.adapter = shoppingCartAdapter
            binding.rvProducts.layoutManager = LinearLayoutManager(this)
            binding.shoppingCartProgressBar.visibility = View.GONE
        }

        GlobalScope.launch(Dispatchers.Main) {
            /*for (i in 1..20) {
                shoppingCartViewModel.insertItem(pokemonDbProvider, ShoppingCartEntity(i))
            }*/
            var listPokemons = shoppingCartViewModel.getAllItems(pokemonDbProvider)

            //Toast.makeText(this@ShoppingCartActivity, listPokemons.toString(), Toast.LENGTH_LONG).show()
            /*
            var allPokemons = pokemonDbViewModel.getAllPokemons(pokemonDbProvider)
            if (allPokemons.size > 0) {
                var listPokemon = ArrayList<PokemonModel>()
                allPokemons.map { it -> listPokemon.add(PokemonModel(it.name, it.url)) }
                var auxData: ResultsModel = ResultsModel(listPokemon)
                resultsViewModel.setData(auxData)
            } else {
                var results = resultsViewModel.getData()
                results.results.forEach() { result ->
                    var pokemon = PokemonEntity(result.getId()!!, result.name, result.url)
                    pokemonDbViewModel.insertPokemon(pokemonDbProvider, pokemon)
                }
            }*/
        }

        binding.mainButtonBack.setOnClickListener {
            super.onBackPressed();
        }

        binding.removeAllItemsToShoppingCart.setOnClickListener {
            GlobalScope.launch {
                shoppingCartViewModel.deleteItems(pokemonDbProvider)
            }
        }

    }


}