package com.bajicdusko.notes.viewmodel

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

/**
 * Created by Dusko Bajic on 08.09.18.
 * GitHub @bajicdusko
 */

inline fun <reified T : ViewModel> FragmentActivity.createViewModel(viewModelFactory: ViewModelProvider.Factory): T {
  return ViewModelProviders.of(this, viewModelFactory).get(T::class.java)
}