package ar.edu.unsam.algo3.repository

import Entidad
import InvalidElementException
import InvalidIdException
import PuntoDeVentas

open class Repositorio<T : Entidad> {
    var nextID = Entidad.ID_INICIAL + 1
        private set
    val elementos: MutableList<T> = mutableListOf()

    /**Agrega un nuevo objeto a la colección, y le asigna un identificador único (id).
     * El identificador puede ser autoincremental para evitar que se repita.*/
    fun create(elemento: T) {
        elemento.validar()
        elemento.id = getNewID()
        validarID(elemento.id)
        elementos.add(elemento)
    }

    fun guardar(puntoDeVentas: PuntoDeVentas){

    }

    private fun getNewID(): Int = nextID++

    private fun validarID(id: Int) {
        if (elementos.any { elemento -> elemento.id == id }) {
            throw InvalidIdException("Error ID: $id. Ya existe un elemento con ese ID")
        }
    }

    /** Elimina el objeto de la colección.*/
    fun delete(elemento: T) {
        val elementoAEliminar = getById(elemento.id)
        elementos.remove(elementoAEliminar)
    }

    private fun existeElemento(elemento: T) {
        if (!elementos.contains(elemento)) {
            throw InvalidElementException("Elemento invalido: El elemento no ha sido encontrado")
        }
    }

    /**Modifica el objeto dentro de la colección.
     * De no existir el objeto buscado, es decir, un objeto con ese id, se debe lanzar una excepción.*/

    /**fun update(elemento: T) {
        elemento.validar()
        if (elemento.esNuevo()) throw InvalidElementException("Elemento invalido: El elemento no existe en el repositorio")
        val elementoEncontrado = getById(elemento.id)

        elementoEncontrado.actualizarDatos(elemento)
    }Falta implementar actualizarDatos*/

    /**Retorna el objeto cuyo id sea el recibido como parámetro.*/
    fun getById(id: Int): T {
        val elementoRequerido = elementos.find { id == it.id }
        esNoNulo(elementoRequerido)
        return elementoRequerido!!
    }

    private fun esNoNulo(elementoRequerido: T?) {
        if (elementoRequerido == null) {
            throw InvalidElementException("Elemento invalido: No se encontro un elemento con ese ID")
        }

    }

    /**Devuelve los objetos que coincidan con la búsqueda de acuerdo a los siguientes criterios:*/
    fun search(value: String): List<T> = elementos.filter { it.busqueda(value) }


}

class RepositorioDePuntosDeVentas : Repositorio<PuntoDeVentas>() {

}

