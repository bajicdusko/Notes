package com.bajicdusko.notes.data.mapper

import com.bajicdusko.notes.data.db.model.NoteDb
import com.bajicdusko.notes.domain.model.Note
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.*

/**
 * Created by Dusko Bajic on 24.09.18.
 * GitHub @bajicdusko
 */
@RunWith(JUnit4::class)
class NoteMapperTest {

  @Test
  fun noteMapperTest_shouldConvertNoteToNoteDb(){
    val content = "test content"
    val timestamp = Date()
    val note = Note(content, timestamp)
    val noteDb = note.asDb()

    Assert.assertEquals(note.content, noteDb.content)
    Assert.assertEquals(note.timestamp.time, noteDb.timestamp)
  }

  @Test
  fun noteMapperTest_shouldConvertNoteDbToNote(){
    val content = "test content"
    val timestamp = Date()
    val noteDb = NoteDb(content = content, timestamp = timestamp.time)
    val note = noteDb.asNote()

    Assert.assertEquals(noteDb.content, note.content)
    Assert.assertEquals(noteDb.timestamp, note.timestamp.time)
  }
}