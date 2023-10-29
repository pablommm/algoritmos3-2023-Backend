import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

class Figurita(
    var numero: Int = 0, var nivelDeImpresion: NivelDeImpresion, var onFire: Boolean, @JsonIgnore val jugador: Jugador
) :Entidad() {


    fun esPar(): Boolean = numero.esPar()
    @JsonProperty
    fun nombre():String = jugador.nombre

    @JsonProperty
    fun apellido():String = jugador.apellido

    @JsonProperty
    fun fechaNacimiento(): LocalDate = jugador.fechaDeNacimiento

    @JsonProperty
    fun numeroCamiseta():Int = jugador.nroDeCamiseta

    @JsonProperty
    fun seleccion():String = jugador.pais

    @JsonProperty
    fun copasDelMundo():Int = jugador.seleccion.cantidadDeCopasDelMundo

    @JsonProperty
    fun copasConfederacion():Int = jugador.seleccion.cantidadCopasConfederacion

    @JsonProperty
    fun anioDebut():LocalDate = jugador.anioDebut

    @JsonProperty
    fun altura():Double = jugador.altura

    @JsonProperty
    fun peso():Double = jugador.peso

    @JsonProperty
    fun posicion():String = jugador.posicion.posicion

    @JsonProperty
    fun pais():String = jugador.pais

    @JsonProperty
    fun cotizacionJugador():Int = jugador.cotizacion

    @JsonProperty
    fun esLider():Boolean = jugador.lider




    fun factorOnFire(): Double = if (onFire) FACTOR_ONFIRE else SIN_FACTOR
    fun factorParidad(): Double = if (esPar()) FACTOR_ES_PAR else SIN_FACTOR
    fun factorNivelImpresionBaja(): Double = if (nivelDeImpresionBaja()) SIN_FACTOR else FACTOR_IMPRESION_NO_BAJA

    fun valorPorOnFire(): Double = COSTO_FIGURITA * factorOnFire()
    fun valorPorParidad(): Double = factorParidad() * valorPorOnFire()

    @JsonProperty
    fun valoracionBase(): Double = (valorPorParidad() * factorNivelImpresionBaja())

    fun esNivelDeImpresion(nivelDeImpresion: NivelDeImpresion) = nivelDeImpresion == this.nivelDeImpresion
    fun nivelDeImpresionAlta(): Boolean = esNivelDeImpresion(NivelDeImpresion.ALTA)
    fun nivelDeImpresionBaja(): Boolean = esNivelDeImpresion(NivelDeImpresion.BAJA)


    @JsonProperty
    fun valoracionFigurita(): Double = valoracionBase() + jugador.valorPorPosicion().toDouble()
    fun esJugadorLeyenda(): Boolean = jugador.esLeyenda()
    fun esJugadorPromesa(): Boolean = jugador.esPromesaDeFutbol()

    fun esSeleccionConCopasPar(): Boolean = jugador.tieneSeleccionConCopasPar()

    fun figuritaSeleccion() = jugador.seleccion

    fun tieneJugadorConCamisetaPar() : Boolean = jugador.esJugadorConCamisetaPar()

    fun coincideNumeroFigurita(parametro: String) = numero.toString() == parametro
    fun cambiarFiguAOnFire() {
        this.onFire =true
        }
    override fun busqueda(condicionDeBusqueda: String): Boolean {
        return    jugador.coincideParcialNombreJugador(condicionDeBusqueda)  ||
                jugador.coincideParcialApellidoJugador(condicionDeBusqueda) ||
                coincideNumeroFigurita(condicionDeBusqueda) ||
                jugador.coincideSeleccion(condicionDeBusqueda)
    }


    // validaciones
    fun esValidoNumero() :Boolean = numero.esValidoNumero()
    fun validarNumero(){ if(!esValidoNumero()) throw genericException("El numero no es valido ") }
    fun esValidaNivelDeImpresion() :Boolean = nivelDeImpresion != null
    fun ValidaNivelDeImpresion(){ if (!esValidaNivelDeImpresion()) throw genericException("Nivel de Impresion no valida")}
    fun esValidaOnFire() :Boolean = onFire != null
    fun ValidaOnFire(){ if (!esValidaOnFire()) throw genericException("nivel On Fire no valido")}
    fun esValidaJugador() :Boolean = jugador != null
    fun ValidaJugador(){ if (!esValidaJugador()) throw genericException("jugador no valido")}

    override fun validar() {
        ValidaNivelDeImpresion()
        validarNumero()
        ValidaOnFire()
        ValidaJugador()
    }
    companion object {
        const val COSTO_FIGURITA = 100
        const val FACTOR_ONFIRE = 1.2
        const val FACTOR_ES_PAR = 1.1
        const val FACTOR_IMPRESION_NO_BAJA = 0.85
        const val SIN_FACTOR = 1.0
    }
}





