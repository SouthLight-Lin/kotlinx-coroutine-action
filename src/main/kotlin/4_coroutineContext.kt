import kotlinx.coroutines.*
import kotlin.math.log

/**
 * 协程上下文与调度器（上）
 * @author linnanwei
 * @version 1.0.0
 * @since 1.0.0
 * Date： 2019/10/15
 */

fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

fun main() = runBlocking<Unit> {
    // 运行在父协程的上下文中，即runBlocking主协程
    launch {
        println("main runBlocking   : I'm working in thread ${Thread.currentThread().name}")
    }
    // 不受限的——  将工作在主线程中
    launch(Dispatchers.Unconfined) {
        println("unconfined    : I'm working in thread ${Thread.currentThread().name}")
    }
    // 将会获取默认调度器
    launch(Dispatchers.Default) {
        println("Default    : I'm working in thread ${Thread.currentThread().name}")
    }
    // 将使它获得一个新的线程
    launch(newSingleThreadContext("MyOwnThread")) {
        println("newSingleThreadContext   : I'm working in thread ${Thread.currentThread().name}")
    }


    /** 详细的unconfined对比 **/
    // 非受限的—— 将和主线程一起工作，然后切换到默认的执行者线程中执行
    launch(Dispatchers.Unconfined) {
        println("Unconfied   :I'm working in thread ${Thread.currentThread().name}")
        kotlinx.coroutines.delay(500)
        println("Unconfied   :I'm working in thread ${Thread.currentThread().name}")
    }
    // 父协程的上下文，主runBlocking协程
    launch {
        println("main runBlocking   :I'm working in thread ${Thread.currentThread().name}")
        kotlinx.coroutines.delay(1000)
        println("main runBlocking    :I'm working in thread ${Thread.currentThread().name}")
    }

    // 协程的job是上下文的一部分
    println("My job is ${coroutineContext[Job]}")
}
