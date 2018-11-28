package com.bajicdusko.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bajicdusko.notes.domain.model.Note

class NotesAdapter : RecyclerView.Adapter<NoteViewHolder>() {

  private val notes = mutableListOf<Note>()

  fun onDataChanged(notes: List<Note>) {

    val diffCallback = object : DiffUtil.Callback() {
      override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
          this@NotesAdapter.notes[oldItemPosition] == notes[newItemPosition]

      override fun getOldListSize(): Int = this@NotesAdapter.notes.size

      override fun getNewListSize(): Int = notes.size

      override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
          this@NotesAdapter.notes[oldItemPosition] == notes[newItemPosition]
    }

    val calculateDiff: DiffUtil.DiffResult = DiffUtil.calculateDiff(diffCallback)

    this.notes.clear()
    this.notes.addAll(notes)
    calculateDiff.dispatchUpdatesTo(this)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
    return NoteViewHolder(
        LayoutInflater.from(parent.context).inflate(NoteViewHolder.LAYOUT_ID, parent, false))
  }

  override fun getItemCount(): Int = notes.size

  override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
    holder.bind(notes[position])
  }
}

class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {

  private val tvContent: TextView = view.findViewById(R.id.note_list_item_tv_content)
  private val tvTimestamp: TextView = view.findViewById(R.id.note_list_item_tv_timestamp)

  fun bind(note: Note) {
    tvContent.text = note.content
    tvTimestamp.text = note.timestamp.toString()
  }

  companion object {
    const val LAYOUT_ID: Int = R.layout.note_list_item
  }
}
