import kotlinx.coroutines.*

/**
 * 取消和超时
 * @author linnanwei
 * @version 1.0.0
 * @since 1.0.0
 * Date： 2019/10/15
 */
/** 普通的取消 **/
//fun main() = runBlocking {
//    val job = GlobalScope.launch {
//        repeat(1000) { i ->
//            println("job: I'm sleeping $i ..")
//            kotlinx.coroutines.delay(500)
//        }
//    }
//
//    delay(1300)
//    println("main: I'm tried of waiting")
////    job.cancel() // 取消该作业
////    job.join()   // 等待作业执行结束
//    // 上面两个函数可以合并成一个
//    job.cancelAndJoin()
//    println("main: Now I can quit.")
//}

/** 取消是协作的，也就是说调用cancel()只是通知协程协程可取消，但是
 * 取消还是不取消全看协程自己 **/
//fun main() = runBlocking {
//    val startTime = System.currentTimeMillis()
//    val job = launch(Dispatchers.Default) {
//        var nextPrintTime = startTime
//        var i = 0
//        while (i < 5) {
//            if (System.currentTimeMillis() >= nextPrintTime) {
//                println("job: I'm sleeping ${i++} ...")
//                nextPrintTime += 500L
//            }
//        }
//    }
//    kotlinx.coroutines.delay(1300)
//    println("main: I'm tried of waiting")
//    job.cancelAndJoin()  // 就算是取消了，协程还是会自己循环执行
//    println("main: Now I can quit.")
//}

/** 使代码可取消 **/
//fun main() = runBlocking {
//    val startTime = System.currentTimeMillis()
//    val job = launch(Dispatchers.Default) {
//        var nextPrintTime = startTime
//        var i = 0
//        // 可以被取消的循环计算
//        while (isActive) {
//            if (System.currentTimeMillis() >= nextPrintTime) {
//                println("job: I'm sleeping ${i++} ..")
//                nextPrintTime += 500L
//            }
//        }
//    }
//
//    kotlinx.coroutines.delay(1300L)
//    println("main:I'm tried of waiting")
//    job.cancelAndJoin()
//    println("main: Now I can quit.")
//}

/** 运行不能取消的代码块（没有循环，找不到出口的） **/
//fun main() = runBlocking {
//    val job = launch {
//        try {
//            repeat(1000) {
//                println("job: I'm sleeping $it ...")
//                kotlinx.coroutines.delay(500L)
//            }
//        }finally {
//            withContext(NonCancellable) {
//                println("job: I'm running finally")
//                kotlinx.coroutines.delay(1000L)
//                println("job: And I've just delayed for 1 sec because I'm non-cancellable")
//            }
//        }
//    }
//    kotlinx.coroutines.delay(1300)
//    println("main: I'm tired of waiting")
//    job.cancelAndJoin()
//    println("main: Now I can quit.")
//}

/** 超时
 *  抛出TimeoutCancellationException异常
 *  但是CancellationException被认为是协程执行结束的正常原因，所以不会抛出
 * **/
fun main() = runBlocking {
    withTimeout(1300L) {
        repeat(1000) {
            println("I'm  sleeping $it ...")
            kotlinx.coroutines.delay(500)
        }
    }
}