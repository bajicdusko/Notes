package com.bajicdusko.notes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bajicdusko.notes.domain.usecase.CreateNote
import com.bajicdusko.notes.domain.usecase.GetAllNotes

/**
 * Created by Dusko Bajic on 08.09.18.
 * GitHub @bajicdusko
 */
class ViewModelFactory(
    val createNote: CreateNote,
    val getAllNotes: GetAllNotes
) : ViewModelProvider.Factory {
  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    return when (modelClass) {
      NotesViewModel::class.java -> NotesViewModel(createNote, getAllNotes)
      else -> throw IllegalArgumentException()
    } as T
  }
}