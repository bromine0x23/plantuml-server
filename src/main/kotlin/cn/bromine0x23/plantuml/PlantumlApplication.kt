package cn.bromine0x23.plantuml

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PlantumlApplication

fun main(args: Array<String>) {
	runApplication<PlantumlApplication>(*args)
}
