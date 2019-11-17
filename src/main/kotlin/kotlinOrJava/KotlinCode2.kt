@file:JvmName("KotlinCode")  // 指定该文件中生成类的类名
@file:JvmMultifileClass            //  将类名跟包名相同的，都生成在同一个类中
package kotlinOrJava

/**
 * @author SouthLight-Lin on 2019/11/17
 */

fun doFunction(block: () -> Unit) {
    block.invoke()
}