package org.athenian

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

@ExperimentalCoroutinesApi
fun CoroutineScope.numbersFrom(start: Int) =
    produce<Int> {
        println("Creating numbersFrom")
        var x = start
        while (true)
            send(x++)
    }

@ExperimentalCoroutinesApi
fun CoroutineScope.filter(numbers: ReceiveChannel<Int>, prime: Int) =
    produce<Int> {
        println("Creating filter for $prime")
        for (x in numbers)
            if (x % prime != 0)
                send(x)
            else
                println("$x divisible by $prime")
    }

@ExperimentalCoroutinesApi
fun main() {
    runBlocking {
        var cur = numbersFrom(2)
        repeat(100) {
            val prime = cur.receive()
            println("#$it: $prime")
            cur = filter(cur, prime)
        }
        coroutineContext.cancelChildren()
    }
}