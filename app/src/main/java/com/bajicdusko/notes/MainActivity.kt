package com.bajicdusko.notes

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bajicdusko.notes.viewmodel.NotesViewModel
import com.bajicdusko.notes.viewmodel.ViewModelFactory
import com.bajicdusko.notes.viewmodel.createViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.activity_main_rl_notes as rlNotes
import kotlinx.android.synthetic.main.content_main.fab
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

  @Inject lateinit var viewModelFactory: ViewModelFactory

  private lateinit var notesViewModel: NotesViewModel

  private lateinit var notesAdapter: NotesAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    setSupportActionBar(toolbar)

    notesViewModel = createViewModel(viewModelFactory)

    rlNotes.apply {
      layoutManager = LinearLayoutManager(this@MainActivity)
      adapter = NotesAdapter()
    }

    fab.setOnClickListener { _ ->
      NewNoteBottomDialogFragment().show(supportFragmentManager, "newNote")
    }
  }

  override fun onResume() {
    super.onResume()
    notesViewModel.getNotes().observe(this, Observer { notesResponseWrapper ->
      notesResponseWrapper.data?.let {
        notesAdapter.onDataChanged(it)
      } ?: notesAdapter.onDataChanged(emptyList())

      notesResponseWrapper.error?.let {
        toast("Error occurred. ${it.message}")
      }
    })
  }

  private fun onAddNote(noteContent: String) {
    notesViewModel.addNote(noteContent)
  }
}

fun Context.toast(message: String): Unit = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

