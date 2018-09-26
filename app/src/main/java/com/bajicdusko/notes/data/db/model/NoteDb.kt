package com.bajicdusko.notes.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * Created by Dusko Bajic on 08.09.18.
 * GitHub @bajicdusko
 */

@Entity(tableName = "Note")
class NoteDb(@PrimaryKey val id: Long? = null, val content: String, val timestamp: Long)