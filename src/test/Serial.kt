package test

import java.io.*
import java.nio.file.Paths

val file = Paths.get("D:/develops/projects/nyethack/src/test/Serial.bin").toFile()

fun main() {
    writeMC()
}

fun writeMC() {
    val mc = MyClass("mc1")
    val oos = ObjectOutputStream(FileOutputStream(file))
    oos.use {
        it.writeObject(mc)
    }
}

fun readMC() {
    val ois = ObjectInputStream(FileInputStream(file))
    val mc = ois.use { it.readObject() as MyClass }
    println(mc)
}

data class MyClass(val name: String)  {
    private companion object {
      const val serialVersionUID = 2L
    }
}
