package com.bajicdusko.notes.di

import android.app.Activity
import android.content.Context
import androidx.room.Room
import com.bajicdusko.notes.MainActivity
import com.bajicdusko.notes.NotesApplication
import com.bajicdusko.notes.data.db.NoteDao
import com.bajicdusko.notes.data.db.NotesDatabase
import com.bajicdusko.notes.data.repository.DbNotesRepository
import com.bajicdusko.notes.domain.usecase.CreateNote
import com.bajicdusko.notes.domain.usecase.GetAllNotes
import com.bajicdusko.notes.viewmodel.ViewModelFactory
import dagger.*
import dagger.android.ActivityKey
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap
import javax.inject.Named
import javax.inject.Singleton


/**
 * Created by Dusko Bajic on 08.09.18.
 * GitHub @bajicdusko
 */
@Component(
    modules = [AndroidInjectionModule::class, AppModule::class, UseCaseModule::class, MainActivityModule::class])
@Singleton
interface NotesComponent {
  fun inject(notesApplication: NotesApplication)

  @Component.Builder
  interface Builder {
    fun withAppModule(appModule: AppModule): Builder
    fun build(): NotesComponent
  }
}

@Module
class AppModule(private val notesApplication: NotesApplication) {

  @Provides
  @Named("app")
  fun provideAppContext(): Context {
    return notesApplication
  }
}

@Module
class DatabaseModule {

  @Provides
  @Singleton
  fun provideNotesDatabase(@Named("app") appContext: Context): NotesDatabase {
    return Room.databaseBuilder(appContext, NotesDatabase::class.java, "noteDb").build()
  }

//  @Singleton
  @Provides
  fun provideNoteDao(notesDatabase: NotesDatabase): NoteDao {
    return notesDatabase.getNoteDao()
  }
}

@Module(includes = [DatabaseModule::class])
class RepositoryModule {

  @Provides
  fun provideDbNotesRepository(noteDao: NoteDao): DbNotesRepository {
    return DbNotesRepository(noteDao)
  }
}

@Module(includes = [RepositoryModule::class])
class UseCaseModule {

  @Provides
  fun provideCreateNote(dbNotesRepository: DbNotesRepository): CreateNote {
    return CreateNote(dbNotesRepository)
  }

  @Provides
  fun provideGetAllNotes(dbNotesRepository: DbNotesRepository): GetAllNotes {
    return GetAllNotes(dbNotesRepository)
  }
}

@Module(subcomponents = [MainActivityComponent::class])
abstract class MainActivityModule {

  @Binds
  @IntoMap
  @ActivityKey(MainActivity::class)
  abstract fun bindYourActivityInjectorFactory(
      builder: MainActivityComponent.Builder): AndroidInjector.Factory<out Activity>

  @Module
  companion object {

    @JvmStatic
    @Provides
    fun providesViewModelFactory(createNote: CreateNote, getAllNotes: GetAllNotes): ViewModelFactory {
      return ViewModelFactory(createNote, getAllNotes)
    }
  }
}

@Subcomponent
interface MainActivityComponent : AndroidInjector<MainActivity> {

  @Subcomponent.Builder
  abstract class Builder : AndroidInjector.Builder<MainActivity>()
}