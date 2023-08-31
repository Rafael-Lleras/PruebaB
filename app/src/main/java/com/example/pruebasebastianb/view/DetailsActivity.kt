package com.example.pruebasebastianb.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pruebasebastianb.R
import com.example.pruebasebastianb.databinding.ActivityDetailsBinding
import com.example.pruebasebastianb.databinding.ActivityMainBinding
import com.example.pruebasebastianb.db.DetailsEntity
import com.example.pruebasebastianb.db.PokemonDbProvider
import com.example.pruebasebastianb.db.PokemonEntity
import com.example.pruebasebastianb.helpers.RoomHelper
import com.example.pruebasebastianb.model.AbilityItemModel
import com.example.pruebasebastianb.model.AbilityModel
import com.example.pruebasebastianb.model.DetailsModel
import com.example.pruebasebastianb.model.MovesItemModel
import com.example.pruebasebastianb.model.MovesModel
import com.example.pruebasebastianb.model.PokemonModel
import com.example.pruebasebastianb.model.ResultsModel
import com.example.pruebasebastianb.model.TypesItemModel
import com.example.pruebasebastianb.model.TypesModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailsBinding
    lateinit var dataBase: RoomHelper
    lateinit var pokemonDbProvider: PokemonDbProvider
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra("id", 0)
        //Toast.makeText(this, "$id", Toast.LENGTH_LONG).show()

        val detailsViewModel: DetailsViewModel by viewModels()

        val pokemonDbViewModel: PokemonDbViewModel by viewModels()
        val pokemonDbProvider = PokemonDbProvider(this)

        detailsViewModel.data.observe(this) {
            //binding.tvDetailsInfo.text = detailsViewModel.data.value.toString()

            Picasso.with(this@DetailsActivity).load(detailsViewModel.data.value!!.getUrlHomeImage())
                .into(binding.imageDetails)
            binding.detailsName.text = detailsViewModel.data.value!!.name
            binding.detailsHeight.text = detailsViewModel.data.value!!.height.toString()
            binding.detailsWeight.text = detailsViewModel.data.value!!.weight.toString()
            binding.detailsAbilities.text =
                detailsViewModel.data.value!!.abilities.map { it -> "- ${it.ability.name}" }
                    .joinToString("\n")
            binding.detailsMoves.text =
                detailsViewModel.data.value!!.moves.map { it -> "- ${it.move.name}" }
                    .joinToString("\n")
            binding.detailsTypes.text =
                detailsViewModel.data.value!!.types.map { it -> "- ${it.type.name}" }
                    .joinToString("\n")
            binding.detailsProgressBar.visibility = View.GONE
            binding.llButtons.visibility = View.VISIBLE
            binding.svDetails.visibility = View.VISIBLE

            //Toast.makeText(this@DetailsActivity, detailsViewModel.data.value!!.name.toString(), Toast.LENGTH_LONG).show()

        }

        GlobalScope.launch(Dispatchers.Main) {
            pokemonDbViewModel.deleteDetailsById(pokemonDbProvider, 0)

            var details = pokemonDbViewModel.getDetails(pokemonDbProvider, id)
            if (details != null) {
                var auxData = DetailsModel(
                    details.id,
                    details.name,
                    ArrayList(
                        details.abilities.split("-")
                            .map { it -> AbilityModel(AbilityItemModel(it)) }),
                    details.height,
                    ArrayList(
                        details.moves.split("-").map { it -> MovesModel(MovesItemModel(it)) }),
                    ArrayList(
                        details.types.split("-").map { it -> TypesModel(TypesItemModel(it)) }),
                    details.weight
                )
                detailsViewModel.setData(auxData)
            } else {
                var details = detailsViewModel.getData(id)
                if (details != null) {
                    var detailsEntity = DetailsEntity(
                        details.id,
                        details.name,
                        details.height,
                        details.weight,
                        details.abilities.map { it -> it.ability.name }.joinToString("-"),
                        details.types.map { it -> it.type.name }.joinToString("-"),
                        details.moves.map { it -> it.move.name }.joinToString("-")
                    )
                    pokemonDbViewModel.insertDetails(pokemonDbProvider, detailsEntity)
                } else {
                    showMessage("You need Internet to see the details")
                }
            }
        }

        binding.detailsClose.setOnClickListener {
            super.onBackPressed();
        }

    }

    private fun showMessage(message: String) {
        var toast = Toast.makeText(this@DetailsActivity, message, Toast.LENGTH_LONG).show()
    }

}