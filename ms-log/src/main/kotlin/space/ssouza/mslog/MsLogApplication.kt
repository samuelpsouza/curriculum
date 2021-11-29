package space.ssouza.mslog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MsLogApplication

fun main(args: Array<String>) {
    runApplication<MsLogApplication>(*args)
}
