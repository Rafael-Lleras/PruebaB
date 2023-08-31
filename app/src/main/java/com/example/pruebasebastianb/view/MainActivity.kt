package com.example.pruebasebastianb.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pruebasebastianb.databinding.ActivityMainBinding
import com.example.pruebasebastianb.db.PokemonDbProvider
import com.example.pruebasebastianb.db.PokemonEntity
import com.example.pruebasebastianb.helpers.RoomHelper
import com.example.pruebasebastianb.model.PokemonModel
import com.example.pruebasebastianb.model.ResultsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var dataBase: RoomHelper
    lateinit var pokemonDbProvider: PokemonDbProvider
    val cartViewModel: ShoppingCartViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val resultsViewModel: ResultsViewModel by viewModels()

        val pokemonDbViewModel: PokemonDbViewModel by viewModels()
        pokemonDbProvider = PokemonDbProvider(this)

        resultsViewModel.data.observe(this) {
            var resultsAdapter = ResultsAdapter(
                resultsViewModel.data.value!!.results,
                this@MainActivity
            )
            binding.rvResults.adapter = resultsAdapter
            binding.rvResults.layoutManager = LinearLayoutManager(this)
            binding.progressBar.visibility = View.GONE
        }

        pokemonDbViewModel.data.observe(this) {
            // --
        }

        GlobalScope.launch(Dispatchers.IO) {
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
            }
        }

        binding.mainButtonShoppingCart.setOnClickListener {
            val intent = Intent(this, ShoppingCartActivity::class.java)
            startActivity(intent)
        }
        
    }
}