package org.athenian

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.milliseconds

fun main() {
  runBlocking {
    val jobs = List(10) {
      launch {
        delay(milliseconds(1_000))
        println("${Thread.currentThread()} has run.")
      }
    }

    println("Waiting for launch list to complete")
    jobs.forEach { it.join() }
  }
  println("Exited runBlocking")
}