
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonSubTypes.Type
import com.fasterxml.jackson.annotation.JsonTypeInfo
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.PROPERTY, property="posicion")
@JsonSubTypes(
    Type(value=Arquero::class, name="arquero"),
    Type(value=Delantero::class, name="Delantero")

)

interface Posicion {
    fun valorPorPosicion(jugador: Jugador) = factorPosicion(jugador) + puntos
    fun factorPosicion(jugador: Jugador): Double = if (criterio(jugador))factorCriterio(jugador) else 0.0
    fun criterio(jugador: Jugador):Boolean
    fun factorCriterio(jugador: Jugador): Double


    val puntos: Double

}

object Arquero: Posicion {

    override val puntos: Double = 100.0
    override fun valorPorPosicion(jugador: Jugador): Double = factorPosicion(jugador) * puntos
    override fun criterio(jugador: Jugador): Boolean = jugador.tieneOEsMasAltoDe(180.0)
    override fun factorCriterio(jugador: Jugador): Double =jugador.altura
    override fun factorPosicion(jugador: Jugador): Double = if (criterio(jugador))factorCriterio(jugador) else 1.0


}

object Defensor : Posicion {

    override val puntos: Double = 50.0
    override fun criterio(jugador: Jugador): Boolean = jugador.lider
    override fun factorCriterio(jugador: Jugador): Double = 10.0 * jugador.aniosJugadosEnSeleccion()

}

object Mediocampista : Posicion {

    override val puntos: Double = 150.0
    fun esLigero(jugador: Jugador): Boolean = jugador.peso in 65.0..70.0
    override fun criterio(jugador: Jugador): Boolean = esLigero(jugador)
    override fun factorCriterio(jugador: Jugador): Double = jugador.peso
}


object Delantero : Posicion {

    override val puntos: Double = 200.0
    override fun criterio(jugador: Jugador): Boolean = jugador.perteneceAUnaSeleccionCampeona()

    override fun factorCriterio(jugador: Jugador): Double = jugador.nroDeCamiseta.toDouble() *  10.0

}
 class Polivalentes(val posiciones : MutableList<Posicion>) : Posicion{
     override val puntos: Double = posiciones.sumOf { it.puntos}/cantidadDePosiciones()
     private fun cantidadDePosiciones(): Int = posiciones.count()
     override fun criterio(jugador: Jugador): Boolean = jugador.esLeyenda() || jugador.esPromesaDeFutbol()
     fun ValorPosicion(jugador: Jugador) = posiciones.sumOf { it.valorPorPosicion(jugador) }/cantidadDePosiciones()
     override fun factorCriterio(jugador: Jugador): Double = ValorPosicion(jugador) - jugador.aniosDelJugador() 

 }




