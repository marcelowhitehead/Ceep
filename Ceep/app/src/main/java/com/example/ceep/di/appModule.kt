package com.example.ceep.di

import com.example.ceep.database.NotesDatabase
import com.example.ceep.ui.NoteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
   single {
       NotesDatabase.getDatabase(get())
   }
    factory {
        get<NotesDatabase>().RoomNoteDAO()
    }
    viewModel {
        NoteViewModel(get())
    }
}