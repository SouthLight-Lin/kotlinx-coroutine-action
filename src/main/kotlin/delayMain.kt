import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @author linnanwei
 * @version 1.0.0
 * @since 1.0.0
 * Date： 2019/10/12
 */
fun main(args: Array<String>) {

    print("Start")

    GlobalScope.launch {
        // delay比Thead.sleep效率更高,
        // 它不会阻塞一个线程，但是会挂起协程自身。当这个协程处于等待状态时，该线程会返回线程池
        // 当等待结束时，这个协程会在线程池中的空闲线程上恢复
        delay(1000)
        for (i in 0..100) {
            println(Thread.currentThread().name + " " + i)
        }
    }

    GlobalScope.launch {
        for (i in 11..200) {
            println(Thread.currentThread().name + " " + i)
        }
    }

    Thread.sleep(5000)
    print("stop")

}