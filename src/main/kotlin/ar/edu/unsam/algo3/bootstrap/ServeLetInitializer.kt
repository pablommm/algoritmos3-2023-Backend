package ar.edu.unsam.algo3.bootstrap

import ar.edu.unsam.algo3.ProyectoApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

class ServeLetInitializer :SpringBootServletInitializer(){

    override fun configure(application: SpringApplicationBuilder): SpringApplicationBuilder {
        return application.sources(ProyectoApplication::class.java)
    }
}