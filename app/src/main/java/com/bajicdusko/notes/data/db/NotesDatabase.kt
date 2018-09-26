package com.bajicdusko.notes.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bajicdusko.notes.data.db.model.NoteDb

/**
 * Created by Dusko Bajic on 08.09.18.
 * GitHub @bajicdusko
 */

@Database(entities = [NoteDb::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {

  abstract fun getNoteDao(): NoteDao
}