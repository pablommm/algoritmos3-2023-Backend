abstract class Entidad {

    companion object {
        const val ID_INICIAL = 0
    }
    var id: Int = 0

    fun esNuevo() = id == 0
    abstract  fun validar()
     abstract fun busqueda(condicionDeBusqueda :String) :Boolean


}