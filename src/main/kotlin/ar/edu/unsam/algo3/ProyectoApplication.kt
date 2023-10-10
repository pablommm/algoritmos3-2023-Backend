package ar.edu.unsam.algo3

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.ComponentScans

@SpringBootApplication
@ComponentScans(
    ComponentScan("kotlin")
)
class ProyectoApplication


fun main(args: Array<String>) {
    runApplication<ProyectoApplication>(*args)
}
