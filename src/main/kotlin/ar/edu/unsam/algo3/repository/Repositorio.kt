package ar.edu.unsam.algo3.repository
import Direccion
import Entidad
import Figurita
import Jugador
import PuntoDeVentas
import Seleccion
import TipoDeUsuario
import Usuario
import genericException
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
@Component
open class Repositorio<T : Entidad>() {
    val elementos :MutableList<T> = mutableListOf()
    var siguienteID :Int = 1

    fun incrementadorAsignador(elemento: T) {

        elemento.id = siguienteID++
    }
    fun allInstances()=elementos
    fun existeElId(id:Int) :Boolean = elementos.any { it.id == id }


    fun create(elemento: T) {
        elemento.validar()
        validarInExistencia(elemento)
        this.incrementadorAsignador(elemento)
        elementos.add(elemento)
    }

    fun delete(elemento: T) {
        elementos.remove(getById(elemento.id))
    }
    fun update(elemento: T) {
        elemento.validar()
        val elementoViejo=getById(elemento.id)
        val index= elementos.indexOf(elementoViejo)
        elementos[index] = elemento
    }

    fun getById(id: Int): T {
        validarId(id)
        return elementos.first { it.id == id }
    }
    fun search(condicionDeBusqueda :String) :List<T> = this.elementos.filter { it.busqueda(condicionDeBusqueda) }


    fun validarInExistencia(elemento: T) {
     if (existeElId(elemento.id)) throw genericException("No se pudo crear, ID ya se encuentra utilizado")
 }
    fun validarId(id: Int) {
        if (!existeElId(id)) throw genericException("El ID $id no es valido")
    }

}

@Repository
class  RepoSeleccion: Repositorio<Seleccion>()

@Repository
class RepoPuntoDeVentas: Repositorio<PuntoDeVentas>()
@Repository
class RepoFigurita: Repositorio<Figurita>()

@Repository
class RepoJugador: Repositorio<Jugador>()
@Repository
class RepoUser: Repositorio<Usuario>()

