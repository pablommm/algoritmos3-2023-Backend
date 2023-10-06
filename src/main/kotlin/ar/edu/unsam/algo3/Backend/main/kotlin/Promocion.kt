import java.time.DayOfWeek
import java.time.LocalDate


interface Promocion {
    fun factorPromocion(cantidadDeSobres: Int):Double = if(cumpleCondicionDescuento(cantidadDeSobres)) valorDescuento() else valorSinDescuento(cantidadDeSobres)
    fun valorDescuento  ():Double
    fun valorSinDescuento(cantidadDeSobres: Int) :Double=1.0
    fun cumpleCondicionDescuento(cantidadDeSobres: Int):Boolean


}
object NoHayPromocion : Promocion{
    override fun valorDescuento(): Double = 1.0

    override fun cumpleCondicionDescuento(cantidadDeSobres: Int):Boolean = true
    }
object PromocionLosJueves : Promocion{
    override fun valorDescuento(): Double = 0.90
    override fun cumpleCondicionDescuento(cantidadDeSobres: Int): Boolean {
        return LocalDate.now().dayOfWeek == DayOfWeek.THURSDAY
    }


}

object PromocionDel1Hasta10 :Promocion{
    override fun valorDescuento(): Double =0.95
    override fun cumpleCondicionDescuento(cantidadDeSobres: Int): Boolean = LocalDate.now().dayOfMonth  in 1..10
}
object PromocionCompraSuperiorA200 :Promocion{
    override fun valorDescuento(): Double =0.55
    override fun cumpleCondicionDescuento(cantidadDeSobres: Int): Boolean = cantidadDeSobres > 200
}



class PromocionCombinatorio(var listaDeDescuentos: MutableList<Promocion> = mutableListOf<Promocion>()) :Promocion {
    fun aniadirDescuento(promocion: Promocion) = listaDeDescuentos.add(promocion)
    fun quitarDescuento(promocion: Promocion) = listaDeDescuentos.remove(promocion)

    override fun valorSinDescuento(cantidadDeSobres: Int): Double {
    return factorPromocion(cantidadDeSobres)
    }

    override fun valorDescuento() :Double = 0.5


    override fun cumpleCondicionDescuento(cantidadDeSobres: Int): Boolean {
     return factorPromocion(cantidadDeSobres) > 0.5
    }

    override fun factorPromocion(cantidadDeSobres: Int): Double {
       return maxOf(cantidadDeDescuentos(cantidadDeSobres),0.5)
    }
    fun cantidadDeDescuentos(cantidadDeSobres: Int) : Double {
        return (listaDeDescuentos.sumOf { it.factorPromocion(cantidadDeSobres) })
    }
}
