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
class GetAllNotesTest {

  @JvmField
  @Rule
  val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

  @Test
  fun getAllNotesTest_givenNonEmptyDatabase_shouldGetAllNotes() {

    val dbNotesRepositoryMock = mock(DbNotesRepository::class.java)

    val notes = listOf(
        Note("note1", Date()),
        Note("note2", Date())
    )

    doReturn(
        MutableLiveData<ResponseWrapper<List<Note>>>().apply {
          value = wrappedData { notes }
        }).`when`(dbNotesRepositoryMock).getAllNotes()

    val getAllNotes = GetAllNotes(dbNotesRepositoryMock).execute(Unit)

    Assert.assertNotNull(getAllNotes)
    getAllNotes.observeForever {
      Assert.assertEquals("note1", it.data!![0].content)
      Assert.assertEquals("note2", it.data!![1].content)
    }
  }
}