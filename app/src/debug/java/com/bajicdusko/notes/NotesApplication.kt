package com.bajicdusko.notes

import com.facebook.stetho.Stetho

/**
 * Created by Dusko Bajic on 25.09.18.
 * GitHub @bajicdusko
 */
class NotesApplication : BaseNotesApplication() {

  override fun onCreate() {
    super.onCreate()

    Stetho.initializeWithDefaults(this)
  }
}