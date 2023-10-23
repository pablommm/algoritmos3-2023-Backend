package ar.edu.unsam.algo3


import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

//CODIGO HACIENDO PRUEBAS PARA LINKEAR CON EL FRONT
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.ComponentScans
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

//CODIGO HACIENDO PRUEBAS PARA LINKEAR CON EL FRONT



@SpringBootApplication
@ComponentScans(
    ComponentScan("edu")
)

class ProyectoApplication{
}

fun main(args: Array<String>) {
    runApplication<ProyectoApplication>(*args)
}
