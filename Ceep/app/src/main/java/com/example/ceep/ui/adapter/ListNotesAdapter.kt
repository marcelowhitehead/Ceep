package com.example.ceep.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ceep.R
import com.example.ceep.model.Nota
import java.util.*

class ListNotesAdapter() : RecyclerView.Adapter<ListNotesAdapter.NotesViewHolder>() {

    private val notas: MutableList<Nota> = mutableListOf()
    private var onItemClicked: (position: Int, nota: Nota) -> Unit = { _, _ -> }
    private var onStarClicked: (nota: Nota) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false)
        return NotesViewHolder(view, onItemClicked, onStarClicked)
    }

    override fun getItemCount(): Int {
        return notas.size
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(notas[position])
    }


    fun setOnItemClicked(onItemClicked: (position: Int, nota: Nota) -> Unit) {
        this.onItemClicked = onItemClicked
    }

    inner class NotesViewHolder(
        itemView: View,
        val onItemClicked: (position: Int, nota: Nota) -> Unit,
        val onStarClicked: (nota: Nota) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        fun bind(note: Nota) {
            val title = itemView.findViewById<TextView>(R.id.item_note_title)
            title.text = note.titulo
            val description = itemView.findViewById<TextView>(R.id.item_note_description)
            description.text = note.descricao
            itemView.setOnClickListener {
                onItemClicked(adapterPosition, note)
            }
            val favorite = itemView.findViewById<ImageView>(R.id.item_note_favorite)
            val favoriteIcon = if (note.isFavorite){
                R.drawable.ic_star_full
            }
            else{
                R.drawable.ic_star
            }
            favorite.setBackgroundResource(favoriteIcon)
            favorite.setOnClickListener {
                onStarClicked(note.also { it.isFavorite = !note.isFavorite })
            }
        }
    }

    fun onStarClicked(onStarClicked: (nota: Nota) -> Unit){
        this.onStarClicked = onStarClicked
    }

    fun update(lista: List<Nota>) {
        this.notas.clear()
        this.notas.addAll(lista)
        notifyDataSetChanged()
    }

    fun getItemByPosition(position: Int) : Nota{
        return notas[position]
    }

    fun add(nota: Nota) {
        notas.add(nota)
        notifyDataSetChanged()
    }

    fun altera(position: Int, notaRecebida: Nota) {
        notas[position] = notaRecebida
        notifyItemChanged(position)
    }

    fun remove(position: Int) {
        notas.removeAt(position)
        notifyItemRemoved(position)
    }

    fun troca(posicaoInicial: Int, posicaoFinal: Int) {
        Collections.swap(notas, posicaoInicial, posicaoFinal)
        notifyItemMoved(posicaoInicial, posicaoFinal)
    }
}