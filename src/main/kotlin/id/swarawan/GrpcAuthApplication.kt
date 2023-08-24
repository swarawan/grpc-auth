package id.swarawan

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GrpcAuthApplication

fun main(args: Array<String>) {
	runApplication<GrpcAuthApplication>(*args)
}
