
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

class Jugador(
    val nombre: String= "",
    val apellido: String = "",
    var fechaDeNacimiento: LocalDate = LocalDate.now(),
    val nroDeCamiseta: Int = 0,
    @JsonIgnore
    val seleccion: Seleccion = Seleccion("Argentina", Confederacion.CONMEBOL, 3 , 17),
    @JsonIgnore
    var anioDebut: LocalDate = LocalDate.now(),
    val altura: Double = 0.0,
    val peso: Double = 0.0,

    var posicion: Posicion = Delantero,
    @JsonIgnore
    var lider: Boolean = false,
    val pais: String = "",
    var cotizacion: Int = 0
) :Entidad() {

    companion object {
        const val SIN_FACTOR = 0.0
        const val COTIZACION_INFERIOR = 20000000
        const val EDAD_MAXIMA_JUGADOR_PROMESA = 22
        const val ANIOS_MAXIMO_JUGADOR_PROMESA = 2
    }
    fun esJugadorConCamisetaPar(): Boolean = nroDeCamiseta.esPar()
    fun aniosJugadosEnSeleccion() = anioDebut.edad()
    fun esCamisetaEntre(): Boolean = nroDeCamiseta in 5..10

    fun esLeyenda(): Boolean = aniosJugadosEnSeleccion() > 10 && superaCotizacionInferior() || (esCamisetaEntre() && lider)

    fun aniosDelJugador(): Long = fechaDeNacimiento.edad()

    fun superaCotizacionInferior(): Boolean = cotizacion > COTIZACION_INFERIOR
    fun tieneAntiguedadPromesa() = aniosJugadosEnSeleccion() < ANIOS_MAXIMO_JUGADOR_PROMESA
    fun tieneEdadPromesa() = aniosDelJugador() < EDAD_MAXIMA_JUGADOR_PROMESA
    fun esPromesaDeFutbol() =  tieneEdadPromesa()  && !superaCotizacionInferior() && tieneAntiguedadPromesa()

    fun tieneSeleccionConCopasPar() = seleccion.tieneCopasPares()
    fun perteneceAUnaSeleccionCampeona() = seleccion.esCampeonMundial()

    fun tieneOEsMasAltoDe(alturaMax: Double) = altura >= alturaMax

    fun valorPorPosicion(): Double = posicion.valorPorPosicion(this)
    fun cambiarPosicion(nuevaPosicion: Posicion) {  posicion = nuevaPosicion    }
    fun calcularFactor() =posicion.valorPorPosicion(this).toInt()

    @JsonProperty
    fun posicion():String = posicion.posicion

    fun coincideSeleccion(parametro:String) = seleccion.coincideSeleccion(parametro)
    fun coincideParcialNombreJugador(parametro:String) = nombre.contains(parametro)
    fun coincideParcialApellidoJugador(parametro:String) = apellido.contains(parametro)
    override fun busqueda(condicionDeBusqueda: String): Boolean= criterioDeBusqueda(condicionDeBusqueda)
    fun criterioDeBusqueda(parametro:String):Boolean= coincideSeleccion(parametro) || coincideParcialNombreJugador(parametro) || coincideParcialApellidoJugador(parametro)



    // validaciones

    fun esValidoNombre() :Boolean = nombre.isNotEmpty()
    fun validarNombre(){ if(!esValidoNombre()) throw ExceptionNombre() }
    fun esValidoApellido() :Boolean = apellido.isNotEmpty()
    fun validarApellido(){ if(!esValidoApellido()) throw ExceptionApellido() }
    fun esValidoaAltura() :Boolean = altura >=0
    fun validarAltura(){ if(!esValidoaAltura()) throw GenericException("altura no valida")}
    fun esValidoPeso() :Boolean = peso >=0
    fun validarPeso(){ if(!esValidoPeso()) throw GenericException("peso no valida") }
    fun esValidoNroDeCamiseta() :Boolean = nroDeCamiseta in 1..99
    fun validarnroDeCamiseta(){ if(!esValidoNroDeCamiseta()) throw GenericException("El numero de camiseta no es valido ") }
    fun esValidoPais() :Boolean = pais.isNotEmpty()
    fun validarPais(){ if(!esValidoPais()) throw GenericException("El nombre del pais no es valida") }

    override fun validar() {
        validarNombre()

        validarApellido()
        validarAltura()
        validarPeso()
        validarnroDeCamiseta()
        validarPais()

    }

}

