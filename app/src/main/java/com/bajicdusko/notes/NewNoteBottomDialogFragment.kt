package com.bajicdusko.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.dialog_fragment_new_note.new_note_dialog_fragment_til_content as tilContent
import kotlinx.android.synthetic.main.dialog_fragment_new_note.new_note_dialog_fragment_tiet_content as etContent
import kotlinx.android.synthetic.main.dialog_fragment_new_note.new_note_dialog_fragment_btn_add as btnAddNote

class NewNoteBottomDialogFragment : BottomSheetDialogFragment() {

  var onAddNote: (String) -> Unit = { }

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? = inflater.inflate(R.layout.dialog_fragment_new_note, container)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    btnAddNote.setOnClickListener {
      val noteContent = etContent.text.toString()
      if(noteContent.isNotEmpty()) {
        onAddNote(noteContent)
        dismiss()
      } else {
        tilContent.error = getString(R.string.noteMustNotbeEmpty)
      }
    }
  }
}