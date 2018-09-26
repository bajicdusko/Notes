package com.bajicdusko.notes.data.repository

import androidx.lifecycle.LiveData
import com.bajicdusko.notes.domain.model.Note
import com.bajicdusko.notes.domain.model.ResponseWrapper

interface NotesRepository {

  fun getAllNotes(): LiveData<ResponseWrapper<List<Note>>>
  fun addNote(note: Note): LiveData<ResponseWrapper<Note>>
}
