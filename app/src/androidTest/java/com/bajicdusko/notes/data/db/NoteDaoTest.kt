package com.bajicdusko.notes.data.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.bajicdusko.notes.data.db.model.NoteDb
import org.junit.*
import org.junit.runner.RunWith

/**
 * Created by Dusko Bajic on 25.09.18.
 * GitHub @bajicdusko
 */

@RunWith(AndroidJUnit4::class)
class NoteDaoTest {

  @JvmField
  @Rule
  val instantExecutorRule = InstantTaskExecutorRule()

  lateinit var notesDatabase: NotesDatabase

  @Before
  fun setUp() {

    notesDatabase = Room.databaseBuilder(InstrumentationRegistry.getTargetContext(),
        NotesDatabase::class.java, "temp.db").build()

    val openHelper = notesDatabase.openHelper
    val writableDatabase = openHelper.writableDatabase
    writableDatabase.execSQL("INSERT INTO note VALUES (1, 'note1', 1)")
    writableDatabase.execSQL("INSERT INTO note VALUES (2, 'note2', 2)")
  }

  @Test
  fun noteDao_givenNonEmptyDatabase_shouldReadTheNotes() {

    val noteDao = notesDatabase.getNoteDao()
    val allNotes = noteDao.getAll()

    allNotes.observeForever {
      Assert.assertEquals("note1", it[0].content)
      Assert.assertNotEquals("note1", it[1].content)
    }
  }

  @Test
  fun noteDao_givenNonEmptyDatabase_shouldInsertNewNote(){

    val noteDao = notesDatabase.getNoteDao()
    val id = noteDao.insert(NoteDb(null, "note3", 3))

    Assert.assertEquals(3L, id)
  }

  @After
  fun tearDown(){
    notesDatabase.openHelper.writableDatabase.delete("note", null, null)
  }
}