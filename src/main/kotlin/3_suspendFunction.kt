import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.lang.ArithmeticException
import kotlin.system.measureTimeMillis

/**
 * 挂起函数
 * @author linnanwei
 * @version 1.0.0
 * @since 1.0.0
 * Date： 2019/10/15
 */
suspend fun doSomethingUsefulOne(): Int {
    delay(1000)
    return 23
}

suspend fun doSomethingUsefulTwo(): Int {
    delay(1000)
    return 24
}

/** 使用async并发
 *  async启动单独一个协程，返回一个Deferred——一个轻量的非阻塞future
 * **/
//fun main() = runBlocking {
//    val time = measureTimeMillis {
//        val one = async { doSomethingUsefulOne() }
//        val two = async { doSomethingUsefulTwo() }
//        println("The answer is ${one.await() + two.await()}")
//    }
//    println("Completed in $time ms")
//}

/**
 * 如果在corcurrentSum 函数内部发生了错误，并且它抛出了一个异常
 * 所有在作用域中启动的协程都会被取消
 */
suspend fun concurrentSum(): Int = coroutineScope {
    val one = async { doSomethingUsefulOne() }
    val two = async { doSomethingUsefulTwo() }
    one.await() + two.await()
}

suspend fun failConcurrentSum(): Int = coroutineScope {
    val one = async<Int> {
        try {
            kotlinx.coroutines.delay(Long.MAX_VALUE)
            42
        } finally {
            println("First child was cancelled")
        }
    }

    val two = async<Int> {
        println("Second child throws an exception")
        throw ArithmeticException()
    }

    one.await() + two.await()
}

fun main() = runBlocking<Unit> {
    try {
        // 当其中一个协程抛出异常时
        // 零个协程也回终止（调用finally)
        failConcurrentSum()
    } catch (e: ArithmeticException) {
        println("Computation failed with ArithemeException")
    }
}