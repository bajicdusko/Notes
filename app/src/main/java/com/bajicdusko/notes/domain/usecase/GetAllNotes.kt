package com.bajicdusko.notes.domain.usecase

import androidx.lifecycle.LiveData
import com.bajicdusko.notes.data.repository.DbNotesRepository
import com.bajicdusko.notes.domain.model.Note
import com.bajicdusko.notes.domain.model.ResponseWrapper

class GetAllNotes(private val dbNotesRepository: DbNotesRepository): UseCase<Unit, List<Note>> {

  override fun execute(p: Unit): LiveData<ResponseWrapper<List<Note>>> = dbNotesRepository.getAllNotes()
}
