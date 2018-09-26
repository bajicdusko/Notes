package com.bajicdusko.notes.domain.model

/**
 * Created by Dusko Bajic on 08.09.18.
 * GitHub @bajicdusko
 */
class ResponseWrapper<D>(var data: D? = null, var error: Throwable? = null)

fun <D> wrappedData(dataFn: () -> D) = ResponseWrapper(data = dataFn())
fun <D> wrappedError(errorFn: () -> Throwable) = ResponseWrapper<D>(error = errorFn())