import kotlinx.coroutines.*

/**
 * 协程调度器与上下文（中）
 * @author linnanwei
 * @version 1.0.0
 * @since 1.0.0
 * Date： 2019/10/15
 */

/** 父协程被取消时，所有的子协程也会被递归取消（GlobalScope除外 ） **/
//fun main() = runBlocking<Unit>{
//    val request = launch{
//        // 孵化两个子作业，其中一个通过GlobalScope启动
//        GlobalScope.launch {
//            println("job1: I run in GlobalScope and execute independently")
//            kotlinx.coroutines.delay(1000)
//            println("job1: I am not affected by cancellation of the request")
//        }
//
//        // 另一个则承袭了父协程的上下文
//        launch {
//            kotlinx.coroutines.delay(100)
//            println("job2: I am a child of the request coroutine")
//            kotlinx.coroutines.delay(1000)
//            // 当父作业取消后，该代码不会被执行
//            println("job2: I will not execute this line if my parent request is cancelled")
//        }
//    }
//    kotlinx.coroutines.delay(500)
//    request.cancel()
//    kotlinx.coroutines.delay(1000)
//    println("main: Who has survived request cancellation?")  // job1
//}

/** 父协程的职责 **/
fun main() = runBlocking<Unit> {
    val request = launch {
        repeat(3) { i ->
            launch(CoroutineName("coroutine-$i")) {
                kotlinx.coroutines.delay((i + 1) * 200L)
                println("${Thread.currentThread().name} is done.")
            }
        }
        println("request: I'm  done and I don't explicitly join my children that are still active")
    }
    request.join() // 等待请求的完成，包括所有子协程
    println("Now processing of the request is complete")
}
