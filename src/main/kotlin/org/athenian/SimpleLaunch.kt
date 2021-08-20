package org.athenian

import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.milliseconds

fun main() {

  runBlocking {
    val job: Job =
      launch {
        delay(milliseconds(1_000))
        println("${Thread.currentThread()} has run.")
      }

    println("Waiting for launch to complete")
    job.join()
  }

  println("Exited runBlocking")
}