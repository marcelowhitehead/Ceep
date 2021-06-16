package com.example.ceep.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.ceep.R
import com.example.ceep.model.Nota
import com.example.ceep.ui.NoteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FormularioNotaActivity : AppCompatActivity() {

    val viewModel: NoteViewModel by viewModel()
    private val nota = Nota
    lateinit var title: EditText
    lateinit var description: EditText
    private var getNota: Nota? = null
    var posicaoRecebida: Int = -1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_nota)

        setTitle("Adicionar nota")
        inicializaCampos()

        val dadosRecebidos = intent
        if (dadosRecebidos.hasExtra(nota.NOTA_KEY)) {
            setTitle("Altera nota")
            getNota = dadosRecebidos.getSerializableExtra(nota.NOTA_KEY) as Nota
            posicaoRecebida = dadosRecebidos.getIntExtra(nota.POSICAO_KEY, nota.POSICAO_INVALIDA)
            preencheCampos(getNota)
        }
    }

    private fun inicializaCampos() {
        title = findViewById<EditText>(R.id.formulario_nota_title)
        description = findViewById<EditText>(R.id.formulario_nota_description)
    }

    private fun preencheCampos(nota: Nota?) {
        title.setText(nota?.titulo)
        description.setText(nota?.descricao)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_formulario_save, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_formulario_ic_save) {
            if (title.text.isNullOrEmpty() && description.text.isNullOrEmpty() || "".equals(
                    title.text.toString().trim()) && "".equals(description.text.toString().trim())){
                title.error = "Insira um titulo válido"
                description.error = "Insira uma descrição válida"
            } else if (description.text.isNullOrEmpty() || "".equals(
                    description.text.toString().trim())) {
                description.error = "Insira uma descrição válida"
            } else if (title.text.isNullOrEmpty() || "".equals(title.text.toString().trim())) {
                title.error = "Insira um titulo válido"
            } else {
                finalizaFormulario()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun criaNota(): Nota {
        return Nota(titulo = title.text.toString(), descricao = description.text.toString())
    }

    private fun updateNote(){
        getNota?.titulo = title.text.toString()
        getNota?.descricao = description.text.toString()
        getNota?.let { viewModel.edita(it) }
    }

    private fun createNote(){
        val notaCriada = criaNota()
        viewModel.inserir(notaCriada)
    }

    private fun finalizaFormulario(){
        if (posicaoRecebida >= 0){
            updateNote()
        }
        else{
           createNote()
        }
        finish()
    }
}