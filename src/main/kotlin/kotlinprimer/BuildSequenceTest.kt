package kotlinprimer

/**
 * @author SouthLight-Lin on 2019/11/5
 */

/**
 * buildSequence/yield：  用于执行会多次返回数据的协程任务
 * 斐波那契数列
 */
val fibonacci = sequence {
    yield(1)    // first Fibonacci number
    var cur = 1
    var next = 1
    while (true) {
        yield(next)
        val tmp = cur + next
        cur = next
        next = tmp
    }
}
// yield 可以按需调用，无线的返回数据，
//      yield非常适合一些无法预知终止条件的场景，比如直接读取写好的文本，每次读取一条
fun main(args: Array<String>) {
    fibonacci.take(20).iterator().forEach {
        print("$it ")
    }
}