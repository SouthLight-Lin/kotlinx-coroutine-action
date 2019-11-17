@file:JvmName("KotlinCode")  // 指定该文件中生成类的类名
@file:JvmMultifileClass            //  将类名跟包名相同的，都生成在同一个类中

package kotlinOrJava

/**
 * @author SouthLight-Lin on 2019/11/17
 */

object Test {
    /**
     * 加了该注解，方便Java代码调用
     */
    @JvmStatic
    fun say(msg: String) {
        println(msg)
    }
}

fun doFunction1(block: (i: Int) -> Unit) {
    block.invoke(1)
}