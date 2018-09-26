package com.bajicdusko.notes.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bajicdusko.notes.ThreadPoolExecutorRule
import com.bajicdusko.notes.anyNotNull
import com.bajicdusko.notes.data.db.NoteDao
import com.bajicdusko.notes.data.db.model.NoteDb
import com.bajicdusko.notes.domain.model.Note
import com.bajicdusko.notes.domain.model.ResponseWrapper
import com.bajicdusko.notes.mock
import com.bajicdusko.notes.testExecutor
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatcher
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

/**
 * Created by Dusko Bajic on 08.09.18.
 * GitHub @bajicdusko
 */

@RunWith(MockitoJUnitRunner::class)
class DbNotesRepositoryTest {

  @JvmField
  @Rule
  val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

  @JvmField
  @Rule
  val threadPoolExecutorRule = ThreadPoolExecutorRule()

  @Test
  fun dbNotesRepository_givenThatDatabaseIsNotEmpty_shouldGetNotes() {

    val noteDaoMock = mock(NoteDao::class.java)

    val daoLiveDataResponse = MutableLiveData<List<NoteDb>>().apply {
      value =
        listOf(
            NoteDb(null, "note1", 1),
            NoteDb(null, "note2", 2)
        )
    }
    doReturn(daoLiveDataResponse).`when`(noteDaoMock).getAll()

    val dbNotesRepository = DbNotesRepository(noteDaoMock)
    val allNotes: LiveData<ResponseWrapper<List<Note>>> = dbNotesRepository.getAllNotes()

    Assert.assertNotNull(allNotes)
    allNotes.observeForever {
      Assert.assertEquals(it.data!![1].content, "note1")
    }
  }

  @Test
  fun dbNotesRepository_givenThatDatabaseIsEmpty_shouldCreateNewNote() {

    val noteDaoMock = mock(NoteDao::class.java)
    val dbNotesRepository = DbNotesRepository(noteDaoMock)

    doReturn(1L).`when`(noteDaoMock).insert(anyNotNull())

    val noteContent = "test note"
    val noteTimestamp = Date()
    val note = Note(noteContent, noteTimestamp)
    val createdNote = dbNotesRepository.addNote(note)

    Assert.assertNotNull(createdNote)
    createdNote.observeForever {
      Assert.assertEquals(noteContent, it.data!!.content)
      Assert.assertEquals(noteTimestamp.time, it.data!!.timestamp.time)
    }
  }
}