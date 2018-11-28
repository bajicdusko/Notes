package com.bajicdusko.notes

import android.app.Activity
import android.app.Application
import com.bajicdusko.notes.di.AppModule
import com.bajicdusko.notes.di.DaggerNotesComponent
import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by Dusko Bajic on 08.09.18.
 * GitHub @bajicdusko
 */
open class NotesApplication : Application(), HasActivityInjector {

  @Inject lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

  override fun onCreate() {
    super.onCreate()

    DaggerNotesComponent.builder()
        .withAppModule(AppModule(this))
        .build()
        .inject(this)

    Stetho.initializeWithDefaults(this)
  }

  override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityInjector
}