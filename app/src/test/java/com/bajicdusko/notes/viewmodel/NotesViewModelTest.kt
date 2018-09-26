package com.bajicdusko.notes.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.bajicdusko.notes.anyNotNull
import com.bajicdusko.notes.domain.model.Note
import com.bajicdusko.notes.domain.model.ResponseWrapper
import com.bajicdusko.notes.domain.model.wrappedData
import com.bajicdusko.notes.domain.usecase.CreateNote
import com.bajicdusko.notes.domain.usecase.GetAllNotes
import com.bajicdusko.notes.mock
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.mock

/**
 * Created by Dusko Bajic on 25.09.18.
 * GitHub @bajicdusko
 */

@RunWith(JUnit4::class)
class NotesViewModelTest {

  @JvmField
  @Rule
  val instantRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

  @Test
  fun notesViewModel_givenNoteContent_shouldCreateANewNote() {

    val createNoteMock = mock(CreateNote::class.java)
    val getAllNotesMock: GetAllNotes = mock()

    val noteContent = "test content"
    doReturn(MutableLiveData<ResponseWrapper<Note>>().apply {
      value = wrappedData { Note(noteContent) }
    }).`when`(createNoteMock).execute(anyNotNull())

    val notesViewModel = NotesViewModel(createNoteMock, getAllNotesMock)
    val addedNoteLiveData = notesViewModel.addNote(noteContent)

    Assert.assertNotNull(addedNoteLiveData)
    addedNoteLiveData.observeForever {
      Assert.assertEquals(noteContent, it.data!!.content)
    }
  }

  @Test
  fun notesViewModel_givenNotEmptyDatabase_shouldGetNotes() {

    val createNoteMock: CreateNote = mock()
    val getAllNotesMock: GetAllNotes = mock()

    doReturn(MutableLiveData<ResponseWrapper<List<Note>>>().apply {
      value = wrappedData {
        listOf(
            Note("note1"),
            Note("note2"),
            Note("note3")
        )
      }
    }).`when`(getAllNotesMock).execute(anyNotNull())

    val notesViewModel = NotesViewModel(createNoteMock, getAllNotesMock)

    val notesLiveData = notesViewModel.getNotes()

    Assert.assertNotNull(notesLiveData)
    notesLiveData.observeForever {
      Assert.assertEquals("note1", it.data!![0].content)
    }
  }
}