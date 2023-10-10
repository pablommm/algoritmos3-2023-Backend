import com.fasterxml.jackson.annotation.JsonProperty

class Seleccion(
   @JsonProperty("pais") val nombre: String,
   @JsonProperty("confederacion")  val confederacion: Confederacion,
   @JsonProperty("copasDelMundo")  val cantidadDeCopasDelMundo: Int,
   @JsonProperty("copasConfederacion")  val cantidadCopasConfederacion: Int
):Entidad() {

    fun tieneCopasPares() = cantidadDeCopasTotales().esPar()
    fun cantidadDeCopasTotales() = cantidadCopasConfederacion + cantidadDeCopasDelMundo


    fun esCampeonMundial() = cantidadDeCopasDelMundo > 0

    fun coincideSeleccion(parametro:String) = nombre.lowercase() == parametro.lowercase()


    override fun busqueda(condicionDeBusqueda: String): Boolean = coincideSeleccion(condicionDeBusqueda)

    /////// Validaciones
    fun esValidoNombre() :Boolean = nombre.isNotEmpty()
    fun validarNombre(){
        if(!esValidoNombre()) throw ExceptionNombre()
    }
    fun esValidaConfederacion() = confederacion !=null
    fun validarConfederacion(){
        if (!esValidaConfederacion()) throw genericException("La conferacion  no es valido ")
    }
    fun esValidaCantidadCopasDelMundo():Boolean = cantidadDeCopasDelMundo >= 0
    fun validarCantidadDeCopasMundo(){if(!esValidaCantidadCopasDelMundo()) throw genericException("El numero de copas  del mundo  no es valido ")}

    fun esValidaCantidadCopasConfederacion():Boolean= cantidadCopasConfederacion >= 0
    fun validarCantidadDeCopasConfederacion(){if(!esValidaCantidadCopasConfederacion()) throw  genericException("El numero de copas  confederaciones no es valido ")}
    override fun validar() {
        validarNombre()
        validarConfederacion()
        validarCantidadDeCopasMundo()
        validarCantidadDeCopasConfederacion()
    }

}