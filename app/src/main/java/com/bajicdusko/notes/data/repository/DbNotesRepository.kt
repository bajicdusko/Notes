package com.bajicdusko.notes.data.repository

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.bajicdusko.notes.data.backgroundExecutor
import com.bajicdusko.notes.data.db.NoteDao
import com.bajicdusko.notes.data.db.model.NoteDb
import com.bajicdusko.notes.data.mapper.asDb
import com.bajicdusko.notes.data.mapper.asNotes
import com.bajicdusko.notes.domain.model.Note
import com.bajicdusko.notes.domain.model.ResponseWrapper
import com.bajicdusko.notes.domain.model.wrappedData

open class DbNotesRepository(private val noteDao: NoteDao) : NotesRepository {

  override fun getAllNotes(): LiveData<ResponseWrapper<List<Note>>> {

    val noteDbLiveData: LiveData<List<NoteDb>> = noteDao.getAll()

    return Transformations.map(noteDbLiveData) {
      val notes = it.asNotes()
      wrappedData { notes }
    }
  }

  /**
   * Method used for async writing of note into the database.
   *
   * @note An actual note, we're writing into the database
   */
  override fun addNote(note: Note): LiveData<ResponseWrapper<Note>> {
    val responseLiveData = MutableLiveData<ResponseWrapper<Note>>()
    backgroundExecutor.execute(
        onBackground = {
          noteDao.insert(note.asDb())
        },
        onMainThread = {
          responseLiveData.value = wrappedData { note }
        }
    )
    return responseLiveData
  }
}
