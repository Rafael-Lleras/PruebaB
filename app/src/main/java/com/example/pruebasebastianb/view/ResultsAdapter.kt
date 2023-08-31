package com.example.pruebasebastianb.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebasebastianb.R
import com.example.pruebasebastianb.db.ShoppingCartEntity
import com.example.pruebasebastianb.model.PokemonModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ResultsAdapter(
    private val pokemonsList: List<PokemonModel>,
    val context: MainActivity
) : RecyclerView.Adapter<ResultsAdapter.ViewHolder>() {

    // ViewHolder para mantener las vistas de los elementos
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Enlazar las vistas
        var name = itemView.findViewById<TextView>(R.id.name)
        var img = itemView.findViewById<ImageView>(R.id.img)
        var btnDetails = itemView.findViewById<Button>(R.id.details)
        var btnRemove = itemView.findViewById<Button>(R.id.remove)
        var btnAdd = itemView.findViewById<Button>(R.id.add)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_results, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = pokemonsList[position]
        // Enlazar datos a las vistas del ViewHolder
        holder.name.text = pokemon.name
        Picasso.with(context).load(pokemon.getUrlShinyImage()).into(holder.img)
        // Agregar eventos
        holder.btnDetails.setOnClickListener {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("id", pokemon.getId())
            context.startActivity(intent)
        }
        holder.btnAdd.setOnClickListener {
            var id = pokemonsList[position].getId()
            GlobalScope.launch(Dispatchers.Main) {
                val listPokemonEntity = context.cartViewModel.getAllItems(context.pokemonDbProvider)
                if (id !in listPokemonEntity.map { it -> it.id}) {
                    context.cartViewModel.insertItem(context.pokemonDbProvider, ShoppingCartEntity(id!!))
                    Toast.makeText(context, "Agregado", Toast.LENGTH_LONG).show()
                }
            }
        }
        holder.btnRemove.setOnClickListener {
            var id = pokemonsList[position].getId()
            GlobalScope.launch(Dispatchers.Main) {
                val listPokemonEntity = context.cartViewModel.getAllItems(context.pokemonDbProvider)
                if (id in listPokemonEntity.map { it -> it.id}) {
                    context.cartViewModel.deleteItemById(context.pokemonDbProvider, id!!)
                    Toast.makeText(context, "Quitado", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return pokemonsList.size
    }

}