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
@Configuration
class CorsConfig {
    @Bean
    fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**")
                    .allowedOrigins("http://localhost:4200") // Cambia esto a la URL de tu aplicación Angular
                    .allowedMethods("GET", "POST", "PUT", "DELETE")
            }
        }
    }
}


@SpringBootApplication
@ComponentScans(
    ComponentScan("edu")
)
class ProyectoApplication{

    /*@Bean
    fun getPuntoDeVentas() = RepoPuntoDeVentas().apply {
        create(kioscoJuanito)
    }

     */
}


fun main(args: Array<String>) {
    runApplication<ProyectoApplication>(*args)
}
