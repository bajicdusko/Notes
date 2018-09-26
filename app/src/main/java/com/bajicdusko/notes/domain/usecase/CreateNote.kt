package com.bajicdusko.notes.domain.usecase

import androidx.lifecycle.LiveData
import com.bajicdusko.notes.data.repository.DbNotesRepository
import com.bajicdusko.notes.domain.model.Note
import com.bajicdusko.notes.domain.model.ResponseWrapper

class CreateNote(private val dbNotesRepository: DbNotesRepository) : UseCase<Note, Note> {
  override fun execute(note: Note): LiveData<ResponseWrapper<Note>> = dbNotesRepository.addNote(note)
}
