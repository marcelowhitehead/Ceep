package com.example.ceep.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.ceep.R
import com.example.ceep.model.Nota
import com.example.ceep.ui.NoteViewModel
import com.example.ceep.ui.adapter.ListNotesAdapter
import com.example.ceep.ui.helper.callback.NotaItemTouchHelperCallback
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListNotesActivity : AppCompatActivity() {

    val viewModel: NoteViewModel by viewModel()
    val nota = Nota
    private val adapter: ListNotesAdapter by lazy { ListNotesAdapter() }
    lateinit var listaNotas: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_notes)
        setTitle("Notas")

        configuraBotaoInsereNota()
        configuraRecyclerView()
        setUpObservables()
    }

    private fun configuraBotaoInsereNota() {
        val insereNota = findViewById<TextView>(R.id.lista_notas_insere_nota)
        insereNota.setOnClickListener {
            startActivityForResult(
                Intent(this, FormularioNotaActivity::class.java)
                , nota.CODIGO_REQUISICAO_INSERE_NOTA
            )
        }
    }

    private fun setUpObservables(){
        viewModel.notesObservable.observe(this, Observer {
            adapter.update(it)
        })
    }

    private fun configuraRecyclerView() {
        listaNotas = findViewById(R.id.lista_notas_recyclerview)
        listaNotas.adapter = adapter
        adapter.setOnItemClicked { position, notaRecebida ->
            startActivity(
                Intent(this, FormularioNotaActivity::class.java)
                    .putExtra(nota.POSICAO_KEY, position)
                    .putExtra(nota.NOTA_KEY, notaRecebida)
            )
        }
        adapter.onStarClicked {
            viewModel.edita(it)
        }
        val callback = NotaItemTouchHelperCallback(adapter) {
            val nota = adapter.getItemByPosition(it)
            viewModel.remove(nota)
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(listaNotas)
    }

}