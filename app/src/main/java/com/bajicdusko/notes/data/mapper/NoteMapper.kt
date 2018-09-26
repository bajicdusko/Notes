package com.bajicdusko.notes.data.mapper

import com.bajicdusko.notes.data.db.model.NoteDb
import com.bajicdusko.notes.domain.model.Note
import java.util.*

/**
 * Created by Dusko Bajic on 08.09.18.
 * GitHub @bajicdusko
 */

fun NoteDb.asNote() = Note(content, Date())
fun Note.asDb() = NoteDb(content = content, timestamp = timestamp.time)

fun List<NoteDb>.asNotes(): List<Note> {
  val notes = mutableListOf<Note>()
  forEach {
    notes.add(it.asNote())
  }
  return notes
}

fun List<Note>.asNoteDbs(): List<NoteDb> {
  val noteDbs = mutableListOf<NoteDb>()
  forEach {
    noteDbs.add(it.asDb())
  }
  return noteDbs
}