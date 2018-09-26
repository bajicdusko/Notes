package com.bajicdusko.notes.domain.usecase

import androidx.lifecycle.LiveData
import com.bajicdusko.notes.domain.model.ResponseWrapper

interface UseCase<PARAM, T> {

  fun execute(p: PARAM): LiveData<ResponseWrapper<T>>
}
