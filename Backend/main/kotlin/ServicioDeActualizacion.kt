import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

interface ServiceDeSeleccion {

    fun getSelecciones(): String
}
class CreateOrUpdateSeleccion(val serviceSeleccion :ServiceDeSeleccion)
{
    fun jsonALista(): List<Seleccion> {
        val json = serviceSeleccion.getSelecciones()
        return jacksonObjectMapper().readValue(json)
    }
    fun actualizar(repositorio: Repositorio<Seleccion>){
        jsonALista().forEach{createOrUpdate(repositorio,it)}
    }
private fun createOrUpdate(repositorio: Repositorio<Seleccion>, it: Seleccion){
        if(it.esNuevo())
            repositorio.create(it)
        else repositorio.update(it)

    }

}