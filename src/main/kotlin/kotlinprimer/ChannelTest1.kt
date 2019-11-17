package kotlinprimer

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


/**
 * produce：Channel   用于执行协程任务，并得到一个channel
 * @author SouthLight-Lin on 2019/11/5
 */
fun main(args: Array<String>):Unit = runBlocking {
    val c = Channel<Int>()
    launch {
        get(c)
    }

    launch {
        put(c)
    }
    Unit
}

suspend fun get(channel: Channel<Int>) {
    while (true) {
        println(channel.receive())
    }
}

suspend fun put(channel: Channel<Int>) {
    var i = 0
    while (true) {
        println(channel.send(i++))
    }
}