package com.example.ceep.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ceep.database.dao.RoomNoteDAO
import com.example.ceep.model.Nota
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(val roomNoteDAO: RoomNoteDAO) : ViewModel() {

    private val notes: MutableLiveData<List<Nota>> = MutableLiveData()
    val notesObservable: LiveData<List<Nota>> = notes

    init {
        viewModelScope.launch {
            roomNoteDAO.todos().observeForever {
                notes.postValue(it)
            }
        }
    }

    fun inserir(nota: Nota) {
        viewModelScope.launch(Dispatchers.IO) {
            roomNoteDAO.inserir(nota)
        }
    }

    fun remove(nota: Nota){
        viewModelScope.launch(Dispatchers.IO) {
            roomNoteDAO.remove(nota)
        }
    }

    fun edita(nota: Nota){
        viewModelScope.launch(Dispatchers.IO) {
           roomNoteDAO.edita(nota)
        }
    }

}