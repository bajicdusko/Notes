package com.bajicdusko.notes.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.bajicdusko.notes.data.repository.DbNotesRepository
import com.bajicdusko.notes.domain.model.Note
import com.bajicdusko.notes.domain.model.ResponseWrapper
import com.bajicdusko.notes.domain.model.wrappedData
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.mock
import java.util.*

/**
 * Created by Dusko Bajic on 24.09.18.
 * GitHub @bajicdusko
 */
@RunWith(JUnit4::class)
class CreateNoteTest {

  @JvmField
  @Rule
  val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

  @Test
  fun createNote_shouldCreateNewNote_givenThatDatabaseIsEmpty(){

    val dbNotesRepositoryMock = mock(DbNotesRepository::class.java)

    val note = Note("test note", Date())

    doReturn(MutableLiveData<ResponseWrapper<Note>>().apply {
      value = wrappedData { note }
    }).`when`(dbNotesRepositoryMock).addNote(note)

    val createdNote = CreateNote(dbNotesRepositoryMock).execute(note)

    Assert.assertNotNull(createdNote)
    createdNote.observeForever {
      Assert.assertEquals("test note", it.data!!.timestamp)
    }
  }
}