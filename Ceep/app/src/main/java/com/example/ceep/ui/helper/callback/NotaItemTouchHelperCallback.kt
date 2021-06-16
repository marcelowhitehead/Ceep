package com.example.ceep.ui.helper.callback

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.ceep.model.Nota
import com.example.ceep.ui.adapter.ListNotesAdapter

class NotaItemTouchHelperCallback(val adapter: ListNotesAdapter, val onSwipe: (position: Int) -> Unit) : ItemTouchHelper.Callback() {

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
       val marcacoesDeslize: Int = ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        var marcacoesArrastar: Int = (ItemTouchHelper.DOWN or ItemTouchHelper.UP or
                 ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT)
        return  makeMovementFlags(0, marcacoesDeslize)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        val movidos = mutableListOf<Nota>()
        var posicaoInicial = viewHolder.adapterPosition
        var posicaoFinal = target.adapterPosition
//        (posicaoInicial .. posicaoFinal).forEach{
//            val notaAtual = adapter.getItemByPosition(it)
//            if(it > posicaoInicial && it < posicaoFinal)
//                notaAtual.prioridade = notaAtual.prioridade-1
//        }
//        dao.troca(posicaoInicial, posicaoFinal)
        adapter.troca(posicaoInicial, posicaoFinal)
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        onSwipe(viewHolder.adapterPosition)
    }
}
