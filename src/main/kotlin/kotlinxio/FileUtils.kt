package kotlinxio

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.io.core.ByteReadPacket
import kotlinx.io.core.buildPacket
import java.io.File

/**
 * 使用Kotlin IO
 * @author SouthLight-Lin on 2019/11/17
 */

fun main(args: Array<String>) = runBlocking{
    val tee = Channel<ByteReadPacket>()

    val file = File("./pom.xml")
    // 将内容读取到packet
    val packet = buildPacket(headerSizeHint = 1) {
        writeStringUtf8(file.readText())
    }
    // 将copy后的内容发送的channel
    launch {
        tee.send(packet.copy())
    }
    // 输出管道接收到的内容
    launch {
        println(tee.receive().readText())
    }

    delay(1000)
    Unit
}