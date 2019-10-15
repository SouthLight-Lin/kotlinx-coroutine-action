import kotlinx.coroutines.*

/**
 * 作用域构建器
 * @author linnanwei
 * @version 1.0.0
 * @since 1.0.0
 * Date： 2019/10/10
 */

fun main() = runBlocking {
    //    launch {
    //        kotlinx.coroutines.delay(200)
    //        println("Task from runBlocking")
    //    }
    //
    //    coroutineScope {
    //        launch {
    //            kotlinx.coroutines.delay(500)
    //            println("Task from nested launch")
    //        }
    //        kotlinx.coroutines.delay(100)
    //        println("Task from coroutine scope")
    //    }
    //
    //    println("Coroutine scope is over")

}

fun cancel() = runBlocking {
    launch(Dispatchers.IO) { }
}

