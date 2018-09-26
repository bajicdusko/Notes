package com.bajicdusko.notes.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.bajicdusko.notes.data.db.model.NoteDb

/**
 * Created by Dusko Bajic on 08.09.18.
 * GitHub @bajicdusko
 */

@Dao
interface NoteDao {

  @Query("SELECT * FROM note")
  fun getAll(): LiveData<List<NoteDb>>

  @Insert
  fun insert(noteDb: NoteDb): Long
}