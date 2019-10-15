import kotlinx.coroutines.*
import kotlin.coroutines.suspendCoroutine

/**
 * @author linnanwei
 * @version 1.0.0
 * @since 1.0.0
 * Date： 2019/10/12
 */

//fun main() {
//    GlobalScope.launch {
//        println(suspendFun())
//    }
//
//    Thread.sleep(3000)
//}

//fun main() = runBlocking<Unit> {
//    // 在后台启动一个新的协程并继续
//    GlobalScope.launch {
//        kotlinx.coroutines.delay(1000)
//        println("World!")
//    }
//    println("Hello,")
//    kotlinx.coroutines.delay(2000)
//}

fun main() = runBlocking<Unit> {
    launch {
        kotlinx.coroutines.delay(2000)
        println("Task from runBlocking")
    }
    // 在等待所有子协程执行完毕时不会阻塞当前线程
    coroutineScope {
        launch {
            kotlinx.coroutines.delay(500)
            println("Task from nested launch")
        }
        kotlinx.coroutines.delay(100)
        println("Task from coroutine scope")
    }
    println("Coroutine scope is over")
}

suspend fun suspendFun(): String {
    return suspendCoroutine<String> {
        Thread.sleep(2000)
        println(Thread.currentThread().name)
    }
}