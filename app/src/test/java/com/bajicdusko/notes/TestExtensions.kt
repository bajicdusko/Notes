package com.bajicdusko.notes

import com.bajicdusko.notes.data.BackgroundExecutor
import com.bajicdusko.notes.data.threadPool
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.junit.runners.model.Statement
import org.mockito.Mockito
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.RejectedExecutionHandler
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

/**
 * Created by Dusko Bajic on 25.09.18.
 * GitHub @bajicdusko
 */


val testExecutor: (Unit) -> Unit = { }

fun <T> anyNotNull(): T {
  Mockito.any<T>()
  return null as T
}

inline fun <reified T> mock() = Mockito.mock(T::class.java)

class ThreadPoolExecutorRule : TestWatcher() {
  override fun apply(base: Statement?, description: Description?): Statement {
    return object : Statement() {
      override fun evaluate() {
        BackgroundExecutor.unitTestMode = true

        try {
          base?.evaluate()
        } finally {
          BackgroundExecutor.unitTestMode = false
        }
      }
    }
  }
}