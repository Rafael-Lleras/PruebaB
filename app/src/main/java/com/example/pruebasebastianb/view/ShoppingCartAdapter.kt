package com.example.pruebasebastianb.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebasebastianb.R
import com.example.pruebasebastianb.db.PokemonDbProvider
import com.example.pruebasebastianb.db.PokemonEntity
import com.example.pruebasebastianb.db.ShoppingCartEntity
import com.example.pruebasebastianb.model.PokemonModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ShoppingCartAdapter(private val pokemonList: List<PokemonEntity>, val context: ShoppingCartActivity, val shoppingCartViewModel: ShoppingCartViewModel) : RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder>() {

    // ViewHolder para mantener las vistas de los elementos
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Enlazar las vistas
        var name = itemView.findViewById<TextView>(R.id.name)
        var img = itemView.findViewById<ImageView>(R.id.img)
        var btnRemove = itemView.findViewById<Button>(R.id.removeToShoppingCart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_shopping_cart, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        // Enlazar datos a las vistas del ViewHolder
        holder.name.text = pokemon.name
        Picasso.with(context).load(pokemon.getUrlShinyImage()).into(holder.img)
        // Agregar eventos
        holder.btnRemove.setOnClickListener {
            GlobalScope.launch {
                this@ShoppingCartAdapter.shoppingCartViewModel.deleteItemById(context.pokemonDbProvider, pokemonList.get(position).id)
            }
        }
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

}