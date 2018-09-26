package com.bajicdusko.notes

import android.app.Activity
import android.app.Application
import com.bajicdusko.notes.di.AppModule
import com.bajicdusko.notes.di.DaggerNotesComponent
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

/**
 * Created by Dusko Bajic on 08.09.18.
 * GitHub @bajicdusko
 */
open class BaseNotesApplication : Application(), HasActivityInjector {

  @Inject lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

  override fun onCreate() {
    super.onCreate()

    DaggerNotesComponent.builder()
        .withAppModule(AppModule(this))
        .build()
        .inject(this)
  }

  override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityInjector
}