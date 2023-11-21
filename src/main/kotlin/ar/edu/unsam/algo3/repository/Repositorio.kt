package ar.edu.unsam.algo3.repository
import Entidad
import Figurita
import Jugador
import PuntoDeVentas
import Seleccion
import Usuario
import ar.edu.unsam.algo3.dto.UsuarioLoginDTO
import GenericException
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
@Component
open class Repositorio<T : Entidad>() {
    val elementos :MutableList<T> = mutableListOf()
    var siguienteID :Int = 1

    fun incrementadorAsignador(elemento: T) {

        elemento.id = siguienteID++
    }
    fun allInstances() = elementos

    fun allInstancesExcludeId(id : Int) = allInstances().filter {it.id != id}

    fun filterById(id : Int) = allInstances().filter {it.id == id}

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

    fun cantidadElementos(): Int {
        return elementos.size
    }


    fun validarInExistencia(elemento: T) {
     if (existeElId(elemento.id)) throw GenericException("No se pudo crear, ID ya se encuentra utilizado")
 }
    fun validarId(id: Int) {
        if (!existeElId(id)) throw GenericException("El ID $id no es valido")
    }

    /*fun orderByAscending(parameterName: String): List<T> {
        return elementos.sortedBy { it.parameterName }
    }

    fun orderByDescending(parameterName: String): List<T> {
        return elementos.sortedByDescending { it.getPropertyValue(parameterName) }
    }*/

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
class RepoUser: Repositorio<Usuario>(){
    fun getUserPass(userIdentificador: UsuarioLoginDTO) = elementos.filter { user -> user.accesoUsuario(userIdentificador) }

}
@Repository
class RepositorioGeneral{
    fun cantidadRepoVentas() = RepoPuntoDeVentas().cantidadElementos()

}

