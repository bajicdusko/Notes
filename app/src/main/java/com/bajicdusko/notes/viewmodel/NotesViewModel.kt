package com.bajicdusko.notes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bajicdusko.notes.data.repository.DbNotesRepository
import com.bajicdusko.notes.domain.model.Note
import com.bajicdusko.notes.domain.model.ResponseWrapper
import com.bajicdusko.notes.domain.usecase.CreateNote
import com.bajicdusko.notes.domain.usecase.GetAllNotes

/**
 * Created by Dusko Bajic on 08.09.18.
 * GitHub @bajicdusko
 */
class NotesViewModel(
    private val createNote: CreateNote,
    private val getAllNotes: GetAllNotes
) : ViewModel() {
  fun addNote(noteContent: String): LiveData<ResponseWrapper<Note>> = createNote.execute(Note(noteContent))

  fun getNotes(): LiveData<ResponseWrapper<List<Note>>> = getAllNotes.execute(Unit)
}