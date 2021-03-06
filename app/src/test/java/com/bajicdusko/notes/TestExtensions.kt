package com.bajicdusko.notes

import com.bajicdusko.notes.data.BackgroundExecutor
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.junit.runners.model.Statement
import org.mockito.Mockito

fun <T> anyNotNull(): T {
  Mockito.any<T>()
  return null as T
}

inline fun <reified T> mock(): T = Mockito.mock(T::class.java)

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