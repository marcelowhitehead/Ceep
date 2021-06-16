package com.example.ceep.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.ceep.model.Nota
import java.util.*

@Dao
interface RoomNoteDAO {

    @Insert
    fun inserir(nota: Nota)

    @Query("SELECT * FROM nota order by isFavorite DESC")
    fun todos(): LiveData<List<Nota>>

    @Delete
    fun remove(nota: Nota)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun edita(nota: Nota)

}