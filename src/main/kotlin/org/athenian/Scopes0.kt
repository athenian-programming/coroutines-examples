package org.athenian

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.coroutineContext
import kotlin.time.ExperimentalTime
import kotlin.time.milliseconds

@ExperimentalTime
fun main() {
    runBlocking {

        launch { scopeCheck(this) }


        launch {
            delay(200.milliseconds)
            log("Task from runBlocking")
        }

        coroutineScope {
            launch {
                delay(500.milliseconds)
                log("Task from nested launch")
            }

            delay(100.milliseconds)
            log("Task from coroutine scope")
        }

        log("Coroutine scope is over")
    }
}

suspend fun scopeCheck(scope: CoroutineScope) {
    log("coroutineContext are equal: ${scope.coroutineContext === coroutineContext}")
}