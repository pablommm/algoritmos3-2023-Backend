abstract class Entidad {

    var id: Int = 0

    fun esNuevo() = id == 0
    abstract  fun validar()
     abstract fun busqueda(condicionDeBusqueda :String) :Boolean


}