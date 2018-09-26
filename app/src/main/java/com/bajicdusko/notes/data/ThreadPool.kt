package com.bajicdusko.notes.data

import android.os.Handler
import android.os.Looper
import java.util.concurrent.*
import kotlin.concurrent.thread

/**
 * Created by Dusko Bajic on 13.09.18.
 * GitHub @bajicdusko
 */

class BackgroundExecutor {

  companion object {
    var unitTestMode = false
  }

  fun execute(onBackground: () -> Unit, onMainThread: () -> Unit) {
    if (!unitTestMode) {
      threadPool.execute {
        onBackground()
        val handler = Handler(Looper.getMainLooper())
        handler.post(onMainThread)
      }
    } else {
      onBackground()
      onMainThread()
    }
  }
}

class SimpleThreadFactory : ThreadFactory {
  override fun newThread(runnable: Runnable?): Thread = Thread(runnable)
}

val threadPool by lazy {
  ThreadPoolExecutor(
      Runtime.getRuntime().availableProcessors(),
      Runtime.getRuntime().availableProcessors() * 2,
      10,
      TimeUnit.SECONDS,
      LinkedBlockingQueue<Runnable>(),
      SimpleThreadFactory()
  )
}

val backgroundExecutor: BackgroundExecutor = BackgroundExecutor()

