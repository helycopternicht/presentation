package io.zensoft.auditing.envers

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EnversApplication

fun main(args: Array<String>) {
	runApplication<EnversApplication>(*args)
}