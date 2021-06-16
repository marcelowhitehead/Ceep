package com.example.ceep.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class Nota(
    @PrimaryKey(autoGenerate = true) var notaId: Int = 0,
    var titulo: String,
    var descricao: String,
    var isFavorite: Boolean = false
) : Serializable {
    companion object {
        const val NOTA_KEY = "nota"
        const val POSICAO_KEY = "posicao"
        val CODIGO_REQUISICAO_INSERE_NOTA: Int = 1
        val CODIGO_REQUISICAO_ALTERA_NOTA: Int = 2
        var POSICAO_INVALIDA = -1
    }

    fun temIdValido() : Boolean {
        return notaId > 0
    }
}