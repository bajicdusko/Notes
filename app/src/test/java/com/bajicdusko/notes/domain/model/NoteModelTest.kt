package com.bajicdusko.notes.domain.model

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.*

/**
 * Created by Dusko Bajic on 08.09.18.
 * GitHub @bajicdusko
 */

@RunWith(JUnit4::class)
class NoteModelTest {

  @Test
  fun shouldCreateNoteModel(){
    val timestamp = Date()
    val note = Note("test note", timestamp)
    Assert.assertEquals("test note", note.content)
    Assert.assertEquals(timestamp, note.timestamp)
  }
}