import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * @author linnanwei
 * @version 1.0.0
 * @since 1.0.0
 * Date： 2019/10/10
 */

fun main(args: Array<String>) = runBlocking<Unit> {

    // 等待一个作业
    val job = GlobalScope.launch {
        kotlinx.coroutines.delay(1000)
        println("World")
    }

    println("Hello")
    job.join()  // 等待直到子协程执行完

    launch {
        repeat(100) {
            println("${Thread.currentThread().name} val: ${it}")
        }
    }

    println("${Thread.currentThread().name} job: done")
}

inline fun func(crossinline l:() -> Unit) {
    l.invoke()
    return
}