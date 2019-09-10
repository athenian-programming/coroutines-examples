package org.athenian

import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.time.ExperimentalTime
import kotlin.time.milliseconds

@ExperimentalTime
fun main() {
    runBlocking {
        timeout1()
        timeout2()
        timeout3()
    }
}

@ExperimentalTime
suspend fun timeout1() {
    try {
        withTimeout(1300) {
            repeat(1_000) { i ->
                log("I'm sleeping $i ...")
                delay(500.milliseconds)
            }
        }
    } catch (e: TimeoutCancellationException) {
        log("Caught ${e.javaClass.simpleName}")
    }
}

@ExperimentalTime
suspend fun timeout2() {
    val result =
        withTimeoutOrNull(1300) {
            repeat(1_000) { i ->
                log("I'm sleeping $i ...")
                delay(500.milliseconds)
            }
            "Done"
        }
    log("Result #1 is $result")
}

@ExperimentalTime
suspend fun timeout3() {
    val result =
        withTimeoutOrNull(1_300) {
            repeat(2) { i ->
                log("I'm sleeping $i ...")
                delay(500.milliseconds)
            }
            "Done"
        }
    log("Result #2 is $result")
}